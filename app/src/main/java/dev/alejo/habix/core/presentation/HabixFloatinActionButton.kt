package dev.alejo.habix.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun HabixFloatingActionButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.size(AppDimens.FABSize).background(
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            shape = FloatingActionButtonDefaults.shape
        )
            .padding(AppDimens.ExtraTiny)
    ) {
        FloatingActionButton(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary,
            onClick = onClick
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}