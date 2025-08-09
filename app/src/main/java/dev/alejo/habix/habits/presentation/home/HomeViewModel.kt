package dev.alejo.habix.habits.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.habix.habits.domain.usecase.home.HabitUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HabitUseCases
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private val _effect = Channel<HomeEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private var habitsJob: Job? = null

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
            is HomeEvent.ChangeDate -> {
                _state.update {
                    it.copy(selectedDate = event.date)
                }
                getHabits()
            }

            is HomeEvent.EditHabit -> {
                viewModelScope.launch {
                    _effect.send(HomeEffect.NavigateToDetail(event.habitId))
                }
            }

            is HomeEvent.AddHabit -> {
                viewModelScope.launch {
                    _effect.send(HomeEffect.NavigateToDetail())
                }
            }

        }
    }

    private fun getHabits() {
        habitsJob?.cancel()
        habitsJob = viewModelScope.launch {
            homeUseCases.getAllHabitsForSelectedDate(_state.value.selectedDate)
                .collectLatest { habits ->
                    _state.update {
                        it.copy(habits = habits)
                    }
                }
        }
    }

}