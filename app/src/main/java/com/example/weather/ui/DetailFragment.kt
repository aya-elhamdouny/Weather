package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.weather.R
import com.example.weather.databinding.TempDetailsBinding

class DetailFragment : Fragment(R.layout.temp_details) {


private lateinit var binding : TempDetailsBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.temp_details, container, false)
        binding.setLifecycleOwner(this)


        return binding.root


    }


}