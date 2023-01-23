package com.example.mwfase2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
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
    val errorState = viewModel.error.collectAsState()
    val scoreState = viewModel.score.collectAsState()
    val timeLeft = viewModel.timeLeft.collectAsState()
    val gameOver = viewModel.gameOver.collectAsState()
    val win = viewModel.win.collectAsState()
    Surface {
        showAlertDialog(viewModel, gameOver.value || win.value)
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
            Spacer(modifier = Modifier.weight(0.05f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(16.dp)
                    .background(color = Color.LightGray),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(id = R.string.score, scoreState.value),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface
                )
                Text(
                    text = stringResource(id = R.string.error, errorState.value),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.error
                )
            }
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                progress = timeLeft.value)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.85f),
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
fun showAlertDialog(viewModel: GameViewModel, bo: Boolean){
    val gameState = viewModel.gameState.collectAsState()
    if (bo) {
        AlertDialog(
            onDismissRequest = {
                //viewModel.resetGame()
            },
            title = {
                Text(text = stringResource(id = gameState.value.title))
            },
            text = {
                Text(text = stringResource(id = gameState.value.message))
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { viewModel.resetGame() }
                    ) {
                        Text("Play Again?")
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MWfase2Theme {
        GameView()
    }
}