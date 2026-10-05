package com.example.cashflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cashflow.ui.CashFlowScreen
import com.example.cashflow.ui.theme.CashFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CashFlowTheme {
                CashFlowScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainCashFlowPreview() {
    CashFlowTheme {
        CashFlowScreen()
    }
}
