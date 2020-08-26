package com.example.exploretheworld.flow.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.exploretheworld.R
import com.example.exploretheworld.common.mvvm.BaseFragment
import com.example.exploretheworld.common.state.observe
import com.example.exploretheworld.utils.extensions.withDivider
import kotlinx.android.synthetic.main.fragment_top_ten_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Top10CitiesFragment : BaseFragment() {

    private val citiesViewModel: CitiesViewModel by viewModel()
    private val cityAdapter: Top10CitiesAdapter by lazy {
        Top10CitiesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_ten_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRecyclerView()
        bind()
    }

    private fun setupUI() {
        cityAdapter.onCityClicked = { city ->
            findNavController().navigate(
                R.id.action_top10CitiesFragment_to_cityDetailsFragment,
                CityDetailsFragmentArgs(city).toBundle()
            )
        }
    }

    private fun setupRecyclerView() {
        top10CitiesList.withDivider()
        top10CitiesList.adapter = cityAdapter
    }

    private fun bind() {
        citiesViewModel.top10Cities.observe(viewLifecycleOwner) { cities ->
            cityAdapter.cities = cities
        }
        citiesViewModel.state.observe(viewLifecycleOwner, this)
    }

    override fun showLoading() {
        Toast.makeText(requireContext(), "Fetching cities please wait", Toast.LENGTH_SHORT).show()
        loadingCities.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loadingCities.visibility = View.GONE
    }
}