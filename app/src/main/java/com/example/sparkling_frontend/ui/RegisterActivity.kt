package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sparkling_frontend.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val selectedRole = intent.getStringExtra("selectedRole")
        val username = intent.getStringExtra("username")

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        val buttonGoToRegisterAddress = findViewById<Button>(R.id.goToRegisterAddress)
        buttonGoToRegisterAddress.setOnClickListener {
            val intent = Intent(this, ChooseAddressActivity::class.java)
            intent.putExtra("selectedRole", selectedRole)
            intent.putExtra("username", username)
            intent.putExtra("email", email.text.toString())
            intent.putExtra("password", password.text.toString())
            startActivity(intent)
        }

        backArrow.setOnClickListener {
            finish()
        }
    }
}