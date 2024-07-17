package com.mykodo.utils

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CurrencyFormatterTest {

    lateinit var subject : CurrencyFormatter

    @Before
    fun setUp() {
        subject = CurrencyFormatter()
    }

    @Test
    fun formatLargeNumbers_on16Billion_returnsFormattedNumberInBillion() {
        val number: Number = 16_000_000_000
        val result = subject.formatLargeNumbers(number.toDouble())
        assertEquals("16 Billion", result)
    }

    @Test
    fun formatLargeNumbers_on16Million_returnsFormattedNumberInMillion() {
        val number: Number = 16_000_000
        val result = subject.formatLargeNumbers(number.toDouble())
        assertEquals("16 Million", result)
    }

    @Test
    fun formatLargeNumbers_on16Thousand_returnsFormattedNumber() {
        val number: Number = 16_000
        val result = subject.formatLargeNumbers(number.toDouble())
        assertEquals("16,000", result)
    }
}