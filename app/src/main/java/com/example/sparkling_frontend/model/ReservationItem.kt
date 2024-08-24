package com.example.sparkling_frontend.model

data class ReservationItem(
    val id: Int,
    val memberName: String,
    val collectorName: String,
    val completeDate: String,
    val powderQuantity: Int,
    val generalQuantity: Int,
    val professionalQuantity: Int,
    val liquidQuantity: Int,
    val ointmentQuantity: Int,
    val isCompleted: Boolean,
    val reservationDate: String,
    val address: String
)