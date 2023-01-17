package com.example.mwfase2


import androidx.annotation.DrawableRes
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mwfase2.ui.theme.MWfase2Theme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MWfase2Theme {
                //View()
            }
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mwfase2", appContext.packageName)
    }

    @Test
    fun checkFirstImage() {
        composeTestRule.onNodeWithContentDescription("My Image").performClick()
        composeTestRule
            .onNode(hasDrawable(R.drawable.re))
            .assertIsDisplayed()
    }

    fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher =
        SemanticsMatcher.expectValue(
            SemanticsPropertyKey<Int>("My Image"), id
        )
}