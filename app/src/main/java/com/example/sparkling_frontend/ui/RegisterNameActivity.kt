package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.R

class RegisterNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_name)

        // Receiving the selectedRole from the intent
        val selectedRole = intent.getStringExtra("selectedRole")
        val username = findViewById<EditText>(R.id.username)

        val buttonGoToRegister = findViewById<Button>(R.id.goToRegister)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        // "다음" 버튼 클릭 리스너 설정
        buttonGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("selectedRole", selectedRole)
            intent.putExtra("username", username.text.toString())
            startActivity(intent)
        }

        backArrow.setOnClickListener {
            finish()
        }
    }
}