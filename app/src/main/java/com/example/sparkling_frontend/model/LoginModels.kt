package com.example.sparkling_frontend.model

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val authType: String, val token: String)