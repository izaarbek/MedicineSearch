package com.example.medicinesearch.ui.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.medicinesearch.ui.data.models.Medicine

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicine")
    fun getAll(): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicine WHERE isFavorite IN (:isFavorite)")
    fun loadAllFavorites(isFavorite: Boolean): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicine WHERE name LIKE :first")
    fun findByName(first: String): Medicine

    @Insert
    fun insert(medicine: Medicine)

    @Delete
    fun delete(medicine: Medicine)
}