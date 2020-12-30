package com.example.android4a.Presentation.main

sealed class LoginStatus

data class LoginSucces(val email: String):LoginStatus()
object LoginError:LoginStatus()
