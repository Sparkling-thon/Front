package com.example.sparkling_frontend.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.api.RetrofitClient
import com.example.sparkling_frontend.databinding.FragmentDashboardBinding
import com.example.sparkling_frontend.model.ReservationItem
import com.example.sparkling_frontend.util.PreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment(), CollectionHistoryAdapter.OnItemClickListener {

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
        adapter = CollectionHistoryAdapter(items, this)
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
                    }
                } else {
                    Toast.makeText(requireContext(), "데이터 로드 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ReservationItem>>, t: Throwable) {
                Toast.makeText(requireContext(), "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(item: ReservationItem) {
        // 클릭된 아이템의 상세 페이지로 이동
        val intent = Intent(requireContext(), DetailFragmentManage::class.java)
        intent.putExtra("reservation_item", item)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}