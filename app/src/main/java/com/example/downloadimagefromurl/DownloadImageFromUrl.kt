package com.example.downloadimagefromurl

import android.app.Application
import com.example.downloadimagefromurl.di.module.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DownloadImageFromUrl:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(Test.modules())
            androidContext(this@DownloadImageFromUrl)
            androidLogger()
        }
    }
}