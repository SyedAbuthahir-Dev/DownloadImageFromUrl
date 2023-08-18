package com.example.downloadimagefromurl.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.downloadimagefromurl.manager.PreferenceManager

class ImageDownloaderRepository(
    private val context: Context,
    private val PreferenceManager:PreferenceManager
) {

    fun saveImageInInternalStorage(fileName: String?, imageBitmap: Bitmap?,){
        val fileOutputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE)
        imageBitmap?.compress(Bitmap.CompressFormat.JPEG,90,fileOutputStream)
        fileOutputStream.close()
    }

    fun loadImageFromInternalStorage(fileName:String, extensions:String):Bitmap{
        val imageName = "$fileName"
        val fileInputStream = context.openFileInput(imageName)
        val imageBitmap = BitmapFactory.decodeStream(fileInputStream)
        Log.e("imagedirectory",imageBitmap.toString())
        fileInputStream.close()
        return imageBitmap
    }

    fun startWorkManager(downloadWorkRequest: OneTimeWorkRequest) {
         WorkManager.getInstance().enqueue(downloadWorkRequest)
    }
}