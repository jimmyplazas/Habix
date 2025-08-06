package dev.alejo.habix.habits.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import dev.alejo.habix.core.presentation.HabixBackground
import dev.alejo.habix.core.presentation.HabixTextField
import dev.alejo.habix.core.presentation.HabixTopAppBar
import dev.alejo.habix.habits.presentation.detail.components.Frequency
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HabixTopAppBar(
                title = "New Habit",
                navigationIcon = Icons.Default.ArrowBack
            ) {

            }
        }
    ) { innerPadding ->

        HabixBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = AppDimens.Default),
            verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
        ) {
            HabixTextField(
                placeholder = "Enter habit name",
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Enter habit name",
                backgroundColor = Color.White
            )

            Frequency()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppDimens.Medium))
                    .background(Color.White)
                    .padding(horizontal = AppDimens.Default, vertical = AppDimens.Tiny),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reminder",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )

                TextButton(onClick = { /*TODO*/ }) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(AppDimens.Medium)),
                        horizontalArrangement = Arrangement.spacedBy(AppDimens.Small),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "10:00",
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
        }
    }
}