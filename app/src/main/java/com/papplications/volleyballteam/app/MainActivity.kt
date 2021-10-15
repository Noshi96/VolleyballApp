package com.papplications.volleyballteam.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithNavController(findNavController(R.id.nav_main))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}