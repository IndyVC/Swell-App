package com.example.swell


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.swell.databinding.FragmentCurrentLocationBinding
import com.example.swell.databinding.FragmentTopLocationsBinding

/**
 * A simple [Fragment] subclass.
 */
class TopLocation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTopLocationsBinding>(inflater,
            R.layout.fragment_top_locations,container,false)
        return binding.root
    }


}
