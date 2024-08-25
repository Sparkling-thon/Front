package com.example.sparkling_frontend.api

import com.example.sparkling_frontend.model.ReservationRequest
import com.example.sparkling_frontend.model.LoginRequest
import com.example.sparkling_frontend.model.LoginResponse
import com.example.sparkling_frontend.model.RegisterRequest
import com.example.sparkling_frontend.model.ReservationItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/member/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/api/member/join")
    fun register(@Body registerRequest: RegisterRequest): Call<Void>

    @GET("/api/reservation/member/accepted")
    fun getAcceptedReservations(@Header("Authorization") token: String): Call<List<ReservationItem>>

    @POST("/api/reservation")
    fun reservation(
        @Header("Authorization") token: String,
        @Body reservationRequest: ReservationRequest
    ): Call<Void>
}
