package dev.alejo.habix.habits.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.alejo.core_ui.theme.AppDimens
import java.time.LocalTime

@Composable
fun DetailReminder(
    reminder: LocalTime,
    modifier: Modifier = Modifier,
    onTimeClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppDimens.Medium))
            .background(Color.White)
            .clickable { onTimeClick() }
            .padding(AppDimens.Default),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Reminder",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.tertiary
        )

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(AppDimens.Medium)),
            horizontalArrangement = Arrangement.spacedBy(AppDimens.Small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = parseTime(reminder),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Select time",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private fun parseTime(time: LocalTime): String {
    val builder = StringBuilder()
    val hour = time.hour
    val minutes = time.minute
    if (hour < 10) {
        builder.append("0")
    }
    builder.append(hour)
    builder.append(":")
    if (minutes < 10) {
        builder.append("0")
    }
    builder.append(minutes)
    return builder.toString()
}