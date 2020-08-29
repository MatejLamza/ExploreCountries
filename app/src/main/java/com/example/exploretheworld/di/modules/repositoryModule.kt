package com.example.exploretheworld.di.modules

import com.example.exploretheworld.data.repositories.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(sharedPrefs = get()) }
}