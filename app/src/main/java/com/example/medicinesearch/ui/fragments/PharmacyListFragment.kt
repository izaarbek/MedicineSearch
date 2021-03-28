package com.example.medicinesearch.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicinesearch.R
import com.example.medicinesearch.ui.adapters.PharmacyAdapter
import com.example.medicinesearch.ui.data.models.Combined
import com.example.medicinesearch.ui.viewmodels.MedicineViewModel
import com.example.medicinesearch.ui.viewmodels.PharmacyViewModel
import com.example.medicinesearch.ui.viewmodels.StockViewModel
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_pharmacy_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PharmacyListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PharmacyListFragment : Fragment(R.layout.fragment_pharmacy_list) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val medicineViewModel by viewModels<MedicineViewModel>()
    private val stockViewModel by viewModels<StockViewModel>()
    private val pharmacyViewModel by viewModels<PharmacyViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private val combinedList= mutableListOf<Combined>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {







            val drug_id = it.getInt("drug_id")
            medicineViewModel.getById(drug_id)

            drugTxt.text =
                "${it.getString("drug_name")} ${it.getString("drug_form")} ${it.getString("drug_dosage")}"

            favoriteCheckBox.isChecked = it.getInt("is_favorite") == 1

            favoriteCheckBox.setOnCheckedChangeListener { compoundButton, b ->
                val medicine = medicineViewModel.currentMedicine
                medicine.is_favorite = if (b) 1 else 0


                Log.i("askdhbjvwvg", ":" + medicine.toString())
                medicineViewModel.update(medicine)

            }

            stockViewModel.getStockByDrugid(drug_id)

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }


            startLocationUpdates()








        }
    }


    private  val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // do work here
            val location = locationResult.lastLocation

            stockViewModel.stockList.observe(viewLifecycleOwner, Observer { stockList ->

                val idsOfPharmacies = stockList.map { it.pharmacy_id }

                Log.i("askdhbjvefb",":"+idsOfPharmacies .toString())

                pharmacyViewModel.getPharmaciesByIds(idsOfPharmacies)

                pharmacyViewModel.pharmacyList.observe(
                    viewLifecycleOwner,
                    Observer { pharmacyList ->



                        if(pharmacyList.isNotEmpty()){
                            Log.i("askdhbjvefb","dvd:")

                            pharmacyList.forEach { pharmacy ->
                                val prices = stockList.find { it.pharmacy_id == pharmacy.id }

                                val distance=distance(location.latitude,location.longitude,pharmacy.lat,pharmacy.lng)
                                val combined = Combined(pharmacy.name, prices!!.price,distance)
                                combinedList.add(combined)

                                Log.i("ajdhzvsv","asjdhvg")




                            }

                            combinedList.sortBy { it.price }

                            Log.i("asjdhbvwvb",":"+combinedList.toString())

                            val pharmacyAdapter=PharmacyAdapter(combinedList)
                            rvPharmacies.adapter=pharmacyAdapter
                            rvPharmacies.layoutManager=LinearLayoutManager(requireContext())
                        }





                    })


            })


        }

    }


    private fun distance(lat1: Double, lat2: Double, lng1: Double, lng2: Double): Double {
        val loc1 = Location("")
        loc1.setLatitude(lat1)
        loc1.setLongitude(lng1)
        val loc2 = Location("")
        loc2.setLatitude(lat2)
        loc2.setLongitude(lng2)
        val distanceInMeters: Float = loc1.distanceTo(loc2)
        return distanceInMeters.toDouble()

    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            numUpdates=3
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
            mLocationCallback,
            Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(mLocationCallback)
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
    }




}