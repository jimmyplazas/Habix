package dev.alejo.onboarding_presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.alejo.onboarding_presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel(),
    onFinish: () -> Unit
) {

    LaunchedEffect(viewModel.hasSeenOnboarding) {
        if (viewModel.hasSeenOnboarding) {
            onFinish()
        }
    }

    val pages = listOf(
        OnboardingPagerInformation(
            title = "Welcome to monumental habits",
            subtitle = "We can help you to be a better version of yourself",
            image = R.drawable.onboarding_1
        ),
        OnboardingPagerInformation(
            title = "Create new habits easily",
            subtitle = "We can help you to be a better version of yourself",
            image = R.drawable.onboarding_2
        ),
        OnboardingPagerInformation(
            title = "Keep track of your progress",
            subtitle = "We can help you to be a better version of yourself",
            image = R.drawable.onboarding_3
        ),
        OnboardingPagerInformation(
            title = "Join a supportive community",
            subtitle = "We can help you to be a better version of yourself",
            image = R.drawable.onboarding_4
        )
    )
    OnboardingPager(
        pages = pages,
        onFinish = { viewModel.completeOnboarding() },
        modifier = modifier
    )
}