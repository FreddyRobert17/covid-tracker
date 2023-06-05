package com.app.covidtracker.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.covidtracker.R
import com.app.covidtracker.data.model.CovidDailyData
import com.app.covidtracker.databinding.CovidListItemBinding
import com.app.covidtracker.util.Constants
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class CovidListAdapter: ListAdapter<CovidDailyData, CovidListAdapter.CovidViewHolder>(DiffCallback) {

    lateinit var onLikeClickListener:  (CovidDailyData) -> Unit

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
            binding.tvDate.text = formatDate(covidDailyData.date.toString())
            binding.tvPositives.text = formatNumber(covidDailyData.positives)
            binding.tvNegatives.text = formatNumber(covidDailyData.negatives)
            binding.tvHospitalized.text = formatNumber(covidDailyData.hospitalized)
            binding.tvDeath.text = formatNumber(covidDailyData.death)

            updateLikeIconState(covidDailyData)

            setOnLikeClickListener(covidDailyData)
        }

        private fun setOnLikeClickListener(covidDailyData: CovidDailyData){
            binding.ivHeart.setOnClickListener {
                if(::onLikeClickListener.isInitialized){
                    onLikeClickListener(covidDailyData)
                } else {
                    Log.e(CovidListAdapter::class.simpleName, "OnItemClickListener was not initialized")
                    return@setOnClickListener
                }

                updateLikeIconState(covidDailyData)
            }
        }

        private fun updateLikeIconState(covidDailyData: CovidDailyData){
            if(covidDailyData.isFavorite) {
                binding.ivHeart.setImageResource(R.drawable.filled_heart_icon)
            } else {
                binding.ivHeart.setImageResource(R.drawable.empty_heart_icon)
            }
        }

        private fun formatNumber(number: Int): String{
            val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            return numberFormat.format(number)
        }

        private fun formatDate(rawDate: String): String{
            val inputDateFormat = SimpleDateFormat(Constants.INPUT_DATE_FORMAT, Locale.getDefault())
            val date = inputDateFormat.parse(rawDate)

            val outputDateFormat = SimpleDateFormat(Constants.OUTPUT_DATE_FORMAT, Locale.getDefault())
            return outputDateFormat.format(date)
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<CovidDailyData>(){
        override fun areItemsTheSame(oldItem: CovidDailyData, newItem: CovidDailyData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CovidDailyData, newItem: CovidDailyData): Boolean {
            return oldItem == newItem
        }
    }
}