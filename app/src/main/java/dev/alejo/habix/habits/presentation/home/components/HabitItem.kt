package dev.alejo.habix.habits.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import dev.alejo.habix.core.presentation.HabixCheckBox
import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.ui.theme.AppDimens
import java.time.LocalDate

@Composable
fun HabitItem(
    habit: Habit,
    selectedDate: LocalDate,
    onCheckedChange: (Boolean) -> Unit,
    onHabitClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(
                topStart = AppDimens.Medium,
                bottomStart = AppDimens.Medium)
            )
            .clickable { onHabitClick() }
            .background(Color.White)
            .padding(AppDimens.Default),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = habit.name
        )
        HabixCheckBox(
            isChecked = habit.completedDates.contains(selectedDate),
            onCheckedChange = onCheckedChange
        )
    }
}