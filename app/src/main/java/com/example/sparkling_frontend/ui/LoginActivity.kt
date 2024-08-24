package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.MainActivity
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.api.RetrofitClient
import com.example.sparkling_frontend.model.LoginRequest
import com.example.sparkling_frontend.model.LoginResponse
import com.example.sparkling_frontend.util.PreferenceManager
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.loginId)
        val passwordEditText = findViewById<EditText>(R.id.loginPassword)
        val loginButton = findViewById<Button>(R.id.logIn)
        val backArrow = findViewById<ImageView>(R.id.backArrow)

        backArrow.setOnClickListener {
            finish()
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "이메일과 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        // 로그인 성공 처리
                        Toast.makeText(this@LoginActivity, "로그인 성공: ${it.authType}", Toast.LENGTH_SHORT).show()

                        // 토큰 저장
                        it.token.let { token ->
                            PreferenceManager.saveAuthToken(this@LoginActivity, token)
                        }
                        it.authType.let { authType ->
                            PreferenceManager.saveAuthType(this@LoginActivity, authType)
                        }

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    // 로그인 실패 처리
                    Toast.makeText(this@LoginActivity, "로그인 실패: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // 네트워크 오류 처리
                Toast.makeText(this@LoginActivity, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}