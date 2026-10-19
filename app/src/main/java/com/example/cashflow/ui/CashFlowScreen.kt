package com.example.cashflow.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflow.ui.theme.CashFlowTheme

enum class CashFlowAppScreen {
    WELCOME,
    LOGIN,
    DASHBOARD,
    CALCULATOR,
    KYC,
    ELIGIBILITY,
    DISBURSEMENT,
}

@Composable
fun CashFlowScreen(
    modifier: Modifier = Modifier,
    initialScreen: CashFlowAppScreen = CashFlowAppScreen.WELCOME,
) {
    var currentScreen by remember { mutableStateOf(initialScreen) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC)),
    ) {
        when (currentScreen) {
            CashFlowAppScreen.WELCOME -> {
                WelcomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    onGetStartedClick = { currentScreen = CashFlowAppScreen.LOGIN },
                    onLoginClick = { currentScreen = CashFlowAppScreen.LOGIN },
                    onSkipClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                )
            }
            CashFlowAppScreen.LOGIN -> {
                LoginScreen(
                    modifier = Modifier.fillMaxSize(),
                    onSignInClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                    onBiometricsClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                    onRegisterClick = { currentScreen = CashFlowAppScreen.WELCOME },
                )
            }
            CashFlowAppScreen.DASHBOARD -> {
                CashFlowDashboardScreen(
                    modifier = Modifier.fillMaxSize(),
                    onApplyLoanClick = { currentScreen = CashFlowAppScreen.CALCULATOR },
                    onLogoutClick = { currentScreen = CashFlowAppScreen.LOGIN },
                )
            }
            CashFlowAppScreen.CALCULATOR -> {
                LoanCalculatorScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                    onContinueClick = { currentScreen = CashFlowAppScreen.KYC },
                )
            }
            CashFlowAppScreen.KYC -> {
                KycVerificationScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = { currentScreen = CashFlowAppScreen.CALCULATOR },
                    onContinueClick = { currentScreen = CashFlowAppScreen.ELIGIBILITY },
                )
            }
            CashFlowAppScreen.ELIGIBILITY -> {
                EligibilityPreApprovalScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = { currentScreen = CashFlowAppScreen.KYC },
                    onProceedClick = { currentScreen = CashFlowAppScreen.DISBURSEMENT },
                    onAdjustTermsClick = { currentScreen = CashFlowAppScreen.CALCULATOR },
                )
            }
            CashFlowAppScreen.DISBURSEMENT -> {
                LoanDisbursementScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackClick = { currentScreen = CashFlowAppScreen.ELIGIBILITY },
                    onConfirmDisburseClick = { currentScreen = CashFlowAppScreen.DASHBOARD },
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onGetStartedClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
) {
    val backgroundColor = Color(0xFFF8FAFC)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                // Top App Bar (Logo + Brand + Skip)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(42.dp),
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF0066B3),
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "R",
                                    color = Color.White,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "cashflowPartners",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A),
                            )
                            Text(
                                text = "Simple • Secure • Flexible",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B),
                            )
                        }
                    }

                    TextButton(onClick = onSkipClick) {
                        Text(
                            text = "Skip",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0066B3),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Hero Card
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 12.dp,
                            shape = RoundedCornerShape(32.dp),
                            ambientColor = Color.Black.copy(alpha = 0.04f),
                            spotColor = Color.Black.copy(alpha = 0.08f),
                        ),
                    shape = RoundedCornerShape(32.dp),
                    color = Color.White,
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFE8F2FC),
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Shield,
                                    contentDescription = null,
                                    tint = Color(0xFF0066B3),
                                    modifier = Modifier.size(15.dp),
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Up to MWK 2,500,000 with flexible tenures",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0066B3),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Box(
                            modifier = Modifier.size(100.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Surface(
                                modifier = Modifier.size(88.dp),
                                shape = RoundedCornerShape(24.dp),
                                color = Color(0xFFD6E8FA),
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.Wallet,
                                        contentDescription = null,
                                        tint = Color(0xFF0066B3),
                                        modifier = Modifier.size(44.dp),
                                    )
                                }
                            }

                            Surface(
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.BottomEnd),
                                shape = CircleShape,
                                color = Color(0xFF00C853),
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.ElectricBolt,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp),
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Get the funds you need",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A),
                            textAlign = TextAlign.Center,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Apply for an instant digital loan quickly and securely directly from your mobile device.",
                            fontSize = 14.sp,
                            color = Color(0xFF475467),
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(7.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF0066B3)),
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFD0E2F5)),
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .size(7.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFD0E2F5)),
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                WelcomeFeatureCardItem(
                    icon = Icons.Default.Wallet,
                    title = "Instant Loan Access",
                    subtitle = "Fast automated scoring & rapid payout",
                    trailingIconType = WelcomeTrailingIconType.CHECK,
                )

                Spacer(modifier = Modifier.height(10.dp))

                WelcomeFeatureCardItem(
                    icon = Icons.Default.Calculate,
                    title = "Know Your Repayment",
                    subtitle = "Transparent rates, zero hidden fees",
                    trailingIconType = WelcomeTrailingIconType.CHEVRON,
                )

                Spacer(modifier = Modifier.height(10.dp))

                WelcomeFeatureCardItem(
                    icon = Icons.Default.Schedule,
                    title = "Stay In Total Control",
                    subtitle = "Track installments & live schedules",
                    trailingIconType = WelcomeTrailingIconType.CHEVRON,
                )

                Spacer(modifier = Modifier.height(10.dp))

                WelcomeFeatureCardItem(
                    icon = Icons.Default.Security,
                    title = "Bank-grade 256-bit encryption",
                    subtitle = "& licensed micro-lending compliance.",
                    trailingIconType = WelcomeTrailingIconType.NONE,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = onGetStartedClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0066B3),
                        contentColor = Color.White,
                    ),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Get Started",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, Color(0xFFD0E2F5)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF0066B3),
                    ),
                ) {
                    Text(
                        text = "I already have an account — Log In",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = onExploreClick) {
                    Text(
                        text = "Explore all features",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF64748B),
                    )
                }
            }
        }
    }
}

enum class WelcomeTrailingIconType {
    CHECK, CHEVRON, NONE
}

@Composable
fun WelcomeFeatureCardItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    trailingIconType: WelcomeTrailingIconType,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 0.03f),
                spotColor = Color.Black.copy(alpha = 0.05f),
            ),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
            ) {
                Surface(
                    modifier = Modifier.size(42.dp),
                    shape = CircleShape,
                    color = Color(0xFFE8F2FD),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color(0xFF0066B3),
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A),
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                    )
                }
            }

            when (trailingIconType) {
                WelcomeTrailingIconType.CHECK -> {
                    Surface(
                        modifier = Modifier.size(26.dp),
                        shape = CircleShape,
                        color = Color(0xFF0066B3),
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
                WelcomeTrailingIconType.CHEVRON -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color(0xFF94A3B8),
                        modifier = Modifier.size(22.dp),
                    )
                }
                WelcomeTrailingIconType.NONE -> {}
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    CashFlowTheme {
        WelcomeScreen()
    }
}
