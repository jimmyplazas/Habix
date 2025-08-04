package dev.alejo.habix.authentication.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dev.alejo.habix.core.presentation.HabixPasswordTextField
import dev.alejo.habix.core.presentation.HabixTextField
import dev.alejo.habix.ui.theme.AppDimens

@Composable
fun AuthTextFields() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HabixTextField(
            value = "Email",
            onValueChange = {},
            placeholder = "Email",
            contentDescription = "Enter email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppDimens.Tiny)
                .padding(horizontal = AppDimens.Default),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onAny = {

            }),
            errorMessage = null
        )

        HabixPasswordTextField(
            value = "Password",
            onValueChange = {},
            placeholder = "Password",
            contentDescription = "Enter Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppDimens.Tiny)
                .padding(horizontal = AppDimens.Default),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onAny = {

            }),
            errorMessage = null
        )
    }
}