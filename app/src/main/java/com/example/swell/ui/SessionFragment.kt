package com.example.swell.ui


import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.swell.R
import com.example.swell.databinding.FragmentSessionBinding

/**
 * A simple [Fragment] subclass.
 */
class SessionFragment : Fragment(), LocationListener {

    lateinit var binding: FragmentSessionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding
        binding = DataBindingUtil.inflate<FragmentSessionBinding>(
            inflater,
            R.layout.fragment_session,
            container,
            false
        )

        // Changing actionbar title
        (activity as MainActivity).supportActionBar?.title = (getString(R.string.title_search_spot))

        // Activate LocationListener
        try {
            val locationManager = context?.getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0, 5F, this
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

        // Binding spotname
        if (arguments != null) {
            val args = CurrentSpotFragmentArgs.fromBundle(arguments!!)
            binding.lblSpotName.text = args.spotName
        }

        return binding.root
    }

    override fun onLocationChanged(location: Location?) {
        binding.txtSessionFragmentAltitude.text = location?.altitude.toString()
        binding.txtSessionFragmentLongitude.text = location?.longitude.toString()
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        // DEPRECATED SINCE API level 29
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
        Toast.makeText(context, "Enable GPS please.", Toast.LENGTH_LONG).show()
    }

}

