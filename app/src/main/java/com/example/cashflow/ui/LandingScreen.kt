package com.example.cashflow.ui

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cashflow.ui.theme.CashFlowTheme

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFEEF5FD),
            Color(0xFFF7FAFE),
            Color(0xFFF2F6FA),
        ),
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = backgroundGradient),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Top Encrypted Badge
            Surface(
                shape = CircleShape,
                color = Color(0xFFE4EFFB),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Encrypted",
                        tint = Color(0xFF006699),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "END-TO-END ENCRYPTED",
                        color = Color(0xFF006699),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.8.sp
                    )
                }
            }

            // Center Section (Logo, Title, Subtitle, Loader)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Logo Graphic
                Box(
                    modifier = Modifier.size(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Central White Elevated Circle
                    Surface(
                        modifier = Modifier
                            .size(128.dp)
                            .shadow(
                                elevation = 16.dp,
                                shape = CircleShape,
                                ambientColor = Color(0xFF006699).copy(alpha = 0.15f),
                                spotColor = Color(0xFF006699).copy(alpha = 0.25f)
                            ),
                        shape = CircleShape,
                        color = Color.White
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            TransferArrowsLogo(modifier = Modifier.size(60.dp))
                        }
                    }

                    // Top-right status badge
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp, end = 8.dp)
                            .background(Color(0xFFD0EAFA), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .background(Color(0xFF0088CC), shape = CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Brand Title: "cashflowPartners"
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "cashflow",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101828),
                        letterSpacing = (-0.5).sp
                    )
                    Text(
                        text = "Partners",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0066B3),
                        letterSpacing = (-0.5).sp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Tagline Pill
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFEDF4FC)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = Color(0xFF536375), fontWeight = FontWeight.Normal)) {
                                append("Simple ")
                            }
                            withStyle(SpanStyle(color = Color(0xFFB0BEC5), fontWeight = FontWeight.Normal)) {
                                append(" • ")
                            }
                            withStyle(SpanStyle(color = Color(0xFF0066B3), fontWeight = FontWeight.Bold)) {
                                append(" Secure ")
                            }
                            withStyle(SpanStyle(color = Color(0xFFB0BEC5), fontWeight = FontWeight.Normal)) {
                                append(" • ")
                            }
                            withStyle(SpanStyle(color = Color(0xFF536375), fontWeight = FontWeight.Normal)) {
                                append(" Flexible")
                            }
                        },
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Loading Progress Spinner & Status Text
                CircularProgressIndicator(
                    modifier = Modifier.size(42.dp),
                    color = Color(0xFF0088CC),
                    trackColor = Color(0xFFE2EFFC),
                    strokeWidth = 3.5.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Securing verified gateway...",
                    color = Color(0xFF475467),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Bottom Section (Regulated Lending Card & Version Footer)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Regulatory Info Floating Card
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 12.dp,
                            shape = RoundedCornerShape(36.dp),
                            ambientColor = Color.Black.copy(alpha = 0.04f),
                            spotColor = Color.Black.copy(alpha = 0.08f)
                        ),
                    shape = RoundedCornerShape(36.dp),
                    color = Color.White
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(54.dp),
                            shape = CircleShape,
                            color = Color(0xFFE8F2FD)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.AccountBalance,
                                    contentDescription = "Bank",
                                    tint = Color(0xFF005596),
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = "REGULATED LENDING",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF102A43),
                                letterSpacing = 0.6.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Reserve Bank of Malawi (RBM)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF334E68)
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Tier-1 Microfinance Lic. #MF-2418",
                                fontSize = 12.sp,
                                color = Color(0xFF627D98),
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Version Footer
                Text(
                    text = "v2.4.0  •  Enterprise FinTech",
                    color = Color(0xFF829AB1),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun TransferArrowsLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val strokeWidth = w * 0.11f
        val blueColor = Color(0xFF0088CC)

        // Top Arrow Path (Pointing Left with right node dot)
        val topPath = Path().apply {
            moveTo(w * 0.75f, h * 0.28f)
            cubicTo(
                w * 0.75f, h * 0.15f,
                w * 0.35f, h * 0.15f,
                w * 0.25f, h * 0.28f
            )
        }
        drawPath(
            path = topPath,
            color = blueColor,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Top Arrow Head (Left side)
        val topArrowHead = Path().apply {
            moveTo(w * 0.15f, h * 0.28f)
            lineTo(w * 0.28f, h * 0.20f)
            moveTo(w * 0.15f, h * 0.28f)
            lineTo(w * 0.28f, h * 0.36f)
        }
        drawPath(
            path = topArrowHead,
            color = blueColor,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Top Arrow Node Dot at start
        drawCircle(
            color = blueColor,
            radius = strokeWidth * 0.8f,
            center = Offset(w * 0.75f, h * 0.28f)
        )

        // Bottom Arrow Path (Pointing Right with left node dot)
        val bottomPath = Path().apply {
            moveTo(w * 0.25f, h * 0.72f)
            cubicTo(
                w * 0.25f, h * 0.85f,
                w * 0.65f, h * 0.85f,
                w * 0.75f, h * 0.72f
            )
        }
        drawPath(
            path = bottomPath,
            color = blueColor,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )

        // Bottom Arrow Head (Right side)
        val bottomArrowHead = Path().apply {
            moveTo(w * 0.85f, h * 0.72f)
            lineTo(w * 0.72f, h * 0.64f)
            moveTo(w * 0.85f, h * 0.72f)
            lineTo(w * 0.72f, h * 0.80f)
        }
        drawPath(
            path = bottomArrowHead,
            color = blueColor,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Bottom Arrow Node Dot at start
        drawCircle(
            color = blueColor,
            radius = strokeWidth * 0.8f,
            center = Offset(w * 0.25f, h * 0.72f)
        )

        // Connection line with top right branch dot
        drawLine(
            color = blueColor,
            start = Offset(w * 0.55f, h * 0.38f),
            end = Offset(w * 0.70f, h * 0.22f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
        drawCircle(
            color = blueColor,
            radius = strokeWidth * 0.8f,
            center = Offset(w * 0.70f, h * 0.22f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LandingScreenPreview() {
    CashFlowTheme {
        LandingScreen()
    }
}
