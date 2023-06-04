package com.captsone.padicure.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.captsone.padicure.R
import com.captsone.padicure.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomAppBar: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navHostMainController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        bottomAppBar = binding.bottomAppBar
        setBottomAppBar()
    }
    private fun setBottomAppBar(){
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerHome) as NavHostFragment
        navHostMainController = navHostFragment.navController
        bottomAppBar.setOnItemSelectedListener { menuItem ->
            val destinationId = when (menuItem.itemId) {
                R.id.homeFragment -> R.id.homeFragment
                R.id.scanFragment -> R.id.scanFragment
                R.id.profileFragment -> R.id.profileFragment
                else -> return@setOnItemSelectedListener false
            }
            navHostMainController.navigate(destinationId)
            true
        }
    }
}