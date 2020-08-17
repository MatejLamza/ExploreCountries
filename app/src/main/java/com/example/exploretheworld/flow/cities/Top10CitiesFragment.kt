package com.example.exploretheworld.flow.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.exploretheworld.R
import com.example.exploretheworld.utils.Mock
import kotlinx.android.synthetic.main.fragment_top_ten_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Top10CitiesFragment : Fragment() {

    private val mainController: MainController by lazy {
        MainController()
    }

    private val regionCityController: RegionCitiesController by lazy {
        RegionCitiesController()
    }

    private val citiesViewModel: CitiesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_ten_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        bind()


        setupUI()
    }

    private fun setupUI() {
        topTenCitiesEpoxy.setController(mainController)
        regionCityController.regions = Mock.regions
        regionCityController.cities = Mock.cities
    }

    private fun bind() {
        citiesViewModel.top10Cities.observe(viewLifecycleOwner) {
            regionCityController.cities = it
            it.forEach {
                Log.d("aaa", "Citiy: ${it.city} country: ${it.country} pop: ${it.population}")
            }
        }
    }
}