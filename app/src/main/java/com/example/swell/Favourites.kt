package com.example.swell


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.swell.R
import com.example.swell.databinding.FragmentCurrentLocationBinding
import com.example.swell.databinding.FragmentFavouritesBinding

/**
 * A simple [Fragment] subclass.
 */
class Favourites : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentFavouritesBinding>(inflater,
            R.layout.fragment_favourites,container,false)
        return binding.root
    }


}
