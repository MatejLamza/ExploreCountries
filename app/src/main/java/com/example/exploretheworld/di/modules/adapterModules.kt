package com.example.exploretheworld.di.modules

import androidx.fragment.app.Fragment
import com.example.exploretheworld.flow.countries.pager.CountriesPagerAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { (parent: Fragment) -> CountriesPagerAdapter(parent) }
}