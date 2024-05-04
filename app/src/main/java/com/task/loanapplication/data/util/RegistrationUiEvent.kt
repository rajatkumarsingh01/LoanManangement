package com.task.loanapplication.data.util

sealed class UIEvent {
    //user perform action
    data class emailChanged(val email:String):UIEvent()
    data class passwordChanged(val password:String):UIEvent()

    object  registrationButton:UIEvent()
    object loginButton:UIEvent()

}
