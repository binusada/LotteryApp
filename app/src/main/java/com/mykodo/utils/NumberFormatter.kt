package com.mykodo.utils

import javax.inject.Inject

class NumberFormatter @Inject constructor() {
    fun prefixZeroIfSingleDigit(text: String): String {
        return if (text.matches(Regex("\\d")) && text.toInt() < 10) { // Check if it's a single digit
            "0$text" // Prefix with zero
        } else {
            text // Returnoriginal text if not single digit
        }
    }
}