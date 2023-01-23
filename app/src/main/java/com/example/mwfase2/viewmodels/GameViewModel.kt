package com.example.mwfase2.viewmodels


import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.StateObject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mwfase2.R
import com.example.mwfase2.showAlertDialog
import com.example.mwfase2.states.GameState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking

private const val TAG = "GameViewModel"

open class GameViewModel : ViewModel() {

    private var _timeLeft = MutableStateFlow(0f)
    val timeLeft: StateFlow<Float> = _timeLeft
    private var _gameOver = MutableStateFlow(false)
    val gameOver: StateFlow<Boolean> = _gameOver
    private var _win = MutableStateFlow(false)
    val win: StateFlow<Boolean> = _win
    private var _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score
    private var _error = MutableStateFlow(0)
    val error: StateFlow<Int> = _error
    private var _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState

    private val _imageStateList = mutableListOf<MutableStateFlow<Int>>()

    fun addImageStateList(imageState: MutableStateFlow<Int>) {
        _imageStateList.add(imageState)
    }

    fun incScore() {
        if (_score.value >= 2) {
            _score.value++
            _win.value = true
            getGameState()
        } else
            _score.value++
    }

    fun incError() {
        if (_error.value >= 2) {
            _error.value++
            _gameOver.value = true
            getGameState()
        } else
            _error.value++
    }

    private fun getGameState() {
        if (_win.value) {
            _gameState.value.title = R.string.win_message_title
            _gameState.value.message = R.string.win_message_body
        }
        if (_gameOver.value) {
            _gameState.value.title = R.string.error_message_title
            _gameState.value.message = R.string.error_message_body
        }
    }

    private fun changePic(index: Int) {
        if (_imageStateList[index].value == R.drawable.hole)
            _imageStateList[index].value = R.drawable.mouse
        else
            _imageStateList[index].value = R.drawable.hole
    }

    private fun getRandomImage(): Int {
        return (0 until _imageStateList.size).random()
    }

    private fun getRandomDelayBetweenShowTime(): Long {
        return (1000..3000).random().toLong()
    }

    private fun getRandomShowTime(): Long {
        return (400..800).random().toLong()
    }

    fun checkIfReadyForRun() {
        if (_imageStateList.size == 11) {
            startGameTime()
            runGame()
        }
    }

    private fun startGameTime() {
        val timer = object : CountDownTimer(20000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e(
                    TAG,
                    "onTick: ${millisUntilFinished / 20000.0}  ${
                        (millisUntilFinished / 20000.0)
                            .toFloat()
                    }"
                )
                _timeLeft.value = (millisUntilFinished / 20000.0).toFloat()
            }

            override fun onFinish() {
                _gameOver.value = true
            }
        }
        timer.start()
    }


    private fun runGame() {
        viewModelScope.launch {
            job()
        }
    }

    private suspend fun job(){
        while (true) {
            val delay = getRandomDelayBetweenShowTime()
            val randImage = getRandomImage()
            Log.e(TAG, "runGame: $delay")
            delay(delay.milliseconds)
            changePic(randImage)
            delay(getRandomShowTime().milliseconds)
            changePic(randImage)
        }
    }

    fun resetGame() {
        _timeLeft.value = 0f
        _gameOver.value = false
        _win.value = false
        _score.value = 0
        _error.value = 0
        startGameTime()
    }
}