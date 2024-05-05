package com.task.loanapplication

import android.app.Application

class LoanApp : Application(){
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        TODO("Not yet implemented")
    }
}