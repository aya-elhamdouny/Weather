package com.example.weather.ui
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.weather.App
import com.example.weather.R
import com.example.weather.TrackingUtils
import com.example.weather.adapters.WeatherAdapter
import com.example.weather.database.WeatherDatabase
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.model.Postion
import com.example.weather.repository.WeatherRepository
import com.example.weather.viewmodel.LocationViewModel
import com.example.weather.viewmodel.LocationViewModelProviderFactory
import com.example.weather.viewmodel.WeatherViewModel
import com.example.weather.viewmodel.WeatherViewModelProviderFactory
import com.google.android.gms.location.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber
import timber.log.Timber.d
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


const val LOCATION_REQUEST = 0
@SuppressLint("StaticFieldLeak")
lateinit var fusedLocationProviderClient: FusedLocationProviderClient
class HomeFragment : Fragment()  , EasyPermissions.PermissionCallbacks{


    var _lat: Double =0.0
    var _long: Double=0.0


    val repository = WeatherRepository(WeatherDatabase.getDatabase(App.app) , App.app)
    val weatherviewModelProviderFactory = WeatherViewModelProviderFactory(repository)
    val locationViewModelProviderFactory = LocationViewModelProviderFactory(App())


    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, weatherviewModelProviderFactory).get(WeatherViewModel::class.java)
    }

    private val locationViewModel: LocationViewModel by lazy {
        ViewModelProvider(this, locationViewModelProviderFactory).get(LocationViewModel::class.java)
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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(  requireContext())
        getLastLocation()



        return binding.root
    }

    fun getLastLocation(){
        if(checkPermission()){
            if(isLocationEnabled){
                fusedLocationProviderClient.lastLocation.addOnSuccessListener{
                    val lat = it?.latitude
                    val long = it?.longitude
                    Timber.d("lat from getlast %s" , lat)
                    Timber.d("long from getlast %s" , long)
                 /*   if (lat != null) {
                        if (long != null) {
                            setupLocation(it)
                            getCountryname(it.latitude, it.latitude)
                        }
                    }*/
                    if(it?.latitude == null && it?.longitude==null){
                        Timber.d("no location data")
                        requestNewLocationData()
                    }else{
                        Timber.d(" location data fetched ")
                        setupLocation(it)
                        getCountryname(it.latitude, it.latitude)
                    }
                }
            }
            else{
                   requestPermissions()
            }
        }
        else{
            requestPermissions()
        }
    }





    fun checkPermission() : Boolean{
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    }
    private val isLocationEnabled: Boolean
        get() {
            val locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        }
    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    private val locationCallback : LocationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
            p0?: return
            // return the last saved location
            val lat = p0.lastLocation.latitude
            val long = p0.lastLocation.longitude

            setupLocation(p0.lastLocation)
        }
    }

    private fun setupLocation(location: Location) {
        Postion(
            latitude = location.latitude,
            longitude = location.longitude
        )

        val country = getCountryname(location.latitude , location.longitude)
        App.lat= location.latitude
        geoLocatoin.lat = location.latitude
        geoLocatoin.long=location.longitude
        App.long = location.longitude
        Timber.d("App lat and lond : %s",geoLocatoin.long )
        Timber.d("ciuntry : %s ", country)
        Timber.d("lat %s" , location.latitude )
        Timber.d("long %s" , location.longitude )
    }


    fun getCountryname(lat : Double , long : Double ) : String {
        var geocoder: Geocoder
        var addressList = ArrayList<Address>()
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        addressList = geocoder.getFromLocation(lat , long, 1) as ArrayList<Address>
        return    addressList.get(0).adminArea
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

    }

    @Suppress("DEPRECATION")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }









}
