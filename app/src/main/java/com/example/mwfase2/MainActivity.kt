package com.example.mwfase2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mwfase2.ui.theme.MWfase2Theme
import com.example.mwfase2.viewmodels.ImagePosition
import com.example.mwfase2.viewmodels.MainActViewModel
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MWfase2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    View()
                }
            }
        }
    }
}

@Composable
fun View(viewModel: MainActViewModel = viewModel()) {
    //val imageClicked by viewModel.imageState.collectAsState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 80.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.c2Bright)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CircularHeaderImage(
                imageId = viewModel.getImageResource(viewModel.getImageStateTopLeft()),
                modifier = Modifier
                    .clickable { viewModel.incImageState(ImagePosition.TOP_LEFT) }
                    .align(CenterVertically)
            )
            CircularHeaderImage(
                imageId = viewModel.getImageResource(viewModel.getImageStateTopRight()),
                modifier = Modifier
                    .clickable { viewModel.incImageState(ImagePosition.TOP_RIGHT) }
                    .align(CenterVertically)
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 80.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.c2Dark)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CircularHeaderImage(
                imageId = viewModel.getImageResource(viewModel.getImageStateBottomLeft()),
                modifier = Modifier
                    .clickable { viewModel.incImageState(ImagePosition.BOTTOM_LEFT) }
                    .align(CenterVertically)
            )
            SwipeSample()
            CircularHeaderImage(
                imageId = viewModel.getImageResource(viewModel.getImageStateBottomRight()),
                modifier = Modifier
                    .clickable { viewModel.incImageState(ImagePosition.BOTTOM_RIGHT) }
                    .align(CenterVertically)
            )
        }
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MWfase2Theme {
        View()
    }
}*/
