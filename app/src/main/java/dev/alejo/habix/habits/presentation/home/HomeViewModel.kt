package dev.alejo.habix.habits.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.habix.habits.domain.usecase.HomeUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getHabits()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.CompleteHabit -> {
                viewModelScope.launch {
                    homeUseCases.completeHabitUseCase(event.habit, _state.value.selectedDate)
                }
            }
            is HomeEvent.GangeDate -> {
                _state.update {
                    it.copy(selectedDate = event.date)
                }
                getHabits()
            }
        }
    }

    private fun getHabits() {
        viewModelScope.launch {
            homeUseCases.getAllHabitsForSelectedDate(_state.value.selectedDate)
                .collect { habits ->
                    _state.update {
                        it.copy(habits = habits)
                    }
                }
        }
    }

}