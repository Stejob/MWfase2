package com.example.mwfase2

import com.example.mwfase2.viewmodels.ImagePosition
import com.example.mwfase2.viewmodels.MainActViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var viewModel: MainActViewModel

    @Before
    fun setUp(){
        viewModel = MainActViewModel()
    }
    @Test
    fun addition_isCorrect() {
        viewModel.incImageState(ImagePosition.TOP_LEFT)
        assertEquals(R.drawable.re, viewModel.getImageResource(imageState = viewModel.getImageStateTopLeft()))
    }
    @Test
    fun test_forTest (){
        viewModel.incImageState(ImagePosition.TOP_RIGHT)
        viewModel.incImageState(ImagePosition.TOP_RIGHT)
        assertEquals(R.drawable.img_20150226_wa0034, viewModel.getImageResource(viewModel.getImageStateTopRight()))

    }
    @Test
    fun state_inc (){
        var state = 0
        state = viewModel.stateAddition(state)
        state = viewModel.stateAddition(state)
        state = viewModel.stateAddition(state)
        assertEquals(1, viewModel.stateAddition(state))
    }

    @Test
    fun holes_click_test (){

    }
}