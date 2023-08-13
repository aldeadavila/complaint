package com.aldeadavila.complaint.screens.image

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aldeadavila.complaint.utils.Constants.ALL_IMAGES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(
    viewModel: ImageViewModel = hiltViewModel(),
    navController: NavController
) {
    val scaffoldState = SnackbarHostState()
    val coroutineScope = SnackbarHostState()
    val galleryLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        imageUri-> imageUri?.let { viewModel.addImageToStorage(imageUri) }

    }

    Scaffold (
        content = { padding->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                AbrirGaleria(
                    openGallery = {
                        galleryLauncher.launch(ALL_IMAGES)
                    }
                )
            }
        }
    )

    AddImageToStorage(
        addImageToDatabase = { downloadUrl->
            viewModel.addImageToDatabase(downloadUrl)
        }
    )
}
