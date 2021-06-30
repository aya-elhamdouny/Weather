package com.example.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.Forecastday.adapters.NextDayAdapter
import com.example.weather.R
import com.example.weather.databinding.TempDetailsBinding
import com.example.weather.viewmodel.DetailViewModel
import com.example.weather.viewmodel.DetailViewModelProviderFactory
import com.example.weather.viewmodel.NextDaysViewModel
import com.example.weather.viewmodel.NextDaysViewModelProviderFactory

class DetailFragment : Fragment(R.layout.temp_details) {


private lateinit var binding : TempDetailsBinding

    lateinit var adapter: NextDayAdapter




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.temp_details, container, false)

        val forecastday = DetailFragmentArgs.fromBundle(requireArguments()).forecastday

        val detailViewModelProviderFactory = DetailViewModelProviderFactory(forecastday , application)

        binding.viewModel = ViewModelProvider(
            this, detailViewModelProviderFactory).get(DetailViewModel::class.java)


        binding.bkBtn.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToNextDaysFragment())
        }

        binding.setLifecycleOwner(this)
        return binding.root


    }


}