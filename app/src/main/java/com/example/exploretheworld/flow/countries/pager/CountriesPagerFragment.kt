package com.example.exploretheworld.flow.countries.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.exploretheworld.R
import kotlinx.android.synthetic.main.fragment_countries_pager.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CountriesPagerFragment : Fragment() {

    private val args: CountriesPagerFragmentArgs by navArgs()
    private val pagerAdapter: CountriesPagerAdapter by inject { parametersOf(this) }

    private val changePageListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            pagerAdapter.createFragment(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        countriesPager.adapter = pagerAdapter
        pagerAdapter.countries = args.countries.countries
        countriesPager.registerOnPageChangeCallback(changePageListener)
        /**
         * Bug if position is less then 5
         */
        countriesPager.setCurrentItem(args.position, true)
    }
}