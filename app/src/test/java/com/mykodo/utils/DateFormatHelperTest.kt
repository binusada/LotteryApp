package com.mykodo.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.util.Date

class DateFormatHelperTest {

    lateinit var subject: DateFormatHelper

    @Before
    fun setUp() {
        subject = DateFormatHelper()
    }

    @Test
    @Ignore ("TODO - Add wrapper for DateUtils class needed")
    fun formatDateRelative_withTodayDate_returnsToday() {
        val date = Date()
        val result = subject.formatDateRelative(date)
        assertEquals(TODAY, result)
    }
}