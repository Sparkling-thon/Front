package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sparkling_frontend.R

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
        // 이 프래그먼트의 레이아웃을 인플레이트합니다.
        return inflater.inflate(R.layout.optionselect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 초기화
        drugRecyclerView = view.findViewById(R.id.recycler_view)
        drugRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 어댑터를 초기화하고 RecyclerView에 설정
        drugAdapter = DrugAdapter(drugList)
        drugRecyclerView.adapter = drugAdapter

        // 버튼 클릭 리스너 설정
        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            navigateToApplyInfoFragment()
        }
    }

    private fun navigateToApplyInfoFragment() {
        // ApplyInfoFragment로 이동
        findNavController().navigate(R.id.action_optionSelectFragment_to_applyInfoFragment)
    }
}
