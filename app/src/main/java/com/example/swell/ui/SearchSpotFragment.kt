package com.example.swell.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swell.R
import com.example.swell.databinding.FragmentSearchSpotBinding

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

        // Changing actionbar title
        (activity as MainActivity).supportActionBar?.title = (getString(R.string.title_search_spot))

        val application = requireNotNull(this.activity).application
        //val viewModel = SearchSpotViewModel(application)

        binding.btnFragSearchSpotSearch.setOnClickListener {
            val spotName = binding.inputFragSearchSpotSpotName
            view?.findNavController()?.navigate(
                SearchSpotFragmentDirections.actionSearchSpotFragmentToCurrentSpotFragment(spotName.text.toString())
            )

        }

        return binding.root
    }


}
