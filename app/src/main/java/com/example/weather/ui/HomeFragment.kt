package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weather.R
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.repository.WeatherRepository
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelProviderFactory


class HomeFragment : Fragment() {


    val repository = WeatherRepository()
    val viewModelProviderFactory = WeatherViewModelProviderFactory(repository)

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this , viewModelProviderFactory ).get(WeatherViewModel::class.java)
    }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.tempRv.adapter= WeatherAdapter()
        return binding.root


}

}
