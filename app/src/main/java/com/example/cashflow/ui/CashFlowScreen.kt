package com.example.cashflow.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

enum class CashFlowAppScreen {
    LANDING,
    ONBOARDING,
    LOGIN,
    DASHBOARD,
}

@Composable
fun CashFlowScreen(
    modifier: Modifier = Modifier,
    initialScreen: CashFlowAppScreen = CashFlowAppScreen.LOGIN,
) {
    var currentScreen by remember { mutableStateOf(initialScreen) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                enabled = currentScreen == CashFlowAppScreen.LANDING,
                onClick = {
                    currentScreen = CashFlowAppScreen.ONBOARDING
                },
            ),
    ) {
        when (currentScreen) {
            CashFlowAppScreen.LANDING -> {
                LandingScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }
            CashFlowAppScreen.ONBOARDING -> {
                OnboardingScreen(
                    modifier = Modifier.fillMaxSize(),
                    onNextClick = { currentScreen = CashFlowAppScreen.LOGIN },
                    onSkipClick = { currentScreen = CashFlowAppScreen.LOGIN },
                    onSignInClick = { currentScreen = CashFlowAppScreen.LOGIN },
                )
            }
            CashFlowAppScreen.LOGIN -> {
                LoginScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = { currentScreen = CashFlowAppScreen.ONBOARDING },
                    onSignInClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                    onBiometricsClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                    onCreateAccountClick = { currentScreen = CashFlowAppScreen.ONBOARDING },
                )
            }
            CashFlowAppScreen.DASHBOARD -> {
                CashFlowDashboardScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
