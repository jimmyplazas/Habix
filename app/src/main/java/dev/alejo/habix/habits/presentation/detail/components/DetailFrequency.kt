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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.alejo.habix.ui.theme.AppDimens
import java.time.DayOfWeek

@Composable
fun DetailFrequency(
    selectedDays: Set<DayOfWeek>,
    onFrequencyChange: (DayOfWeek) -> Unit,
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
                DetailFrequencyDate(
                    day = day,
                    isChecked = selectedDays.contains(day),
                    onCheckedChange = { onFrequencyChange(day) },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White)
                        .padding(vertical = AppDimens.Default)
                )
            }
        }
    }
}

@Preview
@Composable
private fun FrequencyPreview() {
    DetailFrequency(
        selectedDays = setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
        onFrequencyChange = {}
    )
}