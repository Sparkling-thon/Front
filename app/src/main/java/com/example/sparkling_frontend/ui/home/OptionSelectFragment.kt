package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.api.RetrofitClient
import com.example.sparkling_frontend.model.ReservationRequest
import com.example.sparkling_frontend.util.PreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class OptionSelectFragment : Fragment() {

    private lateinit var drugRecyclerView: RecyclerView
    private lateinit var drugAdapter: DrugAdapter
    private val drugList = mutableListOf(
        Drug("가루약", "일반의약품", 0),
        Drug("알약 (전문의약품)", "전문의약품", 0),
        Drug("알약 (일반의약품)", "일반의약품", 0),
        Drug("물약", "일반의약품", 0),
        Drug("연고 등 특수용기", "특수용기", 0)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.optionselect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drugRecyclerView = view.findViewById(R.id.recycler_view)
        drugRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        drugAdapter = DrugAdapter(drugList)
        drugRecyclerView.adapter = drugAdapter

        view.findViewById<View>(R.id.next_button).setOnClickListener {
            sendReservationDataAndNavigate()
        }
    }

    private fun sendReservationDataAndNavigate() {
        val powderQuantity = 1
        val generalQuantity = 2
        val professionalQuantity = 3
        val liquidQuantity = 4
        val ointmentQuantity = 5

        // 서버가 기대하는 날짜 형식 확인
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        val reservationRequest = ReservationRequest(
            reservationDate = currentDate,
            powderQuantity = powderQuantity,
            generalQuantity = generalQuantity,
            professionalQuantity = professionalQuantity,
            liquidQuantity = liquidQuantity,
            ointmentQuantity = ointmentQuantity
        )

        val token = PreferenceManager.getAuthToken(requireContext()) ?: return
        val authHeader = "Bearer $token"

        // 요청 본문과 헤더를 함께 전달
        RetrofitClient.instance.reservation(authHeader, reservationRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    findNavController().navigate(R.id.applyInfoFragment)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("OptionSelectFragment", "Error: $errorBody")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("OptionSelectFragment", "Failure: ${t.message}")
            }
        })
    }
}
