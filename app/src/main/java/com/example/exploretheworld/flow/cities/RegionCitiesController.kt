package com.example.exploretheworld.flow.cities

import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.example.exploretheworld.data.models.City
import com.example.exploretheworld.data.models.Region

class RegionCitiesController : EpoxyController() {

    var regions: List<Region> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var cities: List<City> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        val modelRegions = regions.map {
            RegionEpoxyModel_()
                .id("sedam")
                .region(it)
        }

        carousel {
            id("regions")
            padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
            models(modelRegions)
        }

        cities.forEach {
            citiyItemView {
                id("temp")
                cityName(it.city)
                cityPopulation(it.population)
                countryName(it.country)
            }
        }
    }

}