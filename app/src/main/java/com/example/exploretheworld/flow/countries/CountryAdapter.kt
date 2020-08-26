package com.example.exploretheworld.flow.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exploretheworld.R
import com.example.exploretheworld.common.GlideApp
import com.example.exploretheworld.data.models.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    var onCoutryClicked: ((currentCountry: Country) -> Unit)? = null

    var countries: List<Country> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.country = countries[position]
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var country: Country? = null
            set(value) {
                field = value
                if (value != null) {
                    itemView.countryName.text = value.name
                    itemView.countryCapital.text = value.capital
                    itemView.countryPopulation.text = value.population.toString()
                    GlideApp.with(itemView.context).load(value.flag).into(itemView.countryFlag)
                }
            }

        init {
            itemView.setOnClickListener {
                val currentCountry = countries[layoutPosition]
                onCoutryClicked?.invoke(currentCountry)
            }
        }
    }
}