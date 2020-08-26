package com.example.exploretheworld.flow.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.exploretheworld.R
import com.example.exploretheworld.common.mvvm.BaseFragment
import com.example.exploretheworld.flow.countries.details.FRAGMENT_CITY_MAP
import com.example.exploretheworld.flow.countries.details.FragmentMap
import kotlinx.android.synthetic.main.fragment_country_details.*

class CityDetailsFragment : BaseFragment() {

    private val args: CityDetailsFragmentArgs by navArgs()

    companion object {
        fun create(args: Bundle): CityDetailsFragment {
            val fragment = CityDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        displayMap()
    }

    private fun displayMap() {
        val bundle = Bundle()
        bundle.apply {
            putParcelable(FRAGMENT_CITY_MAP, args.currentCity)
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.mapFragment, FragmentMap.create(bundle)).commit()
    }

    private fun setupUI() {
        countryNameDetails.text = args.currentCity.city
        populationDetails.text = args.currentCity.population.toString()
        descriptionDetails.text = args.currentCity.description
    }
}