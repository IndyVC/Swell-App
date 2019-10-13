package com.example.swell


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.swell.databinding.FragmentCurrentLocationBinding
import com.example.swell.databinding.FragmentSearchSpotBinding

/**
 * A simple [Fragment] subclass.
 */
class SearchSpot : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSearchSpotBinding>(inflater,
            R.layout.fragment_search_spot,container,false)

        binding.btnSearchspotButton.setOnClickListener {
            showToast()
        }

        return binding.root
    }

    fun showToast(){
        Toast.makeText(activity, "Clicked search icon",Toast.LENGTH_SHORT).show()
    }


}
