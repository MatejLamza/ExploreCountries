package com.example.exploretheworld.flow.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.exploretheworld.R
import com.example.exploretheworld.common.mvvm.BaseFragment
import com.example.exploretheworld.common.state.observe
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.data.models.ListCountry
import com.example.exploretheworld.flow.countries.pager.CountriesPagerFragmentArgs
import com.example.exploretheworld.utils.extensions.withDivider
import kotlinx.android.synthetic.main.fragment_countries.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesFragment : BaseFragment() {

    private val countriesViewModel: CountriesViewModel by viewModel()
    private val countriesAdapter: CountryAdapter by lazy {
        CountryAdapter()
    }
    private var listOfCountries: List<Country> = emptyList()

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
            //Todo redirect to fragment country details
            findNavController().navigate(
                R.id.action_countriesFragment_to_countriesPagerFragment,
                CountriesPagerFragmentArgs(
                    ListCountry(listOfCountries),
                    listOfCountries.indexOf(country)
                ).toBundle()
            )
        }
        countriesList.withDivider()
        countriesList.adapter = countriesAdapter
    }

    private fun bind() {
        countriesViewModel.countries.observe(viewLifecycleOwner) { countries ->
            countriesAdapter.countries = countries
            listOfCountries = countries
        }
        countriesViewModel.state.observe(viewLifecycleOwner, this)
    }

    override fun showLoading() {
        loadingCountries.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loadingCountries.visibility = View.GONE
    }
}