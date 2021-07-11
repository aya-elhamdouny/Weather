package com.example.weather.ui
import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.weather.App
import com.example.weather.R
import com.example.weather.TrackingUtils
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.database.WeatherDatabase
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.repository.WeatherRepository
import com.example.weather.viewmodel.LocationViewModel
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelProviderFactory
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


const val LOCATION_REQUEST = 0

class HomeFragment : Fragment()  , EasyPermissions.PermissionCallbacks{


    val repository = WeatherRepository(WeatherDatabase.getDatabase(App.app))
    val weatherviewModelProviderFactory = WeatherViewModelProviderFactory(repository)

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, weatherviewModelProviderFactory).get(WeatherViewModel::class.java)
    }



    private lateinit var binding: FragmentHomeBinding
    lateinit var adapter: WeatherAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        adapter = WeatherAdapter()
        binding.tempRv.adapter = adapter

        requestPermissions()
        viewModel.hour.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.viewModel = viewModel

        binding.nextdaysBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNextDaysFragment())
        }
        return binding.root
    }


    private fun requestPermissions() {
        if(TrackingUtils.hasLocationPermissions(requireContext())) {
            return
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                LOCATION_REQUEST,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                LOCATION_REQUEST,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            context?.let {
                EasyPermissions.hasPermissions(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            }
            context?.let {
                EasyPermissions.hasPermissions(
                    it,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    Manifest.permission.FOREGROUND_SERVICE
                )
            }
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(context, "Location allowed" , Toast.LENGTH_SHORT).show()
        setupLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }



    private fun setupLocation(){


    }
}