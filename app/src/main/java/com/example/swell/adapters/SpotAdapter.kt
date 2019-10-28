package com.example.swell.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swell.databinding.ListItemSpotBinding
import com.example.swell.domain.Spot

class SpotAdapter(
    val clickCurrentSpotListener: CurrentSpotListener
) : ListAdapter<Spot, SpotAdapter.ViewHolder>(SpotDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickCurrentSpotListener)
    }


    class ViewHolder private constructor(val binding: ListItemSpotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("RestrictedApi")
        fun bind(
            item: Spot,
            currentSpotListener: CurrentSpotListener
        ) {
            binding.currentSpotListener = currentSpotListener
            binding.spot = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSpotBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class SpotDiffCallback : DiffUtil.ItemCallback<Spot>() {
    override fun areItemsTheSame(oldItem: Spot, newItem: Spot): Boolean {
        return oldItem.spotId == newItem.spotId
    }

    override fun areContentsTheSame(oldItem: Spot, newItem: Spot): Boolean {
        return oldItem == newItem
    }
}

class CurrentSpotListener(val clickListener: (spotId: Long) -> Unit) {
    fun onClick(spot: Spot) = clickListener(spot.spotId)
}

