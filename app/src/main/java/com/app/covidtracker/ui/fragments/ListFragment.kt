package com.app.covidtracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.covidtracker.databinding.FragmentListBinding
import com.app.covidtracker.ui.adapter.CovidListAdapter
import com.app.covidtracker.util.CovidApiResponseStatus
import com.app.covidtracker.viewmodel.CovidViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewmodel: CovidViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = CovidListAdapter()
        binding.recyclerView.adapter = adapter

        viewmodel.listDailyData.observe(requireActivity()) { listCovidDailyData ->
            adapter.submitList(listCovidDailyData)
        }

        viewmodel.apiResponseStatus.observe(requireActivity()) { apiResponseStatus ->
            if (apiResponseStatus == CovidApiResponseStatus.LOADING) {
                binding.progressBar.visibility = View.VISIBLE
            } else if (apiResponseStatus == CovidApiResponseStatus.DONE) {
                binding.progressBar.visibility = View.GONE
            } else if (apiResponseStatus == CovidApiResponseStatus.ERROR) {
                binding.progressBar.visibility = View.GONE
            }
        }

        adapter.onLikeClickListener = { covidDailyData ->
            if(covidDailyData.isFavorite){
                viewmodel.updateDailyData(covidDailyData.apply {
                    isFavorite = false
                })
            } else {
                viewmodel.updateDailyData(covidDailyData.apply {
                    isFavorite = true
                })
            }
        }

        return binding.root
    }
}