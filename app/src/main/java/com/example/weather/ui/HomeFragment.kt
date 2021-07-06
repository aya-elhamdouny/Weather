package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.Forecastday.adapters.NextDayAdapter
import com.example.weather.App
import com.example.weather.R
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.database.WeatherDatabase
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.model.weather
import com.example.weather.repository.WeatherRepository
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelProviderFactory


class HomeFragment : Fragment() {


    val repository = WeatherRepository(WeatherDatabase.getDatabase(App.app))
    val weatherviewModelProviderFactory = WeatherViewModelProviderFactory(repository)

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this , weatherviewModelProviderFactory ).get(WeatherViewModel::class.java)
    }
    private lateinit var binding: FragmentHomeBinding
    lateinit var adapter: WeatherAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        adapter = WeatherAdapter()
        binding.tempRv.adapter = adapter

        viewModel.hour.observe(viewLifecycleOwner, Observer{
            adapter.submitList(it) })

        binding.viewModel = viewModel

        binding.nextdaysBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNextDaysFragment())
        }



        return binding.root


    }


}
