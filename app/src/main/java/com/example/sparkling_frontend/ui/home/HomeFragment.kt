package com.example.sparkling_frontend.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 수거 신청 CardView 클릭 시 ApplycollectionFragment로 이동
        binding.leftBox.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_applyCollectionFragment)
        }

        // 지도 CardView 클릭 시 CollectionmapFragment로 이동
        binding.topRightBox.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_collectionMapFragment)
        }

        // 이용방법 CardView 클릭 시 InfoFragment로 이동
        binding.bottomRightBox.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_infoFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
