package com.mykodo.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykodo.data.Draw
import com.mykodo.utils.CurrencyFormatter
import com.mykodo.utils.DateFormatHelper
import com.mykodo.utils.NumberFormatter
import java.util.Date

@Composable
fun DrawListScreen(
    modifier: Modifier = Modifier,
    drawList: List<Draw>,
    onDrawClicked: (id: String) -> Unit,
    currencyFormatter: CurrencyFormatter,
    dateFormatHelper: DateFormatHelper,
    numberFormatter: NumberFormatter
) {
    Log.d("-->", "DrawListScreen")
    LazyColumn(
        modifier = modifier
    ) {
        items(drawList.size) {
            DrawListItem(
                drawItem = drawList[it],
                onDrawClicked = onDrawClicked,
                currencyFormatter = currencyFormatter,
                dateFormatHelper = dateFormatHelper,
                numberFormatter = numberFormatter
            )
        }
    }
}

@Composable
fun DrawListItem(
    drawItem: Draw,
    onDrawClicked: (id: String) -> Unit,
    currencyFormatter: CurrencyFormatter,
    dateFormatHelper: DateFormatHelper,
    numberFormatter: NumberFormatter
) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = {
            onDrawClicked(drawItem.id)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Text(
                text = drawItem.id,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = "Draw Date : ${dateFormatHelper.formatDateRelative(drawItem.drawDate)}",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Chip(text = numberFormatter.prefixZeroIfSingleDigit(drawItem.number1))
                Chip(text = numberFormatter.prefixZeroIfSingleDigit(drawItem.number2))
                Chip(text = numberFormatter.prefixZeroIfSingleDigit(drawItem.number3))
                Chip(text = numberFormatter.prefixZeroIfSingleDigit(drawItem.number4))
                Chip(text = numberFormatter.prefixZeroIfSingleDigit(drawItem.number5))
                Chip(text = numberFormatter.prefixZeroIfSingleDigit(drawItem.number6))
                Chip(
                    text = numberFormatter.prefixZeroIfSingleDigit(drawItem.bonusBall),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Text(
                text = "Top Prize : £ ${currencyFormatter.formatLargeNumbers(drawItem.topPrize.toDouble())}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview()
@Composable
fun PreviewDrawListItem() {
    DrawListItem(
        drawItem = Draw(
            id = "draw-id-11",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 200000
        ),
        onDrawClicked = {},
        dateFormatHelper = DateFormatHelper(),
        currencyFormatter = CurrencyFormatter(),
        numberFormatter = NumberFormatter()
    )
}

@Preview()
@Composable
fun PreviewDrawList() {
    val drawList = listOf(
        Draw(
            id = "draw-id-11",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 200000
        ),
        Draw(
            id = "draw-id-12",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 200000
        ),
        Draw(
            id = "draw-id-13",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 200000
        ),
        Draw(
            id = "draw-id-14",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 200000
        ),
        Draw(
            id = "draw-id-15",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 200000
        ),
    )

    DrawListScreen(
        drawList = drawList,
        onDrawClicked = {},
        dateFormatHelper = DateFormatHelper(),
        currencyFormatter = CurrencyFormatter(),
        numberFormatter = NumberFormatter()
    )
}
