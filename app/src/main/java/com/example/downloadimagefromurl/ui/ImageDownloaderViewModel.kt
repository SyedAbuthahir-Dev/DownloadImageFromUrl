package com.example.downloadimagefromurl.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.downloadimagefromurl.manager.ImageDownloaderWorker
import com.example.downloadimagefromurl.manager.PreferenceManager
import com.example.downloadimagefromurl.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URL

class ImageDownloaderViewModel(
    private val imageDownloaderRepository: ImageDownloaderRepository,
    private val preferenceManager: PreferenceManager
):ViewModel() {


    private val viewModelState = MutableStateFlow(
        ImageDownloaderState()
    )

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly,viewModelState.value.toUiState())

    fun onImageUrlEnter(imageUrl: String) {
        viewModelState.update { it.copy(imageUrl = imageUrl) }
    }

    fun configWorkManager(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val inputData = Data.Builder()
            .putString(Constants.imageName, uiState.value.imageUrl)
            .build()

        val downloadWorkRequest = OneTimeWorkRequestBuilder<ImageDownloaderWorker>()
                .setInputData(inputData)
                .setConstraints(constraints)
                .build()

        imageDownloaderRepository.startWorkManager(downloadWorkRequest)
    }

    fun onImageDownloadClicked() {
        viewModelScope.launch(Dispatchers.IO){
            val imageBitmap = URL(viewModelState.value.imageUrl).readBytes().toBitmap()
            val fileName = Constants.imageName
         //   saveFileToInternalStorage(fileName,imageBitmap)
            preferenceManager.saveImageInPreference(imageBitmap)
        }
    }

    fun loadImageFromPreference():Bitmap{
        return preferenceManager.retrieveImageFromPreference()
    }

    private fun ByteArray.toBitmap():Bitmap?{
        return BitmapFactory.decodeByteArray(this,0,size)
    }

//    private fun saveFileToInternalStorage(fileName: String?, imageBitmap: Bitmap?) {
//        imageDownloaderRepository.saveImageInInternalStorage(fileName,imageBitmap)
//    }
//
//    fun loadImageFromInternalStorage(fileName: String, extension: String):Bitmap{
//        return imageDownloaderRepository.loadImageFromInternalStorage(fileName,extension)
//    }

}

data class ImageDownloaderState(
    val imageUrl:String?= null,
    val image:ImageBitmap?=null,
){
    fun toUiState()=ImageDownloaderUiModel(
        imageUrl = imageUrl,
        image = image
    )
}

data class ImageDownloaderUiModel(
    val imageUrl: String?,
    val image:ImageBitmap?
)

