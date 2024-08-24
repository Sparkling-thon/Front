package com.example.sparkling_frontend.ui

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sparkling_frontend.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    val username = findViewById<EditText>(R.id.username)
    val email = findViewById<EditText>(R.id.email)
    val password = findViewById<EditText>(R.id.password)
}