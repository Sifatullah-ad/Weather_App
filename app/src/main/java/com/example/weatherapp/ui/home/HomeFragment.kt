package com.example.weatherapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.api.model.CityLists
import com.example.weatherapp.api.model.WeatherCityListsModel
import com.example.weatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding:FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    private val dataAdapter: HomeAdapter = HomeAdapter()

    private var dataList: MutableList<CityLists> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        fetchCityListWithWeather()

    }

    private fun initView() {
        with(binding?.recyclerView!!) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }
    }

    private fun fetchCityListWithWeather() {
        viewModel.getCityListsWithWeather().observe(viewLifecycleOwner, Observer { lists->
            dataList.clear()
            dataList.addAll(lists.list)
            Log.e("cityList", "$dataList , $lists")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}