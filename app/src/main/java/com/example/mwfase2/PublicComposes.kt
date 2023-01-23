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
        mutableStateOf(PublicComposeViewModel(gameViewModel))
    }
    val imageState = viewModel.imageState.collectAsState()

    Column (modifier = modifier){
        Image(
            painter = painterResource(id = imageState.value),// change in hence
            contentDescription = "My Image",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(76.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable { viewModel.checkClickResult() }
                .testTag("circularHeaderImage tag $row, $col"/*, resId = ${viewModel.imageState}*/)
        )
    }
}