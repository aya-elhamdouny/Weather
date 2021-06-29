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
import com.example.weather.databinding.FragmentNextDaysBinding
import com.example.weather.repository.WeatherRepository
import com.example.weather.viewmodel.NextDaysViewModel
import com.example.weather.viewmodel.NextDaysViewModelProviderFactory
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelProviderFactory


class NextDaysFragment : Fragment() {

    val repository = WeatherRepository()

    private lateinit var binding: FragmentNextDaysBinding

    val nextDaysViewModelProviderFactory = NextDaysViewModelProviderFactory(repository)

    private val viewModel: NextDaysViewModel by lazy {
        ViewModelProvider(this , nextDaysViewModelProviderFactory ).get(NextDaysViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_next_days, container, false)
        binding.lifecycleOwner = this

        binding.nextdaysRv.adapter = NextDayAdapter(NextDayAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)})

        binding.bkBtn.setOnClickListener {
            findNavController().navigate(NextDaysFragmentDirections.actionNextDaysFragmentToHomeFragment())
        }


        return binding.root


    }


}