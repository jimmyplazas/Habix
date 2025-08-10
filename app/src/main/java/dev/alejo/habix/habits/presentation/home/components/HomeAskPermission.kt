package dev.alejo.habix.habits.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import dev.alejo.habix.core.presentation.HabixButton

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeAskPermission(
    permission: String,
    modifier: Modifier = Modifier
) {
    val permissionState = rememberPermissionState(permission)

    LaunchedEffect(Unit){
        permissionState.launchPermissionRequest()
    }

    if (permissionState.status.shouldShowRationale) {
        AlertDialog(
            onDismissRequest = { },
            modifier = modifier,
            title = {
                Text(text = "Permission Required")
            },
            text = {
                Text(text = "This app needs permission to show notifications")
            },
            confirmButton = {
                HabixButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Accept",
                    onClick = { permissionState.launchPermissionRequest() }
                )
            }
        )
    }
}