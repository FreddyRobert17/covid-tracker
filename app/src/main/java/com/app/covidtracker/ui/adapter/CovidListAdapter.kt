package com.app.covidtracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.covidtracker.data.network.CovidDailyData
import com.app.covidtracker.databinding.CovidListItemBinding

class CovidListAdapter: ListAdapter<CovidDailyData, CovidListAdapter.CovidViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val binding = CovidListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CovidViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        val covidDailyData = getItem(position)
        holder.bind(covidDailyData)
    }

    inner class CovidViewHolder(private val binding: CovidListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(covidDailyData: CovidDailyData){
            binding.tvDate.text = covidDailyData.date.toString()
            binding.tvPositives.text = covidDailyData.positives.toString()
            binding.tvNegatives.text = covidDailyData.negatives.toString()
            binding.tvHospitalized.text = covidDailyData.hospitalized.toString()
            binding.tvDeath.text = covidDailyData.death.toString()
        }

        private fun formatDate(){

        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<CovidDailyData>(){
        override fun areItemsTheSame(oldItem: CovidDailyData, newItem: CovidDailyData): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: CovidDailyData, newItem: CovidDailyData): Boolean {
            return oldItem == newItem
        }
    }
}