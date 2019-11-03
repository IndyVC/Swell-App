package com.example.swell.ui


import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swell.R
import com.example.swell.adapters.CurrentSpotListener
import com.example.swell.adapters.SpotAdapter
import com.example.swell.databinding.FragmentCurrentSpotBinding
import com.example.swell.util.ConvertSpot
import com.example.swell.viewmodels.CurrentSpotViewModel
import com.example.swell.viewmodels.CurrentSpotViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class CurrentSpotFragment : Fragment() {

    lateinit var application: Application
    lateinit var viewModel: CurrentSpotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCurrentSpotBinding>(
            inflater,
            R.layout.fragment_current_spot, container, false
        )

        // Changing actionbar title
        (activity as MainActivity).supportActionBar?.title =
            (getString(R.string.title_current_spot))

        // Creating viewModel and binding it
        application = requireNotNull(this.activity).application
        val viewModelFactory = CurrentSpotViewModelFactory(application)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CurrentSpotViewModel::class.java)
        binding.viewModel = viewModel

        // Creating recyclerview and binding it
        val adapater = SpotAdapter(CurrentSpotListener { spotId ->
            viewModel.setCurrentSpot(spotId)
        })
        binding.recyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview.adapter = adapater
        binding.lifecycleOwner = this

        // Observing changes
        viewModel.spots?.observe(viewLifecycleOwner, Observer {
            adapater.submitList(it)
            viewModel.setCurrentSpot(null)
            binding.viewModel = viewModel
        })

        // Checking if data needs to be refreshed (coming from 'search spot')
        if (arguments != null) {
            val args = CurrentSpotFragmentArgs.fromBundle(arguments!!)
            viewModel.retrieveSpot(args.spotName, true)
        } else {
            viewModel.retrieveSpot(null, false)
        }

        // Navigating
        binding.btnCurrentSpotStartSession.setOnClickListener {
            val spotName = ConvertSpot.idToName(viewModel.currentSpot.value?.magicSeaWeedSpotId)
            view?.findNavController()?.navigate(
                CurrentSpotFragmentDirections.actionCurrentSpotFragmentToSessionFragment(spotName)
            )
        }

        return binding.root
    }


}
