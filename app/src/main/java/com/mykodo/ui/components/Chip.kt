package com.mykodo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mykodo.data.Draw
import com.mykodo.utils.CurrencyFormatter
import com.mykodo.utils.DateFormatHelper
import java.util.Date

@Composable
fun Chip(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .size(35.dp),
        shape = RoundedCornerShape(16.dp),
        color = color
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.width(20.dp),
                text = text,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview()
@Composable
fun PreviewChip() {
    Chip("23")
}
@Preview()
@Composable
fun PreviewChip1() {
    Chip("2")
}

@Preview()
@Composable
fun PreviewChip2() {
    Chip("234")
}
