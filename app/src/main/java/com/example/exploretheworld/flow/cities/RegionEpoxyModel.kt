package com.example.exploretheworld.flow.cities

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.exploretheworld.R
import com.example.exploretheworld.data.models.Region

@EpoxyModelClass(layout = R.layout.view_region)
abstract class RegionEpoxyModel : EpoxyModelWithHolder<RegionEpoxyModel.Holder>() {

    @EpoxyAttribute
    lateinit var region: Region

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(region) {
            holder.regionName.text = region.regionName
        }
    }

    inner class Holder : EpoxyHolder() {
        lateinit var regionImage: ImageView
        lateinit var regionName: AppCompatTextView

        override fun bindView(itemView: View) {
            regionImage = itemView.findViewById(R.id.regionImage)
            regionName = itemView.findViewById(R.id.regionName)
        }

    }
}