package com.example.exploretheworld.flow.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exploretheworld.R
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Country
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

const val FRAGMENT_MAP_KEY = "TempoKey"
const val FRAGMENT_CITY_MAP = "com.example.exploretheworld.flow.countries.details_City_Map"

class FragmentMap : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    companion object {
        fun create(bundle: Bundle): FragmentMap {
            val frag = FragmentMap()
            frag.arguments = bundle
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val latLng = getLatLng()
        mMap.addMarker(
            MarkerOptions().position(latLng)
                .title(getString(R.string.common_marker, getMarkerName()))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

    private fun getMarkerName(): String {
        if (arguments?.get(FRAGMENT_MAP_KEY) != null) {
            val country = arguments?.get(FRAGMENT_MAP_KEY) as Country
            return country.name
        } else {
            val city = arguments?.get(FRAGMENT_CITY_MAP) as City
            return city.city
        }
    }

    /**
     * Because this is a shared Fragment we are checking where we came from so we can take city details or country details
     * depending on where we came from.
     */
    private fun getLatLng(): LatLng {
        return if (arguments?.get(FRAGMENT_MAP_KEY) != null) {
            val country = arguments?.get(FRAGMENT_MAP_KEY) as Country
            LatLng(country.latlng[0], country.latlng[1])
        } else {
            val city = arguments?.get(FRAGMENT_CITY_MAP) as City
            LatLng(city.latlng[0], city.latlng[1])
        }
    }
}