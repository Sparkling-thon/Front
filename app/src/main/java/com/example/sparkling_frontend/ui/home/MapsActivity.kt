package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.api.RetrofitClient
import com.example.sparkling_frontend.model.NearestPlaceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity() {

    private lateinit var placeTextViews: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_collectionboxmap)

        // Initialize TextViews for each place
        placeTextViews = listOf(
            findViewById(R.id.place1_name),
            findViewById(R.id.place1_address),
            findViewById(R.id.place1_phone),
            findViewById(R.id.place1_distance),
            findViewById(R.id.place2_name),
            findViewById(R.id.place2_address),
            findViewById(R.id.place2_phone),
            findViewById(R.id.place2_distance),
            findViewById(R.id.place3_name),
            findViewById(R.id.place3_address),
            findViewById(R.id.place3_phone),
            findViewById(R.id.place3_distance),
            findViewById(R.id.place4_name),
            findViewById(R.id.place4_address),
            findViewById(R.id.place4_phone),
            findViewById(R.id.place4_distance),
            findViewById(R.id.place5_name),
            findViewById(R.id.place5_address),
            findViewById(R.id.place5_phone),
            findViewById(R.id.place5_distance)
        )

        Toast.makeText(this@MapsActivity, "Activity created", Toast.LENGTH_SHORT).show()

        // Example coordinates to test API
        val latitude = 127.3652241
        val longitude = 36.3742901

        // Call API
        fetchNearestPlaces(latitude, longitude)
    }

    private fun fetchNearestPlaces(latitude: Double, longitude: Double) {
        val apiService = RetrofitClient.instance
        val requestBody = mapOf("latitude" to latitude, "longitude" to longitude)

        Toast.makeText(this@MapsActivity, "API request started", Toast.LENGTH_SHORT).show()

        val call = apiService.getNearestPlaces(requestBody)
        call.enqueue(object : Callback<List<NearestPlaceResponse>> {
            override fun onResponse(
                call: Call<List<NearestPlaceResponse>>,
                response: Response<List<NearestPlaceResponse>>
            ) {
                if (response.isSuccessful) {
                    val places = response.body()
                    if (places.isNullOrEmpty()) {
                        Toast.makeText(this@MapsActivity, "No places found", Toast.LENGTH_SHORT).show()
                    } else {
                        // Display each place's information in the TextViews
                        for (i in places.indices) {
                            val place = places[i]
                            placeTextViews[i * 4].text = "Name: ${place.name}"
                            placeTextViews[i * 4 + 1].text = "Address: ${place.address}"
                            placeTextViews[i * 4 + 2].text = "Phone: ${place.phoneNumber}"
                            placeTextViews[i * 4 + 3].text = "Distance: ${String.format("%.3f", place.distance)} km"
                        }

                        Toast.makeText(this@MapsActivity, "Places loaded successfully", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MapsActivity, "Failed to load places: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<NearestPlaceResponse>>, t: Throwable) {
                Toast.makeText(this@MapsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}