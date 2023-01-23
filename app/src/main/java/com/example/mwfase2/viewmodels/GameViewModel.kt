package com.example.mwfase2.viewmodels


import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mwfase2.*
import com.example.mwfase2.states.GameState
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "GameViewModel"

open class GameViewModel : ViewModel() {

    private var isTimeUp: Boolean = false
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
    private lateinit var timer: CountDownTimer

    @StringRes
    private var errorMessage = R.string.error_message_body_error

    fun addImageStateList(imageState: MutableStateFlow<Int>) {
        _imageStateList.add(imageState)
    }

    fun incScore() {
        if (_score.value < TOTAL_SCORE - 1) {
            _score.value++
        } else{
            _score.value++
            _win.value = true
            timer.cancel()
            getGameState()
        }
    }

    fun incError() {
        if (_error.value < TOTAL_ERROR - 1) {
            _error.value++
        } else {
            errorMessage = R.string.error_message_body_error
            _error.value++
            _gameOver.value = true
            timer.cancel()
            getGameState()
        }
    }

    private fun getGameState() {
        if (_win.value) {
            _gameState.value.title = R.string.win_message_title
            _gameState.value.message = R.string.win_message_body
        }
        if (_gameOver.value) {
            _gameState.value.title = R.string.error_message_title
            if (isTimeUp)
                _gameState.value.message = R.string.error_message_body_time_up
            else
                _gameState.value.message = R.string.error_message_body_error
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
        return (MIN_DELAY_BETWEEN_SHOW_MOUSE..MAX_DELAY_BETWEEN_SHOW_MOUSE)
            .random()
            .toLong()
    }

    private fun getRandomShowTime(): Long {
        return (MIN_DELAY_FOR_SHOW_MOUSE..MAX_DELAY_FOR_SHOW_MOUSE)
            .random()
            .toLong()
    }

    fun checkIfReadyForRun() {
        if (_imageStateList.size == 11) {
            startGameTime()
            runGame()
        }
    }

    private fun startGameTime() {
        timer = object : CountDownTimer(TOTAL_TIME.toLong(), 10) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = (millisUntilFinished / TOTAL_TIME).toFloat()
            }

            override fun onFinish() {
                isTimeUp = true
                _gameOver.value = true
                getGameState()
            }
        }
        timer.start()
    }

    private fun runGame() {
        viewModelScope.launch {
            job()
        }
    }

    private suspend fun job() {
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
        isTimeUp = false
        _score.value = 0
        _error.value = 0
        startGameTime()
    }
}