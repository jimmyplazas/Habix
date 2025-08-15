package dev.alejo.habix.habits.presentation.detail

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import dev.alejo.habix.core.presentation.HabixBackground
import dev.alejo.habix.core.presentation.HabixFloatingActionButton
import dev.alejo.habix.core.presentation.HabixTextField
import dev.alejo.habix.core.presentation.HabixTopAppBar
import dev.alejo.habix.habits.presentation.detail.components.DetailFrequency
import dev.alejo.habix.habits.presentation.detail.components.DetailReminder
import dev.alejo.habix.ui.theme.AppDimens
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    habitId: String?,
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {

    val viewModel = hiltViewModel<DetailViewModel, DetailViewModel.Factory>(
        creationCallback = { factory ->
            factory.create(habitId)
        }
    )

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                DetailEffect.NavigateBack -> { onBack() }
            }
        }
    }

    val state by viewModel.state.collectAsState()
    val clockState = rememberUseCaseState()
    val focusManager = LocalFocusManager.current
    val imeBottomPadding = WindowInsets.ime.asPaddingValues().calculateBottomPadding()
    val animatedPadding by animateDpAsState(targetValue = imeBottomPadding)

    ClockDialog(
        state = clockState,
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            viewModel.onEvent(DetailEvent.ReminderChange(
                LocalTime.of(hours, minutes)
            ))
        },
        config = ClockConfig(
            defaultTime = state.reminder,
            is24HourFormat = true
        )
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HabixTopAppBar(
                title = if (habitId == null) "New Habit" else "Habit details",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack
            ) { viewModel.onEvent(DetailEvent.Back) }
        },
        floatingActionButton = {
            HabixFloatingActionButton(
                modifier = Modifier.padding(bottom = animatedPadding),
                icon = Icons.Default.Check
            ) {
                viewModel.onEvent(DetailEvent.Save)
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
                value = state.habitName,
                onValueChange = {
                    viewModel.onEvent(DetailEvent.HabitNameChange(it))
                },
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Enter habit name",
                backgroundColor = Color.White,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                    viewModel.onEvent(DetailEvent.Save)
                }
            )

            DetailFrequency(
                selectedDays = state.frequency,
                onFrequencyChange = {
                    viewModel.onEvent(DetailEvent.FrequencyChange(it))
                }
            )

            DetailReminder(
                reminder = state.reminder
            ) { clockState.show() }
        }
    }
}