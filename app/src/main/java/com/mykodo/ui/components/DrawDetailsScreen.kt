package com.mykodo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mykodo.R
import com.mykodo.data.Draw
import com.mykodo.utils.CurrencyFormatter
import com.mykodo.utils.DateFormatHelper
import com.mykodo.utils.NumberFormatter
import java.util.Date

@Composable
fun DrawDetailsScreen(
    modifier: Modifier = Modifier,
    draw: Draw,
    currencyFormatter: CurrencyFormatter,
    dateFormatHelper: DateFormatHelper,
    numberFormatter: NumberFormatter
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lottery_win)
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.weight(0.4f))
        Text(
            text = "Prize MONEY",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "£ ${currencyFormatter.formatLargeNumbers(draw.topPrize.toDouble())}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .height(250.dp),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = composition,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Winning Sequence",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Chip(text = numberFormatter.prefixZeroIfSingleDigit(draw.number1))
            Chip(text = numberFormatter.prefixZeroIfSingleDigit(draw.number2))
            Chip(text = numberFormatter.prefixZeroIfSingleDigit(draw.number3))
            Chip(text = numberFormatter.prefixZeroIfSingleDigit(draw.number4))
            Chip(text = numberFormatter.prefixZeroIfSingleDigit(draw.number5))
            Chip(text = numberFormatter.prefixZeroIfSingleDigit(draw.number6))
            Chip(
                text = numberFormatter.prefixZeroIfSingleDigit(draw.bonusBall),
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = draw.id,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = "Draw Date : ${dateFormatHelper.formatDateRelative(draw.drawDate)}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview()
@Composable
fun PreviewDrawDetailsScreen() {
    DrawDetailsScreen(
        draw = Draw(
            id = "draw-id-11",
            drawDate = Date(),
            number1 = "24",
            number2 = "55",
            number3 = "32",
            number4 = "76",
            number5 = "98",
            number6 = "18",
            bonusBall = "02",
            topPrize = 20000000
        ),
        dateFormatHelper = DateFormatHelper(),
        currencyFormatter = CurrencyFormatter(),
        numberFormatter = NumberFormatter()
    )
}