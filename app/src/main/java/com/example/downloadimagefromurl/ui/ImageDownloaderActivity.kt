package com.example.downloadimagefromurl.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.downloadimagefromurl.ui.theme.DownloadImageFromUrlTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageDownloaderActivity : ComponentActivity() {

    private val imageDownloaderViewModel :ImageDownloaderViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DownloadImageFromUrlTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageDownloaderRouter(
                        viewModel = imageDownloaderViewModel,
                        navigateToImageViewActivity = {
                            navigateToImageViewActivity()
                        }
                    )
                }
            }
        }
    }

    private fun navigateToImageViewActivity(){
        startActivity(Intent(this,ImageViewActivity::class.java))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadImageScreen(
    imageUrl: String,
    onImageUrlEnter: (String) -> Unit,
    onImageDownloadClicked: () -> Unit,
    image: ImageBitmap?,
    navigateToImageViewActivity:()->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextField(
            value = imageUrl,
            onValueChange = onImageUrlEnter,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                onImageDownloadClicked()
            }) {
            Text(text = "DownloadImage")
        }

        Button(onClick = {
            navigateToImageViewActivity()
        }) {
            Text(text = "View Imgae")
        }
        image?.let {
            Image(
                bitmap = it,
                contentDescription = "Image",
                modifier = Modifier.wrapContentSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}