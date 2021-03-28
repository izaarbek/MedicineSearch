package com.example.medicinesearch.ui.data.repository

import androidx.lifecycle.LiveData
import com.example.medicinesearch.ui.data.dao.PharmacyDao
import com.example.medicinesearch.ui.data.models.Pharmacy

class PharmacyRepository(private val pharmacyDao: PharmacyDao):PharmacyDao {
    override fun getPharmaciesById(range: List<Int>): LiveData<List<Pharmacy>> {
       return pharmacyDao.getPharmaciesById(range)
    }
}