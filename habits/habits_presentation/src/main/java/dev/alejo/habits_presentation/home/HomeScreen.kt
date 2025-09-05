package dev.alejo.habits_presentation.home

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.alejo.core_presentation.HabixBackground
import dev.alejo.core_presentation.HabixFloatingActionButton
import dev.alejo.core_presentation.HabixTopAppBar
import dev.alejo.core_ui.theme.AppDimens
import dev.alejo.habits_presentation.R
import dev.alejo.habits_presentation.home.components.HabitItem
import dev.alejo.habits_presentation.home.components.HomeAskPermission
import dev.alejo.habits_presentation.home.components.HomeDateSelector
import dev.alejo.habits_presentation.home.components.HomeQuote
import java.time.ZonedDateTime

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (habitId: String?) -> Unit,
    navigateToSettings: () -> Unit,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.NavigateToDetail -> {
                    navigateToDetail(effect.habitId)
                }

                HomeEffect.GoBack -> { navigateBack() }
                HomeEffect.GoSettings -> { navigateToSettings() }
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HabixTopAppBar(
                title = "Home",
                navigationIcon = Icons.Default.Settings
            ) { viewModel.onEvent(HomeEvent.GoSettings) }
        },
        floatingActionButton = {
            HabixFloatingActionButton(icon = Icons.Default.Add) {
                viewModel.onEvent(HomeEvent.AddHabit)
            }
        }
    ) { innerPadding ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            HomeAskPermission(permission = android.Manifest.permission.POST_NOTIFICATIONS)
        }
        HabixBackground()
        HomeScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun HomeScreenContent(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppDimens.Small),
        contentPadding = PaddingValues(bottom = AppDimens.Large)
    ) {
        item {
            HomeQuote(
                quote = "We first make our habits, and then our habits makes us.",
                author = "Anonymous",
                image = R.drawable.onboarding_1,
                modifier = Modifier.padding(horizontal = AppDimens.Default)
            )
            Spacer(modifier = Modifier.height(AppDimens.Small))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppDimens.Default),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Habits".uppercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f)
                )
                HomeDateSelector(
                    selectedDate = state.selectedDate,
                    mainDate = ZonedDateTime.now(),
                    onDateSelected = { dateSelected ->
                        onEvent(HomeEvent.ChangeDate(dateSelected))
                    }
                )
            }
            Spacer(modifier = Modifier.height(AppDimens.Small))
        }

        items(state.habits.size) {
            val habit = state.habits[it]
            HabitItem(
                habit = habit,
                selectedDate = state.selectedDate.toLocalDate(),
                onCheckedChange = { checked ->
                    onEvent(HomeEvent.CompleteHabit(habit))
                },
                modifier = Modifier.padding(start = AppDimens.Default),
                onHabitClick = { onEvent(HomeEvent.EditHabit(habit.id)) }
            )
        }
    }
}