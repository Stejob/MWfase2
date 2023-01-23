package com.example.mwfase2

import com.example.mwfase2.viewmodels.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GameTest {
    lateinit var gameViewModel: GameViewModel

    @Before
    fun setUp(){
        gameViewModel = GameViewModel()
    }

    @Test
    fun is_hole_shown(){
        assertEquals(
            R.drawable.hole,
            gameViewModel.performClick(R.drawable.mouse)
        )
    }

    @Test
    fun is_mouse_shown(){
        assertEquals(
            R.drawable.mouse,
            gameViewModel.performClick(R.drawable.hole)
        )
    }

    @Test
    fun get_random_time(){
        assertTrue(gameViewModel.getRandomShowTime() in 50..100)
    }

    @Test
    fun get_delay(){

    }
}