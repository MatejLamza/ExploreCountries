package com.example.exploretheworld.utils

import com.example.exploretheworld.data.models.Region

object Mock {

    val regions: List<Region> by lazy {
        listOf(
            Region(1, "Europe", ""),
            Region(2, "Asia", ""),
            Region(3, "North America", ""),
            Region(4, "South America", ""),
            Region(5, "Oceania", "")
        )
    }

//    val cities: List<City> by lazy {
//        listOf(
//            City(1, "Tokyo", "Japan", 173294, emptyList(), ""),
//            City(2, "Sao Paolo", "Brazil", 173294, emptyList(), ""),
//            City(3, "New York", "USA", 173294, emptyList(), ""),
//            City(4, "Mumbai", "India", 173294, emptyList(), ""),
//            City(5, "Instanbull", "Turkey", 173294, emptyList(), "")
//        )
//    }
}