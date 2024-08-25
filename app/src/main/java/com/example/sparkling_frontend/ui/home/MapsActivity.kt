package com.example.sparkling_frontend.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sparkling_frontend.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_collectionboxmap)

        // SupportMapFragment를 얻고, 지도가 준비되었을 때 알림을 받기 위해 콜백 설정
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 확대/축소 버튼을 활성화
        mMap.uiSettings.isZoomControlsEnabled = true

        // KAIST 위치 설정
        val kaistLatLng = LatLng(36.3726, 127.3606)

        // KAIST 위치에 기본 마커 추가
        mMap.addMarker(
            MarkerOptions()
                .position(kaistLatLng)
                .title("Marker at KAIST")
        )

        // 위치 권한이 허용된 경우 현재 위치로 이동
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableLocation()
        } else {
            // 위치 권한 요청
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun enableLocation() {
        try {
            mMap.isMyLocationEnabled = true
            mMap.setOnMyLocationChangeListener { location ->
                val currentLatLng = LatLng(location.latitude, location.longitude)

                // 현재 위치와 KAIST 위치를 포함하는 카메라 설정 (5km 범위)
                val bounds = LatLngBounds.Builder()
                    .include(currentLatLng)
                    .include(LatLng(36.3726, 127.3606))
                    .build()

                // 5km 범위의 초기 줌 레벨을 적용
                val padding = 100 // Padding in pixels
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    // 위치 권한 요청 결과 처리
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용되면 위치 기능 활성화
                enableLocation()
            }
        }
    }
}