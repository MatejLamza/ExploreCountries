package com.example.exploretheworld.di

import android.app.Application
import com.example.exploretheworld.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class ExploreTheWorldDI(private val application: Application) {
    private lateinit var koinApplication: KoinApplication
    private val modules: List<Module> = listOf(
        appModule,
        dataSourceModule,
        viewModelsModule,
        adapterModule,
        repositoryModule,
        networkModules
    )

    fun initialize() {
        koinApplication = startKoin {
            androidLogger(Level.INFO)
            androidContext(application)
            modules(modules)
        }
    }
}