package com.example.exploretheworld.di.modules

import com.example.exploretheworld.flow.cities.CitiesViewModel
import com.example.exploretheworld.flow.countries.CountriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CountriesViewModel(repository = get()) }
    viewModel { CitiesViewModel(repo = get()) }
}