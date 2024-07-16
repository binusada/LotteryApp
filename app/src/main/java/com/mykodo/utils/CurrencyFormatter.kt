package com.mykodo.utils

import java.text.NumberFormat
import javax.inject.Inject

class CurrencyFormatter @Inject constructor() {
    fun formatLargeNumbers(number: Double): String {
        val formatter = NumberFormat.getInstance()
        formatter.maximumFractionDigits = 1

        return when {
            number >= 1_000_000_000 -> formatter.format(number / 1_000_000_000) + " Billion"
            number >= 1_000_000 -> formatter.format(number / 1_000_000) + " Million"
            else -> formatter.format(number) // Format as regular number if below a million
        }
    }
}