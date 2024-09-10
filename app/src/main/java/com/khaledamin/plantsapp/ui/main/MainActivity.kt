package com.khaledamin.plantsapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).findNavController()

        appBarConfiguration = AppBarConfiguration.Builder(R.id.plantsFragment).build()

        setSupportActionBar(viewBinding.toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}