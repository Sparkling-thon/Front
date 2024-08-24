package com.example.sparkling_frontend.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sparkling_frontend.R

class FinishRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_register)

        // Handler를 사용하여 1초(1000밀리초) 뒤에 LoginActivity를 시작합니다.
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // 현재 액티비티를 종료하여 뒤로가기 시 이 액티비티로 돌아오지 않도록 합니다.
        }, 1000) // 1000 밀리초 = 1초
    }
}