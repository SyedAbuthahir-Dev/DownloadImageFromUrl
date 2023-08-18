package com.example.downloadimagefromurl.manager

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.downloadimagefromurl.utils.Constants
import java.io.ByteArrayOutputStream


class PreferenceManager(
    context: Context
) {

    private val myPreference:SharedPreferences = context.getSharedPreferences("childImage",Context.MODE_PRIVATE)
    private val editor = myPreference.edit()

    fun saveImageInPreference(bitmap: Bitmap?){
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        val bitmapString = Base64.encodeToString(byteArray,Base64.DEFAULT)

        editor.putString(Constants.studentImage,bitmapString)
        editor.apply()
    }

    fun retrieveImageFromPreference():Bitmap{
        val bitmapString = myPreference.getString(Constants.studentImage,null)
        val decodedBytes: ByteArray = Base64.decode(bitmapString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}