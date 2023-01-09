package com.example.mwfase2.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mwfase2.R

private const val TAG = "MainActViewModel"
enum class ImagePosition { TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT }

class MainActViewModel : ViewModel() {

    private var _imageStateTopLeft: Int by mutableStateOf(0)
    fun getImageStateTopLeft () = _imageStateTopLeft
    private var _imageStateTopRight: Int by mutableStateOf(0)
    fun getImageStateTopRight () = _imageStateTopRight
    private var _imageStateBottomLeft: Int by mutableStateOf(0)
    fun getImageStateBottomLeft () = _imageStateBottomLeft
    private var _imageStateBottomRight: Int by mutableStateOf(0)
    fun getImageStateBottomRight () = _imageStateBottomRight

    fun getImageResource(imageState: Int): Int {
        return when (imageState) {
            0 -> R.drawable.ic_launcher_foreground
            1 -> R.drawable.re
            else -> R.drawable.img_20150226_wa0034
        }
    }

    fun incImageState(imagePosition: ImagePosition) {
        when (imagePosition) {
            ImagePosition.TOP_LEFT -> _imageStateTopLeft = stateAddition(_imageStateTopLeft)
            ImagePosition.TOP_RIGHT -> _imageStateTopRight = stateAddition(_imageStateTopRight)
            ImagePosition.BOTTOM_LEFT -> _imageStateBottomLeft = stateAddition(_imageStateBottomLeft)
            ImagePosition.BOTTOM_RIGHT -> _imageStateBottomRight = stateAddition(_imageStateBottomRight)
        }
    }

    fun stateAddition(num: Int): Int {
        if (num < 2)
            return num + 1
        return 0
    }
}