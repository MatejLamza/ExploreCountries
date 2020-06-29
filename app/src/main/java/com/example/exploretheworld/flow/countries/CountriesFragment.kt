package com.example.exploretheworld.flow.countries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exploretheworld.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.observe
import com.example.exploretheworld.utils.extensions.withDivider
import kotlinx.android.synthetic.main.fragment_countries.*

class CountriesFragment : Fragment() {

    private val countriesViewModel: CountriesViewModel by viewModel()
    private val countriesAdapter: CountryAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bind()
    }

    private fun setupRecyclerView() {
        countriesAdapter.onCoutryClicked = { country ->
            Log.d("aaa", "Country clicked: ${country.name}")
        }
        countriesList.withDivider()
        countriesList.adapter = countriesAdapter
    }

    private fun bind() {
        countriesViewModel.countries.observe(viewLifecycleOwner) { countries ->
            countriesAdapter.countries = countries
        }
    }
}