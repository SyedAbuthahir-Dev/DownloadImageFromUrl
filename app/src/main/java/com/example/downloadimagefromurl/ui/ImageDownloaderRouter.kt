package com.example.downloadimagefromurl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ImageDownloaderRouter(
    viewModel: ImageDownloaderViewModel,
    navigateToImageViewActivity:()->Unit
){

    val uiState by viewModel.uiState.collectAsState()

    DownloadImageScreen(
        imageUrl = uiState.imageUrl.toString(),
        onImageUrlEnter = { viewModel.onImageUrlEnter(it) },
        onImageDownloadClicked = { viewModel.onImageDownloadClicked() },
        image = uiState.image,
        navigateToImageViewActivity = {
            navigateToImageViewActivity()
        }
    )
}