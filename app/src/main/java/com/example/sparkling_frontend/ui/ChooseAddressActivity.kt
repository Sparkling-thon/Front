package com.example.sparkling_frontend.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sparkling_frontend.R

class ChooseAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_address)

        val selectedRole = intent.getStringExtra("selectedRole")
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val backArrow = findViewById<ImageView>(R.id.backArrow)

        backArrow.setOnClickListener {
            finish()
        }

//        Toast.makeText(this@ChooseAddressActivity, "selectedRolde: ${selectedRole}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this@ChooseAddressActivity, "username: ${username}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this@ChooseAddressActivity, "email: ${email}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this@ChooseAddressActivity, "password: ${password}", Toast.LENGTH_SHORT).show()
    }
}