package dev.alejo.habits_presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.alejo.core_presentation.HabixCheckBox
import dev.alejo.core_ui.theme.AppDimens
import java.time.DayOfWeek

@Composable
fun DetailFrequencyDate(
    day: DayOfWeek,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
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
            isChecked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.semantics { contentDescription = day.name }
        )
    }
}