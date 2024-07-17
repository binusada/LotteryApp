package com.mykodo.vm

import android.content.Context
import com.mykodo.data.Draw
import com.mykodo.utils.CurrencyFormatter
import com.mykodo.utils.DateFormatHelper
import com.mykodo.utils.JsonHelper
import com.mykodo.utils.NumberFormatter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DrawsViewModelTest {

    private val currencyFormatter: CurrencyFormatter = mockk()
    private val dateFormatHelper: DateFormatHelper = mockk()
    private val numberFormatter: NumberFormatter = mockk()
    private val jsonHelper: JsonHelper = mockk()
    private val mockContext: Context = mockk()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var subject: DrawsViewModel

    @Before
    fun setUp() {
        subject = DrawsViewModel(
            currencyFormatter = currencyFormatter,
            dateFormatHelper = dateFormatHelper,
            numberFormatter = numberFormatter,
            jsonHelper = jsonHelper
        )

        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchDraws_onSuccessfulReadingJsonAndParsing_setsUIStateToSuccess() = runTest {
        val mockJsonString = "Json as a string"
        val mockDraw1: Draw = mockk()
        val mockDraw2: Draw = mockk()
        val mockDrawList = listOf(mockDraw1, mockDraw2)
        every {
            jsonHelper.getJsonDataFromAsset(
                context = mockContext,
                "drawList.json"
            )
        } returns mockJsonString
        every { jsonHelper.parseJsonToModel(mockJsonString) } returns mockDrawList

        subject.fetchDraws(mockContext)

        assertEquals(subject.uiState.value.loading, true)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(exactly = 1) { jsonHelper.parseJsonToModel(any()) }
        assertEquals(subject.uiState.value.loading, false)
        assertEquals(subject.uiState.value.draws.size, 2)
        assertEquals(subject.uiState.value.draws, mockDrawList)
    }

    @Test
    fun fetchDraws_onReadingFailed_setsUIStateToSuccess() = runTest {
        every {
            jsonHelper.getJsonDataFromAsset(
                context = mockContext,
                "drawList.json"
            )
        } returns null

        subject.fetchDraws(mockContext)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(exactly = 0) { jsonHelper.parseJsonToModel(any()) }
        assertEquals(subject.uiState.value.loading, true)
    }

    @Test
    fun fetchDraws_onJsonParsingFailed_setsUIStateToSuccess() = runTest {
        val mockJsonString = "Json as a string"
        every {
            jsonHelper.getJsonDataFromAsset(
                context = mockContext,
                "drawList.json"
            )
        } returns mockJsonString
        every { jsonHelper.parseJsonToModel(mockJsonString) } returns emptyList()

        subject.fetchDraws(mockContext)
        testDispatcher.scheduler.advanceUntilIdle()

        verify(exactly = 1) { jsonHelper.parseJsonToModel(any()) }
        assertEquals(subject.uiState.value.loading, false)
        assertEquals(subject.uiState.value.draws.size, 0)
    }
}