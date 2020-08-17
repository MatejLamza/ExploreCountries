package com.example.exploretheworld.flow.cities

import android.content.Context
import android.view.View
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.exploretheworld.R
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.item_city.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CitiyItemView @JvmOverloads constructor(context: Context) : MaterialCardView(context) {

    init {
        View.inflate(context, R.layout.item_city, this)
    }

    @ModelProp
    fun cityName(name: String) {
        cityName.text = name
    }

    @ModelProp
    fun countryName(nameCountry: String) {
        country.text = nameCountry
    }

    @ModelProp
    fun cityPopulation(population: Long) {
        cityPopulation.text = population.toString()
    }
}