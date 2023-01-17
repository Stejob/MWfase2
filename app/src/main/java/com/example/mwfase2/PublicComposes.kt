package com.example.mwfase2


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mwfase2.viewmodels.GameViewModel
import com.example.mwfase2.viewmodels.PublicComposeViewModel

private const val TAG = "'PublicComposes'"

@Composable
fun CircularHeaderImage(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel,
    row: Int,
    col: Int
) {
    val viewModel by remember {
        mutableStateOf(PublicComposeViewModel())
    }

    Column (modifier = modifier){
        Image(
            painter = painterResource(id = viewModel.imageState),
            contentDescription = "My Image",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(76.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable {
                    gameViewModel.checkIfHit(viewModel.getImgState())
                    Log.e(TAG, "CircularHeaderImage: ${viewModel.imageState}")
                }
                .testTag("circularHeaderImage tag $row, $col, resId = ${viewModel.imageState}")
        )
    }
}