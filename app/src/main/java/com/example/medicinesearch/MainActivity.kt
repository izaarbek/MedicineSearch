package com.example.medicinesearch

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val PERMISSION_ID = 42
    val REQUIRED_SDK_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val REQUEST_CODE_ASK_PERMISSIONS = 1
    val REQUEST_CHECK_SETTINGS = 1
    private lateinit var missingPermissions: ArrayList<String>

    val navController by lazy { Navigation.findNavController(this,R.id.nav_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNav(navController)

        checkPermission()
    }

    private fun setUpBottomNav(navController: NavController){
        bottom_nav_view?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }

    private fun checkPermission() {

        val missingPermissions = arrayListOf<String>()

        for (permission in REQUIRED_SDK_PERMISSIONS) {
            val result = ActivityCompat.checkSelfPermission(this,permission)

            if (result != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission)
            }
        }

        if (!missingPermissions.isEmpty()) {

            val permissions = missingPermissions.toTypedArray()

            ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE_ASK_PERMISSIONS)

        } else {

            val grantResults = IntArray(REQUIRED_SDK_PERMISSIONS.size)

            Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED)

            onRequestPermissionsResult(
                REQUEST_CODE_ASK_PERMISSIONS, REQUIRED_SDK_PERMISSIONS,
                grantResults
            )

        }


    }




}