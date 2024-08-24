package com.example.sparkling_frontend.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sparkling_frontend.R
import com.example.sparkling_frontend.model.ReservationItem

class DetailFragmentManage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fragment_manage)

        // DetailFragment 로드
        if (savedInstanceState == null) {
            val item = intent.getParcelableExtra<ReservationItem>("reservation_item")

            if (item != null) {
                val detailFragment = DetailFragment.newInstance(item)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .commit()
            } else {
                // Null일 경우 처리 (예: 오류 메시지 표시 또는 기본 동작 수행)
                // finish() 등을 사용해 Activity를 종료하거나 적절한 대처를 할 수 있습니다.
                finish()
            }
        }
    }
}