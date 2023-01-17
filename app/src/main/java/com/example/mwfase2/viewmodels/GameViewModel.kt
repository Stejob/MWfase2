package com.example.mwfase2.viewmodels

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mwfase2.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "GameViewModel"

class GameViewModel : ViewModel() {

    var score: Int by mutableStateOf(0)

    fun incScore(){
        score++
    }

    fun checkIfHit(@DrawableRes imgRec: Int) {
        if (imgRec == R.drawable.mouse){
            incScore()
        }
    }

}