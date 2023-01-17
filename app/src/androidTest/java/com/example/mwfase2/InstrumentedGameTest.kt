package com.example.mwfase2

import androidx.annotation.DrawableRes
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mwfase2.ui.theme.MWfase2Theme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedGameTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            MWfase2Theme {
                GameView()
            }
        }
    }

    @Test
    fun get_holes_index_per_click(){
        for (row in 0..2){
            for (col in 0..2){
                composeTestRule
                    .onNodeWithTag("circularHeaderImage tag $row, $col, resId = ${R.drawable.hole}")
                    .performClick()
                composeTestRule
                    .onNodeWithTag("circularHeaderImage tag $row, $col, resId = ${R.drawable.mouse}")
                    .assertIsDisplayed()
                if (col % 2 == 0 && row == 2){
                    composeTestRule
                        .onNodeWithTag("circularHeaderImage tag ${row + 1}, $col, resId = ${R.drawable.hole}")
                        .performClick()
                    composeTestRule
                        .onNodeWithTag("circularHeaderImage tag ${row + 1}, $col, resId = ${R.drawable.mouse}")
                        .assertIsDisplayed()
                }
            }
        }
    }

    fun hasDrawable (@DrawableRes res: Int): SemanticsMatcher =
        SemanticsMatcher.expectValue(
            SemanticsPropertyKey("My Image"), res
        )

    @Test
    fun has_correct_tag(){
        for (row in 0..2) {
            for (col in 0..2) {
                composeTestRule
                    .onNodeWithTag("circularHeaderImage tag $row, $col, resId = ${R.drawable.hole}")
                    .assertExists()
                if (col % 2 == 0 && row == 2){
                    composeTestRule
                        .onNodeWithTag("circularHeaderImage tag ${row + 1}, $col, resId = ${R.drawable.hole}")
                        .assertExists()
                }
            }
        }


    }
}