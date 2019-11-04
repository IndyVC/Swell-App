package com.example.swell.ui

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.swell.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val bundle: Bundle? = intent.extras
        val locations: ArrayList<Location> = bundle?.get("locations") as ArrayList<Location>
        val points: ArrayList<LatLng> = ArrayList()
        val bounds = LatLngBounds.builder()
        locations.forEach {
            val point = LatLng(it.latitude, it.longitude)
            points.add(point)
            bounds.include(point)
        }

        val track: Polyline = mMap.addPolyline(
            PolylineOptions()
                .clickable(false)
                .addAll(points)
        )

        val boundary = bounds.build()
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(boundary, 10)
        mMap.moveCamera(cameraUpdate)
        Log.i("test", "test")
    }
}
