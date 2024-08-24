package com.example.sparkling_frontend.api

import com.example.sparkling_frontend.model.LoginRequest
import com.example.sparkling_frontend.model.LoginResponse
import com.example.sparkling_frontend.model.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/member/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/member/join")
    fun register(@Body registerRequest: RegisterRequest): Call<Void>
}