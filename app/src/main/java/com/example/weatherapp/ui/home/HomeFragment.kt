package com.example.weatherapp.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.api.model.CityLists
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
        initClickLister()
        fetchCityListWithWeather()

    }

    private fun initView() {
        with(binding?.recyclerView!!) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dataAdapter
        }
    }


    private fun initClickLister() {

        dataAdapter.onItemClicked = { model->
            val bundle = bundleOf(
                "cityListModel" to model
            )
            findNavController().navigate(R.id.nav_detail_weather, bundle)
        }
    }


    private fun fetchCityListWithWeather() {
        viewModel.getCityListsWithWeather().observe(viewLifecycleOwner, Observer { lists->
            dataList.clear()
            dataList.addAll(lists.list)
            dataAdapter.initLoad(dataList)
            showLocalNotification("Weather App", "Current Temperature: 0℃", "")
        })
    }

    private fun showLocalNotification(title: String, body: String, theData: String) {
        val notificationManager: NotificationManager =
            activity?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannels(notificationManager)
        val builder = createNotification(
            "WeatherData",
            title, body, createLocalPendingIntent(theData)
        )

        notificationManager.notify(1011, builder.build())
    }

    private fun createLocalPendingIntent(theData: String): PendingIntent {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("data", theData)
        return PendingIntent.getActivity(
            requireContext(),
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun createNotification(channelId: String, title: String, body: String, pendingIntent: PendingIntent): NotificationCompat.Builder {
        return NotificationCompat.Builder(requireContext(), channelId).apply {
            setSmallIcon(R.drawable.ic_clouds)
            setContentTitle(title)
            setContentText(body)
            setAutoCancel(true)
            color = ContextCompat.getColor(requireContext(), R.color.color_primary)
            setDefaults(NotificationCompat.DEFAULT_ALL)
            priority = NotificationCompat.PRIORITY_DEFAULT
            setContentIntent(pendingIntent)
        }
    }

    private fun createNotificationChannels(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()

            val channelList: MutableList<NotificationChannel> = mutableListOf()
            val channel1 = NotificationChannel("WeatherData", "Instant Weather", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Current Temparature"
                setShowBadge(true)
                enableLights(true)
                lightColor = Color.GREEN
                enableVibration(true)
                setSound(soundUri, audioAttributes)
            }
            channelList.add(channel1)

            notificationManager.createNotificationChannels(channelList)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}