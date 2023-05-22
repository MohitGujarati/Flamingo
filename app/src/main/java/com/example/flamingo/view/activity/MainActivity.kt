package com.example.flamingo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flamingo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


//bottom navigation
        var bottomnav =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        var nav_container = findNavController(R.id.fragmentContainerView)
        bottomnav.setupWithNavController(nav_container)


        // Tabview
        // dialog
        // sql
        // api:Done
        // activity fragment:Done
        // pixel perfect design:Done
        // material design:Done


    }
}