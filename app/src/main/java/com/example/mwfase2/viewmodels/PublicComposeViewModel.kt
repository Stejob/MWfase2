package com.example.mwfase2.viewmodels

import android.util.Log
import com.example.mwfase2.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "PublicComposeViewModel"

class PublicComposeViewModel(private val gameViewModel: GameViewModel) {

    private var _imageState = MutableStateFlow(R.drawable.hole)
    val imageState: StateFlow<Int> = _imageState

    init {
        gameViewModel.addImageStateList(_imageState)
        gameViewModel.checkIfReadyForRun()
    }

    fun checkClickResult() {
        if (_imageState.value == R.drawable.mouse)
            gameViewModel.incScore()
        else
            gameViewModel.incError()
    }
}