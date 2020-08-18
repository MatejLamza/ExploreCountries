package com.example.exploretheworld.flow.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.exploretheworld.R
import com.example.exploretheworld.utils.extensions.withDivider
import kotlinx.android.synthetic.main.fragment_top_ten_cities.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Top10CitiesFragment : Fragment() {

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
        setupRecyclerView()
        bind()
    }

    private fun setupRecyclerView() {
        top10CitiesList.withDivider()
        top10CitiesList.adapter = cityAdapter
    }

    private fun bind() {
        citiesViewModel.top10Cities.observe(viewLifecycleOwner) { cities ->
            cityAdapter.cities = cities
        }
    }
}