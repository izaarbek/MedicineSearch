package com.example.medicinesearch.ui.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.medicinesearch.ui.data.models.Pharmacy

@Dao
interface PharmacyDao {
    @Query("SELECT * FROM pharmacy WHERE id_аптеки IN (:range)")
    fun getPharmaciesById(range:List<Int>):LiveData<List<Pharmacy>>
}