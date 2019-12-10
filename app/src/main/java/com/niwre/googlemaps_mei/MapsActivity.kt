package com.niwre.googlemaps_mei

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val permission = arrayOf<String>(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val permissionCode = 1100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun verifyPermission(){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    permission[0]
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    permission[1]
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                val uty = LatLng(-7.747033, 110.355398)
                val zoomSize = 16.0f
                mMap.addMarker(MarkerOptions().position(uty).title("UTY Kampusku"))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uty, zoomSize))
                mMap.isMyLocationEnabled = true

            } else {
                ActivityCompat.requestPermissions(this, permission, permissionCode)
            }
        } else {
            val uty = LatLng(-7.747033, 110.355398)
            val zoomSize = 16.0f
            mMap.addMarker(MarkerOptions().position(uty).title("UTY Kampusku"))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(uty, zoomSize))
            mMap.isMyLocationEnabled = true
        }

    }

    private fun startFindLocation(){


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        verifyPermission()
    }
}

