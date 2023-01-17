package com.example.mwfase2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mwfase2.ui.theme.MWfase2Theme
import com.example.mwfase2.viewmodels.GameViewModel

class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MWfase2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GameView()
                }
            }
        }
    }
}

@Composable
fun GameView(viewModel: GameViewModel = GameViewModel()) {
    Surface {
        Image(
            painter = painterResource(id = R.drawable.woodenbackground),
            contentDescription = "null",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                text = stringResource(id = R.string.score, viewModel.score),
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.9f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                for (col in 0..2) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (row in 0..2) {
                            if (col % 2 != 0 && row == 0)
                                Spacer(modifier = Modifier.size(12.dp))
                            CircularHeaderImage(
                                gameViewModel = viewModel,
                                row = row,
                                col = col
                            )
                            if (col % 2 != 0 && row == 2)
                                Spacer(modifier = Modifier.size(12.dp))
                            if (col % 2 == 0 && row == 2)
                                CircularHeaderImage(
                                    gameViewModel = viewModel,
                                    row = row + 1,
                                    col = col
                                )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun decOfImages() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MWfase2Theme {
        GameView()
    }
}