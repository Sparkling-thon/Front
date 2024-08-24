package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.api.RetrofitClient
import com.example.sparkling_frontend.model.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseAddressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_address)

        val selectedRole = intent.getStringExtra("selectedRole") ?: ""
        val username = intent.getStringExtra("username") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val password = intent.getStringExtra("password") ?: ""
        val address = "주소입니다."

        val doneRegister = findViewById<Button>(R.id.doneRegister)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        backArrow.setOnClickListener {
            finish()
        }

        doneRegister.setOnClickListener {

            // RegisterRequest 객체 생성
            val registerRequest = RegisterRequest(
                name = username,
                email = email,
                address = address,
                password = password,
                authType = selectedRole
            )

            // 회원가입 API 호출
            RetrofitClient.instance.register(registerRequest).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ChooseAddressActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        // TODO: 회원가입 성공 후 다음 화면으로 이동 처리
                        val intent = Intent(this@ChooseAddressActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@ChooseAddressActivity, "회원가입 실패: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@ChooseAddressActivity, "회원가입 실패: ${t.message}", Toast.LENGTH_SHORT).show()
                    Log.e("ChooseAddressActivity", "Error: ${t.message}")
                }
            })
        }

//        Toast.makeText(this@ChooseAddressActivity, "selectedRolde: ${selectedRole}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this@ChooseAddressActivity, "username: ${username}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this@ChooseAddressActivity, "email: ${email}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this@ChooseAddressActivity, "password: ${password}", Toast.LENGTH_SHORT).show()
    }
}