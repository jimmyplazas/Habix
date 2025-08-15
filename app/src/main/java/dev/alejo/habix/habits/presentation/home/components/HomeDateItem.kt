package dev.alejo.habix.habits.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.alejo.habix.ui.theme.AppDimens
import java.time.ZonedDateTime

@Composable
fun HomeDateItem(
    date: ZonedDateTime,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(AppDimens.HomeDateItemSize)
            .clip(RoundedCornerShape(AppDimens.Medium)).clickable { onClick() },
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(AppDimens.Min)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = date.dayOfWeek.name.take(3),
                color = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )
            Text(
                text = date.dayOfMonth.toString(),
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        if (isSelected) {
            HorizontalDivider(
                modifier = Modifier.align(Alignment.TopCenter)
                    .padding(horizontal = AppDimens.Default)
                    .clip(RoundedCornerShape(AppDimens.Medium)),
                thickness = AppDimens.Tiny,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}