package com.example.sparkling_frontend.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sparkling_frontend.R
import java.text.SimpleDateFormat
import java.util.*

class ApplyInfoFragment : Fragment() {

    private lateinit var selectedDateText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.applyinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedDateText = view.findViewById(R.id.selected_date_text)

        // 날짜 선택 버튼 클릭 리스너 설정
        view.findViewById<Button>(R.id.select_date_button).setOnClickListener {
            showDatePickerDialog()
        }

        // 다음 버튼 클릭 리스너 설정
        view.findViewById<Button>(R.id.next_button).setOnClickListener {
            findNavController().navigate(R.id.action_applyInfoFragment_to_applySuccessFragment)
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // DatePickerDialog 생성
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                updateSelectedDate(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    private fun updateSelectedDate(date: Calendar) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd (E)", Locale.getDefault())
        val formattedDate = dateFormat.format(date.time)
        selectedDateText.text = "선택된 날짜: $formattedDate"
    }
}
