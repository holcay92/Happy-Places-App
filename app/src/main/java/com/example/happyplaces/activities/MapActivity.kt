package com.example.happyplaces.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happyplaces.R
import com.example.happyplaces.databinding.ActivityMapBinding
import com.example.happyplaces.models.HappyPlaceModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var binding: ActivityMapBinding? = null
    private var mHappyPlaceDetails: HappyPlaceModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            mHappyPlaceDetails = intent.getParcelableExtra(MainActivity.EXTRA_PLACE_DETAILS)!!
        }

        if (mHappyPlaceDetails != null) {
            setSupportActionBar(binding?.toolbarMap)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = mHappyPlaceDetails?.title

            binding?.toolbarMap?.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            supportMapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        val position = LatLng(mHappyPlaceDetails?.latitude!!, mHappyPlaceDetails?.longitude!!)
        p0.addMarker(MarkerOptions().position(position).title(mHappyPlaceDetails?.location))
        // after marking the location, we need to zoom in to that location :D
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(position, 10f)
        // now we need to animate the camera to that location
        p0.animateCamera(newLatLngZoom)
    }


}