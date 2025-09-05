package dev.alejo.core_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.core_ui.theme.AppDimens

@Composable
fun HabixCheckBox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isChecked) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.background
    }

    Box(
        modifier = modifier
            .size(AppDimens.CheckBoxSize)
            .clip(RoundedCornerShape(AppDimens.Medium))
            .background(MaterialTheme.colorScheme.background)
            .padding(AppDimens.ExtraTiny)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppDimens.ExtraTiny)
                .clip(RoundedCornerShape(AppDimens.Medium))
                .background(backgroundColor)
                .clickable { onCheckedChange(!isChecked) },
            contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}

@Preview
@Composable
private fun HabixCheckBoxPreview() {
    HabixCheckBox(isChecked = false, onCheckedChange = {})
}

@Preview
@Composable
private fun HabixCheckBoxCheckedPreview() {
    HabixCheckBox(isChecked = true, onCheckedChange = {})
}