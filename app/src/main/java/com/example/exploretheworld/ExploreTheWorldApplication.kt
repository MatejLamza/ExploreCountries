package com.example.exploretheworld

import android.app.Application
import com.example.exploretheworld.di.ExploreTheWorldDI

class ExploreTheWorldApplication : Application() {
    private val exploreTheWorldDI: ExploreTheWorldDI by lazy { ExploreTheWorldDI(this) }

    override fun onCreate() {
        super.onCreate()
        exploreTheWorldDI.initialize()
    }
}