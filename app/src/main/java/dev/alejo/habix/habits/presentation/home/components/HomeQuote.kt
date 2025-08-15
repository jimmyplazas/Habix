package dev.alejo.habix.habits.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.alejo.habix.R
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun HomeQuote(
    quote: String,
    author: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(AppDimens.QuoteHeight)
            .clip(RoundedCornerShape(AppDimens.Medium))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.align(Alignment.TopEnd)
                .graphicsLayer(
                    scaleX = 2.2f,
                    scaleY = 2.2f
                )
                .offset(
                    x = -(AppDimens.Small),
                    y = AppDimens.Large
                )
        )
        Column(
            modifier = Modifier
                .padding(AppDimens.Default)
                .padding(end = AppDimens.QuoteTitleEndPadding),
            verticalArrangement = Arrangement.spacedBy(AppDimens.Default)
        ) {
            Text(
                text = quote.uppercase(),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "- ${author.uppercase()}",
                color = MaterialTheme.colorScheme.tertiary.copy(
                    alpha = 0.5f
                ),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeQuotePreview() {
    HomeQuote(
        quote = "akjshdkjashdkjasdhkajshd",
        author = "akshjdb",
        image = R.drawable.onboarding_1
    )
}