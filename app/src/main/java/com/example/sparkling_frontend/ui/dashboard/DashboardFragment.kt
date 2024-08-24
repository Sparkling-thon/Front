package com.example.sparkling_frontend.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sparkling_frontend.api.RetrofitClient
import com.example.sparkling_frontend.databinding.FragmentDashboardBinding
import com.example.sparkling_frontend.model.ReservationItem
import com.example.sparkling_frontend.util.PreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CollectionHistoryAdapter
    private val items = mutableListOf<ReservationItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView 설정
        setupRecyclerView()

        // API 호출
        fetchAcceptedReservations()

        return root
    }

    private fun setupRecyclerView() {
        adapter = CollectionHistoryAdapter(items)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun fetchAcceptedReservations() {
        val token = PreferenceManager.getAuthToken(requireContext()) ?: return
        val call = RetrofitClient.instance.getAcceptedReservations("Bearer $token")

        call.enqueue(object : Callback<List<ReservationItem>> {
            override fun onResponse(
                call: Call<List<ReservationItem>>,
                response: Response<List<ReservationItem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        items.clear()
                        items.addAll(it)
                        adapter.notifyDataSetChanged()

                        // 데이터 로깅 추가
                        Log.d("DashboardFragment", "Received ${items.size} items")
                        items.forEach { item ->
                            Log.d("DashboardFragment", "Item: $item")
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "데이터 로드 실패: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ReservationItem>>, t: Throwable) {
                Toast.makeText(requireContext(), "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("DashboardFragment", "Network error", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}