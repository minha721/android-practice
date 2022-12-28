package com.ssafy.googlemap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ssafy.googlemap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapFragment = supportFragmentManager.findFragmentById(R.id.frag_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val markers = mutableListOf<Marker>()
        markers.add(Marker(LatLng(37.5013068, 127.0385654), "역삼 멀티캠퍼스"))
        markers.add(Marker(LatLng(36.106328, 128.413983), "구미 캠퍼스"))
        markers.add(Marker(LatLng(36.106352, 128.404179), "이마트 동구미점"))
        markers.add(Marker(LatLng(36.107129, 128.417981), "메가박스 구미"))
        markers.add(Marker(LatLng(36.107971, 128.418511), "스타벅스 인동점"))

        markers.forEach { marker ->
            googleMap.addMarker(
                MarkerOptions()
                    .position(marker.pos)
                    .title(marker.title)
            )
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markers[1].pos, 15F))

        googleMap.setOnMarkerClickListener { marker ->
            Toast.makeText(this@MainActivity, "${marker.title} clicked!", Toast.LENGTH_SHORT).show()
            true
        }
    }
}