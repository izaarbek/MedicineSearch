package com.example.medicinesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val navController by lazy { Navigation.findNavController(this,R.id.nav_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNav(navController)
    }

    private fun setUpBottomNav(navController: NavController){
        bottom_nav_view?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }
}