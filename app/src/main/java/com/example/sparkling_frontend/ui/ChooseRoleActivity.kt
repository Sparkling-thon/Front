package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.R

class ChooseRoleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_role)

        val boxUser = findViewById<LinearLayout>(R.id.box_user)
        val boxCollector = findViewById<LinearLayout>(R.id.box_collector)

        // 박스 클릭 리스너 설정
        boxUser.setOnClickListener {
            navigateToRegisterNameActivity()
        }

        boxCollector.setOnClickListener {
            navigateToRegisterNameActivity()
        }
    }

    private fun navigateToRegisterNameActivity() {
        val intent = Intent(this, RegisterNameActivity::class.java)
        startActivity(intent)
    }
}
