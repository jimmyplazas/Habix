package dev.alejo.habix.habits.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.habix.habits.domain.model.Habit
import dev.alejo.habix.habits.domain.usecase.detail.DetailUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel(assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel @AssistedInject constructor(
    @Assisted private val habitId: String?,
    private val detailUseCases: DetailUseCases
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(habitId: String?): DetailViewModel
    }

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()
    private val _effect = Channel<DetailEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        if (habitId != null) {
            getHabitData(habitId)
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.HabitNameChange -> {
                _state.update { it.copy(habitName = event.habitName) }
            }
            is DetailEvent.FrequencyChange -> {
                val newFrequency = if (_state.value.frequency.contains(event.dayOfWeek)) {
                    _state.value.frequency - event.dayOfWeek
                } else {
                    _state.value.frequency + event.dayOfWeek
                }
                _state.update { it.copy(frequency = newFrequency) }
            }
            is DetailEvent.ReminderChange -> {
                _state.update { it.copy(reminder = event.time) }
            }
            DetailEvent.Back -> navigateBack()
            DetailEvent.Save -> {
                saveHabit()
                navigateBack()
            }
        }
    }

    private fun saveHabit() {
        viewModelScope.launch {
            val habit = Habit(
                id = _state.value.habitId ?: UUID.randomUUID().toString(),
                name = _state.value.habitName,
                frequency = _state.value.frequency.toList(),
                completedDates = _state.value.completedDates,
                reminder = _state.value.reminder,
                startDate = _state.value.startDate
            )
            detailUseCases.insertHabitUseCase(habit)
        }
    }

    private fun getHabitData(habitId: String) {
        viewModelScope.launch {
            val habitData = detailUseCases.getHabitByIdUseCase(habitId)
            _state.update {
                it.copy(
                    habitId = habitData.id,
                    habitName = habitData.name,
                    frequency = habitData.frequency.toSet(),
                    reminder = habitData.reminder,
                    completedDates = habitData.completedDates,
                    startDate = habitData.startDate
                )
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.send(DetailEffect.NavigateBack)
        }
    }
}