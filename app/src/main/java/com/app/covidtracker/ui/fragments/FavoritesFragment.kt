package com.app.covidtracker.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.covidtracker.R
import com.app.covidtracker.databinding.FragmentFavoritesBinding
import com.app.covidtracker.databinding.FragmentListBinding
import com.app.covidtracker.ui.adapter.CovidListAdapter
import com.app.covidtracker.viewmodel.CovidViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewmodel: CovidViewModel by viewModels()
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = CovidListAdapter()
        binding.recyclerView.adapter = adapter

        viewmodel.favoritesList.observe(requireActivity()) { covidDailyDataList ->
            adapter.submitList(covidDailyDataList)
        }

        viewmodel.getFavoriteDailyData()

        return binding.root
    }
}