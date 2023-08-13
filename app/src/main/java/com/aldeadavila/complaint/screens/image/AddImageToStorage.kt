package com.aldeadavila.complaint.screens.image

import android.net.Uri
import android.widget.ProgressBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.complaint.model.Response
import com.aldeadavila.complaint.components.ProgressBar
@Composable
fun AddImageToStorage(
    viewModel: ImageViewModel = hiltViewModel(),
    addImageToDatabase: (downloadUrl: Uri)->Unit
) {
    when (val addImageToStorageResponse = viewModel.addImageToStorageResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> addImageToStorageResponse.data?.let {downloadUrl ->
            LaunchedEffect(downloadUrl) {
                addImageToDatabase(downloadUrl)
            }
        }
        is Response.Failure -> print(addImageToStorageResponse.e)
    }
}

