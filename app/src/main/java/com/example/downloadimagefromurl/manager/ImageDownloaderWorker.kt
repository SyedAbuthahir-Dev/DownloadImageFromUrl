package com.example.downloadimagefromurl.manager

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.downloadimagefromurl.utils.Constants
import com.example.downloadimagefromurl.utils.Constants.toBitmap
import java.net.URL

class ImageDownloaderWorker(
    context:Context,
    workerParameters: WorkerParameters,
):Worker(context,workerParameters) {
    override fun doWork(): Result {

        val imageUrl =inputData.getString(Constants.imageName)

        return try {
            if(imageUrl.isNullOrEmpty()){
                Log.e("result","invalid input")
                throw IllegalArgumentException("Invalid url")
            }
            val outputData = URL(imageUrl).readBytes().toBitmap()
            Log.e("worker","Image Downloaded")
            saveImage(outputData)
            Result.success()
        } catch (throwable: Throwable){
            Log.e("result","Error Downloading Image")
            Result.failure()
        }
    }
    private fun saveImage(bitmap: Bitmap?) {
        val preferenceManager = PreferenceManager(applicationContext)
        preferenceManager.saveImageInPreference(bitmap)
    }
}