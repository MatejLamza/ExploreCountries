package com.example.exploretheworld.di.modules

import com.example.exploretheworld.ExploreTheWorldApplication
import com.example.exploretheworld.flow.cities.CitiesViewModel
import com.example.exploretheworld.flow.countries.CountriesViewModel
import com.example.exploretheworld.flow.login.LoginViewModel
import com.example.exploretheworld.flow.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { CountriesViewModel(repository = get()) }
    viewModel { CitiesViewModel(repo = get()) }
    viewModel { LoginViewModel(userRepo = get()) }
    viewModel {
        SplashViewModel(
            dataRepository = get(),
            exploreTheWorldApp = androidApplication() as ExploreTheWorldApplication
        )
    }
}