package com.example.sparkling_frontend.model

data class ReservationRequest(
    val reservationDate: String,
    val powderQuantity: Int,
    val generalQuantity: Int,
    val professionalQuantity: Int,
    val liquidQuantity: Int,
    val ointmentQuantity: Int
)
