package com.mykodo.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NumberFormatterTest {

    lateinit var subject: NumberFormatter

    @Before
    fun setUp() {
        subject = NumberFormatter()
    }

    @Test
    fun prefixZeroIfSingleDigit_onDoubleDigitNumber_returnsDoubleDigitNumber() {
        val result = subject.prefixZeroIfSingleDigit("23")
        assertEquals("23", result)
    }

    @Test
    fun prefixZeroIfSingleDigit_onSingleDigitNumber_returnsDoubleDigitNumber() {
        val result = subject.prefixZeroIfSingleDigit("3")
        assertEquals("03", result)
    }

    @Test
    fun prefixZeroIfSingleDigit_onAnyDigitNumber_returnsDoubleDigitNumber() {
        val result = subject.prefixZeroIfSingleDigit("312")
        assertEquals("312", result)
    }
}