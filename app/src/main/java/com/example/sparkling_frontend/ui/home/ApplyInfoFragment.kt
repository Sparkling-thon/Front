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

    private lateinit var timeButtons: List<Button>
    private var selectedButton: Button? = null
    private lateinit var selectedDateText: TextView
    private lateinit var selectDateButton: Button
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.applyinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timeButtons = listOf(
            view.findViewById(R.id.time_button_one),
            view.findViewById(R.id.time_button_two),
            view.findViewById(R.id.time_button_three),
            view.findViewById(R.id.time_button_four),
            view.findViewById(R.id.time_button_five)
        )
        selectedDateText = view.findViewById(R.id.selected_date_text)
        selectDateButton = view.findViewById(R.id.select_date_button)
        nextButton = view.findViewById(R.id.next_button)

        selectDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_applyInfoFragment_to_applySuccessFragment)
        }

        timeButtons.forEach { button ->
            button.setOnClickListener {
                selectedButton?.isSelected = false
                button.isSelected = true
                selectedButton = button
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val date = Calendar.getInstance().apply {
                set(Calendar.YEAR, selectedYear)
                set(Calendar.MONTH, selectedMonth)
                set(Calendar.DAY_OF_MONTH, selectedDay)
            }
            val dateFormat = SimpleDateFormat("yyyy-MM-dd (E)", Locale.getDefault())
            selectedDateText.text = dateFormat.format(date.time)
        }, year, month, day)

        datePickerDialog.show()
    }
}
