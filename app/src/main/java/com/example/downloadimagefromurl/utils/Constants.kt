package com.example.downloadimagefromurl.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory

object Constants {

    val imageName = "studentProfileImage"
    val studentImage = "studentImage"

    fun ByteArray.toBitmap(): Bitmap?{
        return BitmapFactory.decodeByteArray(this,0,size)
    }
}