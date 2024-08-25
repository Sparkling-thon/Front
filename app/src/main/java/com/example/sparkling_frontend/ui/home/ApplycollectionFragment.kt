package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.sparkling_frontend.R

class ApplycollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_applycollection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 꺽쇄 버튼 클릭 시 이전 화면으로 돌아가기
        view.findViewById<View>(R.id.back_button).setOnClickListener {
            findNavController().popBackStack()
        }

        // 초기 화면으로 OptionSelectFragment를 표시
        if (savedInstanceState == null) {
            val optionSelectFragment = OptionSelectFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.content_container, optionSelectFragment)
                .commit()
        }
    }
}
