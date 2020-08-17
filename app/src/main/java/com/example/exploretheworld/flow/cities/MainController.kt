package com.example.exploretheworld.flow.cities

import com.airbnb.epoxy.TypedEpoxyController
import com.example.exploretheworld.data.models.City

class MainController : TypedEpoxyController<List<City>>() {

    private var onCityClickedListener: ((city: City) -> Unit)? = null

    override fun buildModels(data: List<City>?) {
        data?.forEach {
            citiyItemView {
                id(it.id)
                countryName(it.country)
                cityName(it.city)
                cityPopulation(it.population)
                onBind { _, view, _ ->
                    view.setOnClickListener { _ ->
                        onCityClickedListener?.invoke(it)
                    }
                }
            }
        }
    }

    fun setOnCitiyClickListener(listener: (city: City) -> Unit) {
        onCityClickedListener = listener
    }
}