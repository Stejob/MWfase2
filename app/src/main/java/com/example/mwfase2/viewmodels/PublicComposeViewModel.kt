package com.example.mwfase2.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mwfase2.R
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

private const val TAG = "PublicComposeViewModel"
class PublicComposeViewModel: ViewModel() {

    var imageState: Int by mutableStateOf(R.drawable.hole)
    var flag = false


    fun getImgState(): Int = imageState

    init {
        runGame()
    }

    private fun changePic() {
        flag = !flag
        imageState = if (flag)
            R.drawable.mouse
        else
            R.drawable.hole
    }

    fun getRandomDelayBetweenShowTime(): Long{
        return (2000..5000).random().toLong()
    }

    fun getRandomShowTime(): Long {
        return (300..400).random().toLong()
    }

    fun runGame (){
        viewModelScope.launch {
            while (true){
                val delay = getRandomDelayBetweenShowTime()
                kotlinx.coroutines.delay(delay.milliseconds)
                Log.e(TAG, "runGame: $delay", )
                changePic()
                kotlinx.coroutines.delay(getRandomShowTime().milliseconds)
                changePic()
            }
        }
    }

}