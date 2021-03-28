package com.example.medicinesearch.ui.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.medicinesearch.ui.data.models.Medicine

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicine")
    fun getAll(): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicine WHERE is_favorite IN (:isFavorite)")
    fun loadAllFavorites(isFavorite: Int): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicine WHERE name LIKE :first")
    fun findByName(first: String): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicine WHERE id=:id")
    suspend fun findById(id:Int):Medicine

    @Insert
    fun insert(medicine: Medicine)

    @Delete
    fun delete(medicine: Medicine)

    @Update
    suspend fun update(medicine: Medicine)
}