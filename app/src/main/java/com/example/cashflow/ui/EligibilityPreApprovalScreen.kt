package com.example.cashflow.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflow.ui.theme.CashFlowTheme

@Composable
fun EligibilityPreApprovalScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onProceedClick: () -> Unit = {},
    onAdjustTermsClick: () -> Unit = {},
) {
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = onProceedClick,
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
                                text = "Proceed to Final Review & Disburse",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = onAdjustTermsClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color(0xFFD0E2F5)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFEFF5FC),
                            contentColor = Color(0xFF0066B3),
                        ),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Tune,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Adjust Loan Terms",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
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
                EligibilityTopBar(onBackClick = onBackClick)
            }

            // Instant Decision System Badge
            item {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8F8F0),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = Color(0xFF10B981),
                            modifier = Modifier.size(14.dp),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Instant Decision System",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF059669),
                        )
                    }
                }
            }

            // Header Section
            item {
                Column {
                    Text(
                        text = "Checking Your Eligibility",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A),
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Our automated underwriting engine verified your profile in real time against regulatory and financial credit standards.",
                        fontSize = 13.sp,
                        color = Color(0xFF475467),
                        lineHeight = 19.sp,
                    )
                }
            }

            // Underwriting Verification Card
            item {
                UnderwritingVerificationCard()
            }

            // Pre-Approved Offer Ready Card (Deep Blue)
            item {
                PreApprovedOfferCard()
            }

            // Direct Disbursement Guarantee Card
            item {
                DisbursementGuaranteeCard()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun EligibilityTopBar(onBackClick: () -> Unit) {
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

            Text(
                text = "Eligibility Pre Approval",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A),
            )

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
                text = "STEP 3 OF 4",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0066B3),
            )
            Text(
                text = "75% Completed",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0066B3),
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Automated Pre-Approval",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = Color(0xFF0066B3),
                    modifier = Modifier.size(15.dp),
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        LinearProgressIndicator(
            progress = { 0.75f },
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
fun UnderwritingVerificationCard() {
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
                Column {
                    Text(
                        text = "Underwriting Verification",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A),
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "6 of 6 regulatory parameters satisfied",
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                    )
                }

                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8F8F0),
                ) {
                    Text(
                        text = "Passed",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF059669),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            UnderwritingParameterRow(
                title = "Age Requirement",
                value = "18 - 65 Verified",
                subtitle = "Borrower registered age: 29 years old",
            )
            Spacer(modifier = Modifier.height(14.dp))
            UnderwritingParameterRow(
                title = "Identity Verification",
                value = "Confirmed",
                subtitle = "National ID & biometric liveness match confirmed",
            )
            Spacer(modifier = Modifier.height(14.dp))
            UnderwritingParameterRow(
                title = "Income Verification",
                value = "Threshold Met",
                subtitle = "MWK 380,000/mo net validated via payroll link",
            )
            Spacer(modifier = Modifier.height(14.dp))
            UnderwritingParameterRow(
                title = "Debt-to-Income (DTI)",
                value = "24%",
                subtitle = "Optimal ratio, well below 45% regulatory ceiling",
            )
            Spacer(modifier = Modifier.height(14.dp))
            UnderwritingParameterRow(
                title = "Defaulter Registry",
                value = "Clear",
                subtitle = "Zero adverse records across all partner micro-financiers",
            )
            Spacer(modifier = Modifier.height(14.dp))
            UnderwritingParameterRow(
                title = "Existing Loan Status",
                value = "Excellent",
                subtitle = "Consistent on-time repayments in current credit profile",
            )
        }
    }
}

@Composable
fun UnderwritingParameterRow(title: String, value: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.weight(1f),
        ) {
            Surface(
                modifier = Modifier.size(20.dp),
                shape = CircleShape,
                color = Color(0xFFE8F8F0),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF10B981),
                        modifier = Modifier.size(12.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B),
                    lineHeight = 15.sp,
                )
            }
        }

        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF059669),
            textAlign = TextAlign.End,
        )
    }
}

@Composable
fun PreApprovedOfferCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(28.dp),
                ambientColor = Color(0xFF0066B3).copy(alpha = 0.2f),
                spotColor = Color(0xFF0066B3).copy(alpha = 0.3f),
            ),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0066B3)),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8F8F0),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = Color(0xFF10B981),
                            modifier = Modifier.size(14.dp),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Pre-Approved Offer Ready",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF059669),
                        )
                    }
                }

                Surface(
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape,
                    color = Color(0xFF29B6F6).copy(alpha = 0.3f),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "QUALIFIED MAXIMUM CAPACITY",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF90CAF9),
                letterSpacing = 0.8.sp,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "You are eligible for up to\nMWK 750,000",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                lineHeight = 34.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Based on your evaluated risk score and consistent cash flow stability.",
                fontSize = 12.sp,
                color = Color(0xFFE2EFFC),
                lineHeight = 17.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Inner White Card (Loan Configuration)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "LOAN CONFIGURATION",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF64748B),
                            letterSpacing = 0.6.sp,
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color(0xFF0066B3),
                                modifier = Modifier.size(12.dp),
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Fixed Rate",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0066B3),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column {
                            Text(text = "Selected Amount", fontSize = 11.sp, color = Color(0xFF64748B))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "MWK 500,000", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Approved Tenure", fontSize = 11.sp, color = Color(0xFF64748B))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "6 Months", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F172A))
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column {
                            Text(text = "Preferential APR", fontSize = 11.sp, color = Color(0xFF64748B))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "3.2% / mo", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF059669))
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Monthly Installment", fontSize = 11.sp, color = Color(0xFF64748B))
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "MWK 99,333", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0066B3))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color(0xFF90CAF9),
                    modifier = Modifier.size(14.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "This rate is locked and guaranteed for the next 48 hours.",
                    fontSize = 12.sp,
                    color = Color(0xFFE2EFFC),
                )
            }
        }
    }
}

@Composable
fun DisbursementGuaranteeCard() {
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
                    text = "Direct Disbursement Guarantee",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A),
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Funds are transferred to your mobile wallet or bank account within 15 minutes of signing.",
                    fontSize = 12.sp,
                    color = Color(0xFF475467),
                    lineHeight = 17.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EligibilityPreApprovalScreenPreview() {
    CashFlowTheme {
        EligibilityPreApprovalScreen()
    }
}
