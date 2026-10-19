package com.example.cashflow.ui

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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

@Composable
fun CashFlowDashboardScreen(
    modifier: Modifier = Modifier,
    userName: String = "Chisomo",
    borrowerId: String = "CFP-98241",
    remainingBalance: String = "420,000",
    principalTotal: String = "600,000",
    settledAmount: String = "180,000",
    nextDueAmount: String = "70,000",
    nextDueDate: String = "15 Nov 2024",
    onApplyLoanClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
    var selectedNavIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF8FAFC),
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(16.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                color = Color.White,
            ) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp,
                ) {
                    NavigationBarItem(
                        selected = selectedNavIndex == 0,
                        onClick = { selectedNavIndex = 0 },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF0066B3),
                            selectedTextColor = Color(0xFF0066B3),
                            indicatorColor = Color(0xFFE8F2FD),
                        ),
                    )
                    NavigationBarItem(
                        selected = selectedNavIndex == 1,
                        onClick = { selectedNavIndex = 1 },
                        icon = { Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Loans") },
                        label = { Text("Loans", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF0066B3),
                            selectedTextColor = Color(0xFF0066B3),
                            indicatorColor = Color(0xFFE8F2FD),
                        ),
                    )
                    NavigationBarItem(
                        selected = selectedNavIndex == 2,
                        onClick = { selectedNavIndex = 2 },
                        icon = { Icon(Icons.Default.Payments, contentDescription = "Payments") },
                        label = { Text("Payments", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF0066B3),
                            selectedTextColor = Color(0xFF0066B3),
                            indicatorColor = Color(0xFFE8F2FD),
                        ),
                    )
                    NavigationBarItem(
                        selected = selectedNavIndex == 3,
                        onClick = { selectedNavIndex = 3 },
                        icon = { Icon(Icons.Default.Notifications, contentDescription = "Alerts") },
                        label = { Text("Alerts", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF0066B3),
                            selectedTextColor = Color(0xFF0066B3),
                            indicatorColor = Color(0xFFE8F2FD),
                        ),
                    )
                    NavigationBarItem(
                        selected = selectedNavIndex == 4,
                        onClick = { selectedNavIndex = 4 },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                        label = { Text("Profile", fontSize = 11.sp, fontWeight = FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF0066B3),
                            selectedTextColor = Color(0xFF0066B3),
                            indicatorColor = Color(0xFFE8F2FD),
                        ),
                    )
                }
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (selectedNavIndex) {
                0 -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            DashboardTopBar(userName = userName, borrowerId = borrowerId)
                        }

                        // Active Loan Card
                        item {
                            ActiveLoanCard(
                                remainingBalance = remainingBalance,
                                principalTotal = principalTotal,
                                settledAmount = settledAmount,
                                nextDueAmount = nextDueAmount,
                                nextDueDate = nextDueDate,
                            )
                        }

                        // Quick Actions Grid
                        item {
                            QuickActionsGrid(onApplyLoanClick = onApplyLoanClick)
                        }

                        // Recent Activity Section
                        item {
                            RecentActivitySection()
                        }

                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
                1 -> {
                    LoansScreen(onApplyNewLoanClick = onApplyLoanClick)
                }
                2 -> {
                    PaymentsScreen(onMakePaymentClick = onApplyLoanClick)
                }
                3 -> {
                    AlertsScreen()
                }
                4 -> {
                    ProfileScreen(onLogoutClick = onLogoutClick)
                }
            }
        }
    }
}

@Composable
fun DashboardTopBar(userName: String, borrowerId: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "Good morning, $userName ✨",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF0F172A),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "ID: $borrowerId",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    fontWeight = FontWeight.Medium,
                )
                Text(text = " • ", fontSize = 12.sp, color = Color(0xFF94A3B8))
                Text(
                    text = "Tier 2 Verified",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF059669),
                )
            }
        }

        // Profile Avatar
        Surface(
            modifier = Modifier.size(42.dp),
            shape = CircleShape,
            color = Color(0xFF0066B3),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Composable
fun ActiveLoanCard(
    remainingBalance: String,
    principalTotal: String,
    settledAmount: String,
    nextDueAmount: String,
    nextDueDate: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color.Black.copy(alpha = 0.04f),
                spotColor = Color.Black.copy(alpha = 0.08f),
            ),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8F2FD),
                ) {
                    Text(
                        text = "Active SME Loan",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0066B3),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                }

                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = Color(0xFF0066B3),
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "REMAINING BALANCE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF64748B),
                letterSpacing = 0.8.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "MWK ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(bottom = 4.dp),
                )
                Text(
                    text = remainingBalance,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F172A),
                )
            }

            Text(
                text = "Principal total: MWK $principalTotal",
                fontSize = 12.sp,
                color = Color(0xFF64748B),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Progress
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Repayment Progress",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF334155),
                )
                Text(
                    text = "30% Complete",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0066B3),
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { 0.3f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = Color(0xFF0066B3),
                trackColor = Color(0xFFE2EFFC),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "MWK $settledAmount settled",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                )
                Text(
                    text = "4 of 6 installments left",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Next Due Banner
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF1F6FA),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = null,
                            tint = Color(0xFF0066B3),
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Next Due: MWK $nextDueAmount",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A),
                            )
                            Text(
                                text = "Due on $nextDueDate (in 4 days)",
                                fontSize = 12.sp,
                                color = Color(0xFF475467),
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
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
                    Icon(
                        imageVector = Icons.Default.ElectricBolt,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Pay Installment Now",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionsGrid(onApplyLoanClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        QuickActionButtonItem(
            icon = Icons.Default.AccountBalanceWallet,
            label = "Apply Loan",
            modifier = Modifier.weight(1f),
            isHighlighted = true,
            onClick = onApplyLoanClick,
        )
        QuickActionButtonItem(
            icon = Icons.Default.CalendarMonth,
            label = "Schedule",
            modifier = Modifier.weight(1f),
        )
        QuickActionButtonItem(
            icon = Icons.Default.Description,
            label = "Statement",
            modifier = Modifier.weight(1f),
        )
        QuickActionButtonItem(
            icon = Icons.Default.HeadsetMic,
            label = "Support",
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun QuickActionButtonItem(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false,
    onClick: () -> Unit = {},
) {
    val containerColor = if (isHighlighted) Color(0xFFE2EFFC) else Color.White
    val contentColor = if (isHighlighted) Color(0xFF0066B3) else Color(0xFF334155)

    Surface(
        onClick = onClick,
        modifier = modifier
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 0.03f),
                spotColor = Color.Black.copy(alpha = 0.05f),
            ),
        shape = RoundedCornerShape(20.dp),
        color = containerColor,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = if (isHighlighted) Color(0xFF0066B3) else Color(0xFFF1F5F9),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isHighlighted) Color.White else Color(0xFF0066B3),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun RecentActivitySection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Recent Activity",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A),
            )
            TextButton(onClick = {}) {
                Text(
                    text = "View All",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0066B3),
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        RecentActivityRow(
            title = "Repayment Received",
            subtitle = "15 Oct 2024 • Airtel Money",
            amount = "+MWK 70,000",
            status = "Successful",
        )

        Spacer(modifier = Modifier.height(10.dp))

        RecentActivityRow(
            title = "Loan Disbursed",
            subtitle = "15 Aug 2024 • National Bank",
            amount = "MWK 600,000",
            status = "Completed",
        )
    }
}

@Composable
fun RecentActivityRow(
    title: String,
    subtitle: String,
    amount: String,
    status: String,
) {
    Surface(
        modifier = Modifier
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
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
            ) {
                Surface(
                    modifier = Modifier.size(42.dp),
                    shape = CircleShape,
                    color = Color(0xFFE8F8F0),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xFF10B981),
                            modifier = Modifier.size(24.dp),
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

            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = amount,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF059669),
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CashFlowDashboardScreenPreview() {
    CashFlowTheme {
        CashFlowDashboardScreen()
    }
}
