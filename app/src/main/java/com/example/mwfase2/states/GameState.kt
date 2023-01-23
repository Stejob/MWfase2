package com.example.mwfase2.states

import androidx.annotation.StringRes
import com.example.mwfase2.R

data class GameState(
    @StringRes var title: Int = R.string.error_message_title,
    @StringRes var message: Int = R.string.error_message_body
)
