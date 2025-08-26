package dev.alejo.authentication_presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dev.alejo.authentication_presentation.R
import dev.alejo.core_ui.theme.AppDimens
import dev.alejo.core_ui.theme.Background

@Composable
fun LoginBackground() {
    Image(
        painter = painterResource(id = R.drawable.login_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize().offset(
            y = (-AppDimens.ExtraLarge)
        )
    )
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Background,
                        Background,
                    )
                )
            )
    )
}