package com.example.swell


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.swell.databinding.FragmentCurrentLocationBinding

/**
 * A simple [Fragment] subclass.
 */
class CurrentLocation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCurrentLocationBinding>(inflater,
            R.layout.fragment_current_location,container,false)

        return binding.root
    }


}
