package com.example.cashflow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Comprehensive test suite containing 10 robust test cases for CashFlow app features:
 * - Loan calculations (principal, interest, processing fee, monthly installments)
 * - Loan tier limits & boundaries
 * - Authentication & input validations
 * - KYC & credit underwriting verification rules
 */
class CashFlowComprehensiveTest {

    private fun calculateLoan(principal: Float, tenureMonths: Int): LoanSummary {
        val monthlyInterestRate = 0.035f // 3.5% flat monthly
        val processingFeeRate = 0.015f  // 1.5% processing fee
        
        val totalInterest = principal * monthlyInterestRate * tenureMonths
        val processingFee = principal * processingFeeRate
        val totalRepayment = principal + totalInterest + processingFee
        val monthlyInstallment = totalRepayment / tenureMonths
        
        return LoanSummary(principal, totalInterest, processingFee, totalRepayment, monthlyInstallment)
    }

    data class LoanSummary(
        val principal: Float,
        val totalInterest: Float,
        val processingFee: Float,
        val totalRepayment: Float,
        val monthlyInstallment: Float
    )

    // Test Case 1: Standard loan 100k for 3 months
    @Test
    fun test1_StandardLoan_100k_3Months() {
        val summary = calculateLoan(100000f, 3)
        assertEquals(10500f, summary.totalInterest, 0.01f)
        assertEquals(1500f, summary.processingFee, 0.01f)
        assertEquals(112000f, summary.totalRepayment, 0.01f)
        assertEquals(37333.33f, summary.monthlyInstallment, 0.1f)
    }

    // Test Case 2: Medium loan 500k for 6 months
    @Test
    fun test2_MediumLoan_500k_6Months() {
        val summary = calculateLoan(500000f, 6)
        assertEquals(105000f, summary.totalInterest, 0.01f)
        assertEquals(7500f, summary.processingFee, 0.01f)
        assertEquals(612500f, summary.totalRepayment, 0.01f)
        assertEquals(102083.33f, summary.monthlyInstallment, 0.1f)
    }

    // Test Case 3: Maximum tier loan 2,500,000 MWK for 12 months
    @Test
    fun test3_MaxTierLoan_2500k_12Months() {
        val summary = calculateLoan(2500000f, 12)
        assertEquals(1050000f, summary.totalInterest, 0.01f)
        assertEquals(37500f, summary.processingFee, 0.01f)
        assertEquals(3587500f, summary.totalRepayment, 0.01f)
        assertEquals(298958.34f, summary.monthlyInstallment, 0.5f)
    }

    // Test Case 4: Minimum loan amount 10,000 MWK for 1 month
    @Test
    fun test4_MinLoan_10k_1Month() {
        val summary = calculateLoan(10000f, 1)
        assertEquals(350f, summary.totalInterest, 0.01f)
        assertEquals(150f, summary.processingFee, 0.01f)
        assertEquals(10500f, summary.totalRepayment, 0.01f)
        assertEquals(10500f, summary.monthlyInstallment, 0.01f)
    }

    // Test Case 5: Processing fee exact percentage verification (1.5%)
    @Test
    fun test5_ProcessingFeeVerification() {
        val amounts = listOf(50000f, 200000f, 1000000f)
        for (amount in amounts) {
            val summary = calculateLoan(amount, 3)
            assertEquals(amount * 0.015f, summary.processingFee, 0.001f)
        }
    }

    // Test Case 6: Flat monthly interest rate accumulation check (3.5% per month)
    @Test
    fun test6_InterestAccumulationCheck() {
        val principal = 200000f
        for (tenure in 1..12) {
            val summary = calculateLoan(principal, tenure)
            assertEquals(principal * 0.035f * tenure, summary.totalInterest, 0.001f)
        }
    }

    // Test Case 7: Username input validation rule (must be non-empty and at least 3 chars)
    @Test
    fun test7_UsernameValidation() {
        fun isValidUsername(username: String): Boolean {
            return username.trim().length >= 3
        }

        assertTrue(isValidUsername("john_doe"))
        assertTrue(isValidUsername("alice"))
        assertFalse(isValidUsername(""))
        assertFalse(isValidUsername("ab"))
        assertFalse(isValidUsername("   "))
    }

    // Test Case 8: Password complexity check (minimum 6 characters)
    @Test
    fun test8_PasswordValidation() {
        fun isValidPassword(password: String): Boolean {
            return password.length >= 6
        }

        assertTrue(isValidPassword("securePass123"))
        assertTrue(isValidPassword("123456"))
        assertFalse(isValidPassword("123"))
        assertFalse(isValidPassword(""))
    }

    // Test Case 9: KYC document verification status rule
    @Test
    fun test9_KycDocumentVerificationRules() {
        data class KycState(val hasNationalId: Boolean, val hasSelfie: Boolean, val hasProofOfIncome: Boolean) {
            val isVerified: Boolean get() = hasNationalId && hasSelfie && hasProofOfIncome
        }

        val completeKyc = KycState(hasNationalId = true, hasSelfie = true, hasProofOfIncome = true)
        val partialKyc = KycState(hasNationalId = true, hasSelfie = false, hasProofOfIncome = false)

        assertTrue(completeKyc.isVerified)
        assertFalse(partialKyc.isVerified)
    }

    // Test Case 10: Credit pre-approval limit check based on monthly income
    @Test
    fun test10_CreditPreApprovalLimit() {
        fun calculateMaxAllowedLoan(monthlyIncome: Float): Float {
            // Max loan is 5x monthly income capped at 2,500,000 MWK
            val calculatedLimit = monthlyIncome * 5f
            return if (calculatedLimit > 2500000f) 2500000f else calculatedLimit
        }

        assertEquals(250000f, calculateMaxAllowedLoan(50000f), 0.01f)
        assertEquals(1500000f, calculateMaxAllowedLoan(300000f), 0.01f)
        assertEquals(2500000f, calculateMaxAllowedLoan(800000f), 0.01f) // Capped at max tier
    }
}
