package dev.alejo.habix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.alejo.habix.navigation.NavigationHost
import dev.alejo.habix.navigation.NavigationScreens
import dev.alejo.habix.ui.theme.HabixTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabixTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        startDestination = getStartDestination()
                    )
                }
            }
        }
    }

    private fun getStartDestination(): NavigationScreens = if (viewModel.hasSeenOnboarding) {
        NavigationScreens.Login
    } else {
        NavigationScreens.Onboarding
    }
}