package dev.alejo.onboarding_presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.core_presentation.HabixButton
import dev.alejo.core_presentation.HabixTitle
import dev.alejo.core_ui.theme.AppDimens
import dev.alejo.onboarding_presentation.OnboardingPagerInformation
import dev.alejo.onboarding_presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingPager(
    pages: List<OnboardingPagerInformation>,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) { page ->
            val information = pages[page]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(0.2f))
                HabixTitle(
                    title = information.title,
                    modifier = Modifier.padding(horizontal = AppDimens.Large)
                )
                Spacer(Modifier.weight(0.1f))
                Image(
                    painter = painterResource(id = information.image),
                    contentDescription = "Onboarding",
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .padding(horizontal = AppDimens.Large),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(Modifier.weight(0.1f))
                Text(
                    text = information.subtitle.uppercase(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.padding(horizontal = AppDimens.Large)
                )
                Spacer(Modifier.weight(0.2f))
            }
        }
        OnboardingIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = AppDimens.Large,
                    start = AppDimens.Default,
                    end = AppDimens.Default
                ),
            pagerState = pagerState,
            coroutineScope = coroutineScope,
            onFinish = onFinish
        )
        Spacer(Modifier.weight(0.05f))
    }
}

@Composable
fun OnboardingIndicator(
    modifier: Modifier,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    onFinish: () -> Unit
) {
    AnimatedContent(
        targetState = (pagerState.currentPage == pagerState.pageCount - 1)
    ) { isLastPage ->
        if (isLastPage) {
            HabixButton(
                text = "Get started",
                modifier = modifier
            ) { onFinish() }
        } else {
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(pagerState.pageCount - 1)
                        }
                    }
                ) {
                    Text("Skip", color = Color.Black)
                }
                Row(
                    Modifier
                        .wrapContentHeight()
                        .padding(bottom = AppDimens.Small),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.Black else MaterialTheme.colorScheme.primary
                        Box(
                            modifier = Modifier
                                .padding(AppDimens.ExtraTiny)
                                .clip(CircleShape)
                                .background(color)
                                .size(AppDimens.Small)
                        )
                    }
                }
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                ) {
                    Text("Next", color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingPagerPreview() {
    OnboardingPager(
        pages = listOf(
            OnboardingPagerInformation(
                title = "Welcome to monumental habits",
                subtitle = "We can help you to be a better version of yourself",
                image = R.drawable.onboarding_1
            ),
            OnboardingPagerInformation(
                title = "Create new habits easily",
                subtitle = "We can help you to be a better version of yourself",
                image = R.drawable.onboarding_2
            )
        ),
        onFinish = {}
    )
}