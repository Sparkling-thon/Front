package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.optionselect, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drugRecyclerView = view.findViewById(R.id.recycler_view)
        drugRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        drugAdapter = DrugAdapter(drugList)
        drugRecyclerView.adapter = drugAdapter

        view.findViewById<View>(R.id.next_button).setOnClickListener {
            // OptionSelectFragment에서 ApplyInfoFragment로 직접 네비게이트
            findNavController().navigate(R.id.applyInfoFragment)
        }
    }
}
