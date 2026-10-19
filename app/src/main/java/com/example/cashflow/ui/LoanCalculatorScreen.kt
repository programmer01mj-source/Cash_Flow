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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflow.ui.theme.CashFlowTheme
import java.util.Locale

@Composable
fun LoanCalculatorScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
) {
    var loanAmount by remember { mutableFloatStateOf(500000f) }
    var selectedTenureMonths by remember { mutableIntStateOf(6) }

    // Calculations
    val principal = loanAmount
    val monthlyInterestRate = 0.035f // 3.5% p.m. fixed
    val totalInterest = principal * monthlyInterestRate * selectedTenureMonths
    val processingFee = principal * 0.015f // 1.5%
    val totalRepayment = principal + totalInterest + processingFee
    val monthlyInstallment = totalRepayment / selectedTenureMonths

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(
                            text = "Estimated installment:",
                            fontSize = 12.sp,
                            color = Color(0xFF64748B),
                        )
                        Text(
                            text = "MWK ${String.format(Locale.US, "%,d", monthlyInstallment.toInt())} / mo",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0066B3),
                        )
                    }

                    Button(
                        onClick = onContinueClick,
                        modifier = Modifier.height(50.dp),
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
                                text = "Continue to KYC & Verification",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                CalculatorTopBar(onBackClick = onBackClick)
            }

            // Step Progress & Pre-approved Banner
            item {
                PreApprovedBanner()
            }

            // Desired Loan Amount Card
            item {
                LoanAmountCard(
                    loanAmount = loanAmount,
                    onAmountChange = { loanAmount = it },
                    onQuickSelect = { loanAmount = it },
                )
            }

            // Repayment Tenure Card
            item {
                TenureSelectionCard(
                    selectedTenureMonths = selectedTenureMonths,
                    onTenureSelected = { selectedTenureMonths = it },
                )
            }

            // Repayment Breakdown Card
            item {
                RepaymentBreakdownCard(
                    principal = principal,
                    tenureMonths = selectedTenureMonths,
                    monthlyInstallment = monthlyInstallment,
                    totalInterest = totalInterest,
                    processingFee = processingFee,
                    totalRepayment = totalRepayment,
                )
            }

            // Zero Hidden Penalties Card
            item {
                ZeroPenaltiesCard()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun CalculatorTopBar(onBackClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF1E293B),
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF0066B3),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "R",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Loan Calculator",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Text(
                    text = "Application",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
            }

            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = Color(0xFFCBD5E1),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color(0xFF475467),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "STEP 1 OF 4",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0066B3),
            )
            Text(
                text = "Amount & Tenure",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF64748B),
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        LinearProgressIndicator(
            progress = { 0.25f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(CircleShape),
            color = Color(0xFF0066B3),
            trackColor = Color(0xFFE2EFFC),
        )
    }
}

@Composable
fun PreApprovedBanner() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFE8F2FD),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Surface(
                modifier = Modifier.size(38.dp),
                shape = CircleShape,
                color = Color(0xFFD0EAFA),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = Color(0xFF0066B3),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Pre-approved Credit Line",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Text(
                    text = "Instant disbursement to Airtel Money, Mpamba, or Bank",
                    fontSize = 12.sp,
                    color = Color(0xFF475467),
                )
            }
        }
    }
}

@Composable
fun LoanAmountCard(
    loanAmount: Float,
    onAmountChange: (Float) -> Unit,
    onQuickSelect: (Float) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color.Black.copy(alpha = 0.03f),
                spotColor = Color.Black.copy(alpha = 0.06f),
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Desired Loan Amount",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFF0066B3),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "MWK ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                    modifier = Modifier.padding(bottom = 6.dp),
                )
                Text(
                    text = String.format(Locale.US, "%,d", loanAmount.toInt()),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0066B3),
                )
            }

            Text(
                text = "Available up to MWK 2,500,000",
                fontSize = 12.sp,
                color = Color(0xFF64748B),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Slider(
                value = loanAmount,
                onValueChange = onAmountChange,
                valueRange = 50000f..2500000f,
                steps = 48,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF0066B3),
                    activeTrackColor = Color(0xFF0066B3),
                    inactiveTrackColor = Color(0xFFE2EFFC),
                ),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "MWK 50k",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                )
                Text(
                    text = "MWK 2.5M",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Quick Select Amount",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF334155),
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                QuickAmountChip(
                    text = "MWK 100k",
                    isSelected = loanAmount == 100000f,
                    onClick = { onQuickSelect(100000f) },
                    modifier = Modifier.weight(1f),
                )
                QuickAmountChip(
                    text = "MWK 250k",
                    isSelected = loanAmount == 250000f,
                    onClick = { onQuickSelect(250000f) },
                    modifier = Modifier.weight(1f),
                )
                QuickAmountChip(
                    text = "MWK 500k",
                    isSelected = loanAmount == 500000f,
                    onClick = { onQuickSelect(500000f) },
                    modifier = Modifier.weight(1f),
                )
                QuickAmountChip(
                    text = "MWK 1M",
                    isSelected = loanAmount == 1000000f,
                    onClick = { onQuickSelect(1000000f) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
fun QuickAmountChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bg = if (isSelected) Color(0xFF0066B3) else Color(0xFFF1F5F9)
    val fg = if (isSelected) Color.White else Color(0xFF334155)

    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = bg,
    ) {
        Box(
            modifier = Modifier.padding(vertical = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = fg,
            )
        }
    }
}

@Composable
fun TenureSelectionCard(
    selectedTenureMonths: Int,
    onTenureSelected: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color.Black.copy(alpha = 0.03f),
                spotColor = Color.Black.copy(alpha = 0.06f),
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Repayment Tenure",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = null,
                    tint = Color(0xFF0066B3),
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Select term length in calendar months",
                fontSize = 12.sp,
                color = Color(0xFF64748B),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                TenureOptionButton(
                    months = 3,
                    isSelected = selectedTenureMonths == 3,
                    onClick = { onTenureSelected(3) },
                    modifier = Modifier.weight(1f),
                )
                TenureOptionButton(
                    months = 6,
                    isSelected = selectedTenureMonths == 6,
                    onClick = { onTenureSelected(6) },
                    modifier = Modifier.weight(1f),
                )
                TenureOptionButton(
                    months = 9,
                    isSelected = selectedTenureMonths == 9,
                    onClick = { onTenureSelected(9) },
                    modifier = Modifier.weight(1f),
                )
                TenureOptionButton(
                    months = 12,
                    isSelected = selectedTenureMonths == 12,
                    onClick = { onTenureSelected(12) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
fun TenureOptionButton(
    months: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val bg = if (isSelected) Color(0xFF0066B3) else Color(0xFFF1F5F9)
    val textColor = if (isSelected) Color.White else Color(0xFF0F172A)
    val subColor = if (isSelected) Color(0xFFE2EFFC) else Color(0xFF64748B)

    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = bg,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "$months",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "MONTHS",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = subColor,
            )
        }
    }
}

@Composable
fun RepaymentBreakdownCard(
    principal: Float,
    tenureMonths: Int,
    monthlyInstallment: Float,
    totalInterest: Float,
    processingFee: Float,
    totalRepayment: Float,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                ambientColor = Color.Black.copy(alpha = 0.03f),
                spotColor = Color.Black.copy(alpha = 0.06f),
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Repayment Breakdown",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )

                Surface(
                    shape = CircleShape,
                    color = Color(0xFF34D399),
                ) {
                    Text(
                        text = "100% Transparent",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF1F6FA),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Estimated Monthly Installment",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "MWK ${String.format(Locale.US, "%,d", monthlyInstallment.toInt())} / month",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0066B3),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color(0xFF0066B3), CircleShape),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Principal (82.6%)",
                        fontSize = 12.sp,
                        color = Color(0xFF334155),
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(Color(0xFF38BDF8), CircleShape),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Interest & Fees (17.4%)",
                        fontSize = 12.sp,
                        color = Color(0xFF334155),
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.826f)
                        .fillMaxWidth()
                        .background(Color(0xFF0066B3)),
                )
                Box(
                    modifier = Modifier
                        .weight(0.174f)
                        .fillMaxWidth()
                        .background(Color(0xFF38BDF8)),
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            BreakdownRow(label = "Loan Principal", value = "MWK ${String.format(Locale.US, "%,d", principal.toInt())}")
            Spacer(modifier = Modifier.height(10.dp))
            BreakdownRowWithInfo(label = "Interest Rate", value = "3.5% p.m. fixed")
            Spacer(modifier = Modifier.height(10.dp))
            BreakdownRow(label = "Total Interest ($tenureMonths mos)", value = "MWK ${String.format(Locale.US, "%,d", totalInterest.toInt())}")
            Spacer(modifier = Modifier.height(10.dp))
            BreakdownRow(label = "Processing Fee (1.5%)", value = "MWK ${String.format(Locale.US, "%,d", processingFee.toInt())}")

            Spacer(modifier = Modifier.height(14.dp))
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color(0xFFE2E8F0)))
            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Total Repayment Amount",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Text(
                    text = "MWK ${String.format(Locale.US, "%,d", totalRepayment.toInt())}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0066B3),
                )
            }
        }
    }
}

@Composable
fun BreakdownRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = Color(0xFF64748B),
        )
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0F172A),
        )
    }
}

@Composable
fun BreakdownRowWithInfo(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = label,
                fontSize = 13.sp,
                color = Color(0xFF64748B),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color(0xFF94A3B8),
                modifier = Modifier.size(14.dp),
            )
        }
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0F172A),
        )
    }
}

@Composable
fun ZeroPenaltiesCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 0.03f),
                spotColor = Color.Black.copy(alpha = 0.05f),
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
        ) {
            Surface(
                modifier = Modifier.size(36.dp),
                shape = CircleShape,
                color = Color(0xFFE8F8F0),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Zero Hidden Penalties",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF10B981),
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "No hidden administrative charges. Early repayments are welcomed with zero prepayment or closure penalties.",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    lineHeight = 17.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoanCalculatorScreenPreview() {
    CashFlowTheme {
        LoanCalculatorScreen()
    }
}
