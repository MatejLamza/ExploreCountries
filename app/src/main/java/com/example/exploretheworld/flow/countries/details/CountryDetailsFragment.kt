package com.example.exploretheworld.flow.countries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.exploretheworld.R
import com.example.exploretheworld.data.models.Country
import kotlinx.android.synthetic.main.fragment_country_details.*

class CountryDetailsFragment : Fragment() {

    private val args: CountryDetailsFragmentArgs by navArgs()
    private val country: Country by lazy {
        args.country
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(view)
        displayMap()
    }

    private fun displayMap() {
        val bundle = Bundle()
        bundle.apply {
            putParcelable(FRAGMENT_MAP_KEY, args.country)
        }
        childFragmentManager.beginTransaction()
            .replace(R.id.mapFragment, FragmentMap.create(bundle)).commit()
    }

    private fun setupUI(view: View) {
        countryNameDetails.text = country.name
        setImage(country.flag, view)
        populationDetails.setText(getString(R.string.common_population, country.population))
        descriptionDetails.text = country.description
        languageDetails.text = setLanguage(country.language)
    }

    private fun setImage(image: String, view: View) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder_flag_background)
            .error(R.drawable.error_icon_background)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
        Glide.with(view).load(image).apply(requestOptions).into(flagCountryDetails)
    }

    private fun setLanguage(langagues: List<String>): String {
        var language = ""
        if (langagues.size > 1) {
            language += "Languages: "
            langagues.forEach { lang ->
                language += "$lang "
            }
        } else {
            language += "Language: ${langagues.firstOrNull()}"
        }
        return language
    }
}