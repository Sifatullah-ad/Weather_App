package com.example.weatherapp.ui.weather_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.api.model.CityLists
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class WeatherDetailsFragment : Fragment(), OnMapReadyCallback {

    private var binding: FragmentWeatherDetailsBinding? = null
    private var map: GoogleMap? = null

    private var model: CityLists = CityLists()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentWeatherDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap()
        initView()
        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initView() {
        model = arguments?.getParcelable("cityListModel") ?: CityLists()

        binding?.cityName?.text = model.name
        binding?.weatherCondition?.text = model.weather.first().description
        binding?.humidity?.text = "Humidity: ${model.mainPart.humidity}"
        binding?.windSpeed?.text = "Wind Speed: ${model.windInfo.speed}"
        val calculateMaxTemp = (model.mainPart.tempMax - 273.15).toInt()
        binding?.maxTemp?.text = "Max. Temp: $calculateMaxTemp ℃"
        val calculateMinTemp = (model.mainPart.tempMin - 273.15).toInt()
        binding?.minTemp?.text = "Min. Temp: $calculateMinTemp ℃"
        val calculateTemp = (model.mainPart.temp - 273.15).toInt()
        binding?.temperature?.text = "$calculateTemp ℃"
    }

    private fun initMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        map?.let { mMap ->
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(model.coord.lat, model.coord.lon), 7f))
            with(mMap.uiSettings) {
                isZoomControlsEnabled = false
                isMyLocationButtonEnabled = true
                isMapToolbarEnabled = false
                setAllGesturesEnabled(true)
                isCompassEnabled = true
            }
            with(mMap) {
                isTrafficEnabled = false
                isIndoorEnabled = false
                isBuildingsEnabled = true
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}