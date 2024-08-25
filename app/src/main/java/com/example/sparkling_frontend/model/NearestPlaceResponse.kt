package com.example.sparkling_frontend.model

data class NearestPlaceResponse(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val phoneNumber: String,
    val distance: Double
)