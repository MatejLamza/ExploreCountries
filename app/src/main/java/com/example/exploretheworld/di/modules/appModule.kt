package com.example.exploretheworld.di.modules

import android.content.Context
import androidx.room.Room
import com.example.exploretheworld.BuildConfig
import com.example.exploretheworld.data.local.database.ExploreTheWorldDAO
import com.example.exploretheworld.data.local.database.ExploreTheWorldDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DB_NAME = "ExploreTheWorldDatabase"

val appModule = module {

    fun provideExploreTheWorldDatabase(context: Context): ExploreTheWorldDatabase {
        return Room.databaseBuilder(
            context,
            ExploreTheWorldDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }

    fun provideExploreTheWorldDAO(database: ExploreTheWorldDatabase): ExploreTheWorldDAO {
        return database.getExploreTheWorldDAO()
    }

    single { provideExploreTheWorldDAO(database = provideExploreTheWorldDatabase(androidApplication())) }
    single {
        androidContext().getSharedPreferences(BuildConfig.PREFERENCES, Context.MODE_PRIVATE)
    }
}