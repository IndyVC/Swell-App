package com.example.swell.ui


import android.content.Context.LOCATION_SERVICE
import android.content.Intent
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
import androidx.lifecycle.ViewModelProviders
import com.example.swell.R
import com.example.swell.databinding.FragmentSessionBinding
import com.example.swell.viewmodels.SessionViewModel
import com.example.swell.viewmodels.SessionViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class SessionFragment : Fragment(), LocationListener {

    lateinit var binding: FragmentSessionBinding
    lateinit var viewModel: SessionViewModel

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

        // Creating ViewModel
        val application = requireNotNull(activity).application
        val viewModelFactory = SessionViewModelFactory(application = application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SessionViewModel::class.java)

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

        // Stop session
        binding.btnFragSessionStopSession.setOnClickListener {
            viewModel.stopListening()
            val intent: Intent = Intent(context, MapsActivity::class.java)
            intent.putExtra("locations", viewModel.locations)
            startActivity(intent)
        }


        return binding.root
    }

    override fun onLocationChanged(location: Location?) {
        if (viewModel.listening) {
            binding.lblFragSessionLatitude.text = location?.latitude.toString()
            binding.lblFragSessionLongitude.text = location?.longitude.toString()
            viewModel.locations.add(location)
        } else {
            binding.lblFragSessionListening.text = "Stopped listening"
        }
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

