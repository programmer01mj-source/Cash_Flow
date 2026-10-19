package com.example.cashflow

import org.junit.Assert.assertEquals
import org.junit.Test

class LoanCalculatorTest {

    @Test
    fun testLoanRepaymentCalculation_500k_6Months() {
        val principal = 500000f
        val monthlyInterestRate = 0.035f // 3.5% p.m. fixed
        val tenureMonths = 6

        val totalInterest = principal * monthlyInterestRate * tenureMonths
        val processingFee = principal * 0.015f
        val totalRepayment = principal + totalInterest + processingFee
        val monthlyInstallment = totalRepayment / tenureMonths

        assertEquals(105000f, totalInterest, 0.01f)
        assertEquals(7500f, processingFee, 0.01f)
        assertEquals(612500f, totalRepayment, 0.01f)
        assertEquals(102083.33f, monthlyInstallment, 0.1f)
    }

    @Test
    fun testLoanRepaymentCalculation_100k_3Months() {
        val principal = 100000f
        val monthlyInterestRate = 0.035f
        val tenureMonths = 3

        val totalInterest = principal * monthlyInterestRate * tenureMonths
        val processingFee = principal * 0.015f
        val totalRepayment = principal + totalInterest + processingFee
        val monthlyInstallment = totalRepayment / tenureMonths

        assertEquals(10500f, totalInterest, 0.01f)
        assertEquals(1500f, processingFee, 0.01f)
        assertEquals(112000f, totalRepayment, 0.01f)
        assertEquals(37333.33f, monthlyInstallment, 0.1f)
    }
}
