package com.task.loanapplication.data.util

import java.util.Date

data class Payment (
    val loanAmount: String,
    val instalment: String,
    val dateTime: String
)

data class RepaymentEntry (
    val paymentDate: String,
    val paymentTime: String,
    val amount: String
)