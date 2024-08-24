package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.R

class ChooseRoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_role)

        val checkBoxServiceUser = findViewById<CheckBox>(R.id.chooseServiceUser)
        val checkBoxCollector = findViewById<CheckBox>(R.id.chooseCollector)
        val buttonGoToRegister = findViewById<Button>(R.id.goToRegister)

        // 초기 상태에서 버튼 비활성화
        buttonGoToRegister.isEnabled = false

        // CheckBox 리스너 설정
        checkBoxServiceUser.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxCollector.isChecked = false
            }
            updateButtonState(checkBoxServiceUser, checkBoxCollector, buttonGoToRegister)
        }

        checkBoxCollector.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxServiceUser.isChecked = false
            }
            updateButtonState(checkBoxServiceUser, checkBoxCollector, buttonGoToRegister)
        }

        // "다음" 버튼 클릭 리스너 설정
        buttonGoToRegister.setOnClickListener {
            val selectedRole = when {
                checkBoxServiceUser.isChecked -> "이용자"
                checkBoxCollector.isChecked -> "수거자"
                else -> null
            }

            if (selectedRole != null) {
                // RegisterActivity로 선택한 역할 전달
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("selectedRole", selectedRole)
                startActivity(intent)
            } else {
                Toast.makeText(this, "역할을 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 버튼 활성화/비활성화 업데이트 함수
    private fun updateButtonState(
        checkBoxServiceUser: CheckBox,
        checkBoxCollector: CheckBox,
        button: Button
    ) {
        button.isEnabled = checkBoxServiceUser.isChecked || checkBoxCollector.isChecked
    }
}