package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sparkling_frontend.R

class ApplySuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.apply_success, container, false)

        // '다음' 버튼 클릭 리스너 설정
        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }

        return view
    }
}
