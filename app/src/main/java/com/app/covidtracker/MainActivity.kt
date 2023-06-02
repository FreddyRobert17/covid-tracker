package com.app.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.covidtracker.databinding.ActivityMainBinding
import com.app.covidtracker.viewmodel.CovidViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val viewmodel: CovidViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation(binding)

        viewmodel.covidModel.observe(this, Observer {
            Log.i("TAG", it.toString())
        })
    }

    private fun setupNavigation(binding: ActivityMainBinding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_items_list -> {
                    navController.navigate(R.id.listFragment)
                    true
                }

                R.id.action_favorites_list -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }

                else -> false
            }
        }
    }
}