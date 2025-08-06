package dev.alejo.habix.habits.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.alejo.habix.core.presentation.HabixCheckBox
import dev.alejo.habix.ui.theme.AppDimens
import java.time.DayOfWeek

@Composable
fun Frequency(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppDimens.Medium))
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
    ) {
        Text(
            text = "Habit frequency",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 16.sp,
            modifier = Modifier.padding(
                top = AppDimens.Default,
                start = AppDimens.Default,
                end = AppDimens.Default
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(top = AppDimens.ExtraTiny),
            horizontalArrangement = Arrangement.spacedBy(AppDimens.ExtraTiny)
        ) {
            DayOfWeek.entries.forEach { day ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .padding(vertical = AppDimens.Default),
                    verticalArrangement = Arrangement.spacedBy(AppDimens.ExtraTiny),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = day.name.take(3).uppercase(),
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    HabixCheckBox(
                        isChecked = true,
                        onCheckedChange = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FrequencyPreview() {
    Frequency()
}