package com.mykodo.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mykodo.data.Draw
import com.mykodo.utils.CurrencyFormatter
import com.mykodo.utils.DateFormatHelper
import com.mykodo.utils.JsonHelper
import com.mykodo.utils.NumberFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawsViewModel @Inject constructor(
    val currencyFormatter: CurrencyFormatter,
    val dateFormatHelper: DateFormatHelper,
    val numberFormatter: NumberFormatter,
    private val jsonHelper: JsonHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow(DrawsUIState(loading = true))
    val uiState: StateFlow<DrawsUIState> = _uiState

    fun fetchDraws(context: Context) {
        viewModelScope.launch {
            jsonHelper.getJsonDataFromAsset(context = context, "drawList.json")?.let {
                val draws = jsonHelper.parseJsonToModel(it)
                _uiState.value = DrawsUIState(draws = draws)
            }
            //todo handle error
        }
    }
}

data class DrawsUIState(
    val draws: List<Draw> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)