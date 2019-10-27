package com.example.swell.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.swell.R
import com.example.swell.databinding.FragmentSearchSpotBinding
import com.example.swell.viewmodels.SpotViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class SearchSpotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchSpotBinding>(
            inflater,
            R.layout.fragment_search_spot,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val viewModel = SpotViewModel(application)

        binding.btnFragSearchSpotSearch.setOnClickListener {
            val spotName = binding.inputFragSearchSpotSpotName
            viewModel.retrieveSpot(spotName.text.toString())
        }

        viewModel.dePanne.observe(viewLifecycleOwner, Observer {
            val spots = viewModel.dePanne
            Timber.i("Stop")
        })

        viewModel.nazare.observe(viewLifecycleOwner, Observer {
            val spots = viewModel.nazare
            Timber.i("Stop")
        })
        return binding.root
    }


}
