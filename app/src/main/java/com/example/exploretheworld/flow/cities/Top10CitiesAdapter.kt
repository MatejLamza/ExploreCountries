package com.example.exploretheworld.flow.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exploretheworld.R
import com.example.exploretheworld.data.models.City
import kotlinx.android.synthetic.main.item_city.view.*

class Top10CitiesAdapter : RecyclerView.Adapter<Top10CitiesAdapter.Top10CitiesViewHolder>() {

    var cities: List<City> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Top10CitiesViewHolder =
        Top10CitiesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        )

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: Top10CitiesViewHolder, position: Int) {
        holder.city = cities[position]
    }

    inner class Top10CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city: City? = null
            set(value) {
                field = value
                if (value != null) {
                    itemView.cityName.text = value.city
                    itemView.country.text = value.country
                    itemView.cityPopulation.text = value.city
                }
            }
    }
}