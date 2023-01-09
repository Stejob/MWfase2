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
                View()
            }
        }
    }

    @Test
    fun get_holes_index_per_click(){
        composeTestRule.onNodeWithTag("hole position 0, 0").performClick()
        composeTestRule.onNode(hasDrawable(R.drawable.mouse)).assertIsDisplayed()
    }

    fun hasDrawable (@DrawableRes res: Int): SemanticsMatcher =
        SemanticsMatcher.expectValue(
            SemanticsPropertyKey("My Image"), res
        )
}