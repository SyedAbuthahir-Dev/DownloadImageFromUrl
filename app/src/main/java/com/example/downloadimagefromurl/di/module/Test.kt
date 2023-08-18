package com.example.downloadimagefromurl.di.module

import com.example.downloadimagefromurl.manager.PreferenceManager
import com.example.downloadimagefromurl.ui.ImageDownloaderRepository
import com.example.downloadimagefromurl.ui.ImageDownloaderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Test {
    fun modules() = viewModelModule + repositoryModule + commonModule
}

val repositoryModule = module {
    single {
        ImageDownloaderRepository(androidContext(),get())
    }
}

val viewModelModule = module {
    viewModel {
        ImageDownloaderViewModel(get(),get())
    }
}

val commonModule = module {
    single {
        PreferenceManager(androidContext())
    }
}
