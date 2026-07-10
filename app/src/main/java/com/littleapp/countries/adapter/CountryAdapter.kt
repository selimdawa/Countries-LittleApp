package com.littleapp.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.littleapp.countries.databinding.ItemCountryBinding
import com.littleapp.countries.fragments.DashboardFragmentDirections
import com.littleapp.countries.model.Country
import com.littleapp.countries.utils.VOID.downloadFromUrl
import com.littleapp.countries.utils.VOID.placeholderProgressBar

class CountryAdapter : ListAdapter<Country, CountryAdapter.ViewHolder>(CountryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        val binding = holder.binding

        binding.cName.text = model.countryName
        binding.dName.text = model.countryRegion

        binding.item.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToDetailFragment(model.uuid)
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageName.downloadFromUrl(
            false, model.imageURL, placeholderProgressBar(binding.item.context)
        )

        binding.imageBlur.downloadFromUrl(
            true, model.imageURL, placeholderProgressBar(binding.item.context)
        )
    }

    class ViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}