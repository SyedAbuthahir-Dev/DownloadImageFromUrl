package com.example.downloadimagefromurl.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import com.example.downloadimagefromurl.ui.ui.theme.DownloadImageFromUrlTheme
import com.example.downloadimagefromurl.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageViewActivity : ComponentActivity() {

    private val imageDownloaderViewModel :ImageDownloaderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DownloadImageFromUrlTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Image(
                        bitmap = imageDownloaderViewModel.loadImageFromPreference().asImageBitmap(),
                        contentDescription = "DownlaodedImage"
                    )
                }
            }
        }
    }
}
