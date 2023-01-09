package com.example.mwfase2

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Test(modifier: Modifier = Modifier){
    Text(text = "This is test")
}

@Composable
fun CircularHeaderImage(
    modifier: Modifier = Modifier,
    imageId: Int = R.drawable.hole,
    positionX: Int = 0,
    positionY: Int = 0
) {
    var positionX by remember {
        mutableStateOf(positionX)
    }
    var positionY by remember {
        mutableStateOf(positionY)
    }
    Column (modifier = modifier){
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "My Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(76.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
    }
}