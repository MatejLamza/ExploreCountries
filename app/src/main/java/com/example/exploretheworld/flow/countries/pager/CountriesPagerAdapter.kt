package com.example.exploretheworld.flow.countries.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.exploretheworld.data.models.Country
import com.example.exploretheworld.flow.countries.details.CountryDetailsFragment
import com.example.exploretheworld.flow.countries.details.CountryDetailsFragmentArgs

class CountriesPagerAdapter(parent: Fragment) : FragmentStateAdapter(parent) {

    var countries: List<Country> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = countries.size

    override fun createFragment(position: Int): Fragment =
        CountryDetailsFragment.create(CountryDetailsFragmentArgs(countries[position]).toBundle())

}