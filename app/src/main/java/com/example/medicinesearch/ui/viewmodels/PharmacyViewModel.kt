package com.example.medicinesearch.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicinesearch.ui.data.db.AppDatabase
import com.example.medicinesearch.ui.data.models.Pharmacy
import com.example.medicinesearch.ui.data.repository.PharmacyRepository
import kotlinx.coroutines.launch

class PharmacyViewModel(application: Application): AndroidViewModel(application) {

    private val pharmacyRepository:PharmacyRepository
    lateinit var pharmacyList:LiveData<List<Pharmacy>>

    init {
        val pharmacyDao= AppDatabase.getDatabase(application).pharmacyDao()
        pharmacyRepository= PharmacyRepository(pharmacyDao)
    }

    fun getPharmaciesByIds(range:List<Int>)=viewModelScope.launch {
        pharmacyList= pharmacyRepository.getPharmaciesById(range)
    }
}