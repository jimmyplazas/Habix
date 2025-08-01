package dev.alejo.habix.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.alejo.habix.R
import dev.alejo.habix.onboarding.presentation.components.OnboardingPager

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onFinish: () -> Unit
) {
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
        onFinish = onFinish,
        modifier = modifier
    )
}