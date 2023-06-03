package com.app.covidtracker.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.covidtracker.R
import com.app.covidtracker.databinding.FragmentListBinding
import com.app.covidtracker.ui.adapter.CovidListAdapter
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

        val adapter = CovidListAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter

        viewmodel.covidModel.observe(requireActivity()) { listCovidDailyData ->
            adapter.submitList(listCovidDailyData)
        }

        adapter.onItemClickListener = {
            Toast.makeText(requireActivity(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}