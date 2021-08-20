package com.nclassdev.foodicipi.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.databinding.ActivityMainBinding
import com.nclassdev.foodicipi.ui.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabBar()
    }

    private fun setUpTabBar(){

        binding.bottomNavBar.setOnItemSelectedListener {
            when(it){
                R.id.nav_home -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.mainFragment)
                }


                R.id. nav_favorite -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.favoriteFragment)
                }


            }
        }
    }


}