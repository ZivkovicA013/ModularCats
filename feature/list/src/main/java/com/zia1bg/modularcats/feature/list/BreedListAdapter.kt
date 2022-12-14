package com.zia1bg.modularcats.feature.list

import com.zia1bg.modularcats.core.data.model.Breed
import com.zia1bg.modularcats.feature.list.databinding.ListItemBinding


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView



class BreedListAdapter() :
    ListAdapter<Breed, BreedListAdapter.MyViewHolder>(StartDiffCallback()) {

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ListItemBinding.inflate
                (LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBreed = getItem(position)

        holder.binding.apply {
            tvBreed.text = currentBreed.breed
            tvCoat.text = currentBreed.coat
            tvCountry.text = currentBreed.country
            tvOrigin.text = currentBreed.origin
            tvPattern.text = currentBreed.pattern

        }
    }


}

private class StartDiffCallback : DiffUtil.ItemCallback<Breed>() {
    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem.breed == newItem.breed
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return oldItem == newItem
    }
}

