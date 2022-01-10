package com.example.weatherapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.api.model.CityLists
import com.example.weatherapp.databinding.ItemViewCityListWithWeatherBinding

class HomeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var cityLists: MutableList<CityLists> = mutableListOf()

    var onItemClicked: ((model: CityLists) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemViewCityListWithWeatherBinding = ItemViewCityListWithWeatherBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewModel(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HomeAdapter.ViewModel) {

            val model = cityLists[position]
            val binding = holder.binding

            binding.cityName.text = model.name
            binding.weatherCondition.text = model.weather.first().description
            val calculateTemp = (model.mainPart.temp - 273.15).toInt()
            binding.temperature.text = "$calculateTemp ℃"

        }
    }

    override fun getItemCount(): Int {
        return cityLists.size
    }

    inner class ViewModel(val binding: ItemViewCityListWithWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.parent.setOnClickListener {
                onItemClicked?.invoke(cityLists[adapterPosition])
            }
        }

    }

    fun initLoad(list: List<CityLists>){
        cityLists.clear()
        cityLists.addAll(list)
        notifyDataSetChanged()
    }

}