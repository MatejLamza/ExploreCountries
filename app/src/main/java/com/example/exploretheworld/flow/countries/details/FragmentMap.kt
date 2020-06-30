package com.example.exploretheworld.flow.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exploretheworld.R
import com.example.exploretheworld.data.models.Country
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

const val FRAGMENT_MAP_KEY = "TempoKey"

class FragmentMap : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val country: Country by lazy {
        arguments?.get(FRAGMENT_MAP_KEY) as Country
    }

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
        val sydney = LatLng(country.latlng[0], country.latlng[1])
        mMap.addMarker(
            MarkerOptions().position(sydney).title(getString(R.string.common_marker, country.name))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}