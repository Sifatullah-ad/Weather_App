package com.example.weatherapp.ui.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null
    private var isTimeOut: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private var callback: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback = Runnable {
            isTimeOut = true
            goToDestination()
        }
        handler.postDelayed(callback!!,2000L)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentSplashScreenBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onResume() {
        super.onResume()
        if (isTimeOut){
            goToDestination()
        }
    }

    private fun goToDestination(){
        findNavController().navigate(R.id.nav_dashboard)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}