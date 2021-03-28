package com.example.medicinesearch.ui.data.repository

import androidx.lifecycle.LiveData
import com.example.medicinesearch.ui.data.dao.MedicineDao
import com.example.medicinesearch.ui.data.models.Medicine

class MedicineRepository(private val medicineDao: MedicineDao):MedicineDao {
    override fun getAll(): LiveData<List<Medicine>> {
        return medicineDao.getAll()
    }

    override fun loadAllFavorites(isFavorite: Int): LiveData<List<Medicine>> {
        return medicineDao.loadAllFavorites(isFavorite)
    }

    override fun findByName(first: String): LiveData<List<Medicine>> {
        return medicineDao.findByName(first)
    }

    override fun insert(medicine: Medicine) {
        medicineDao.insert(medicine)
    }

    override fun delete(medicine: Medicine) {
       medicineDao.delete(medicine)
    }

    override suspend fun update(medicine: Medicine) {
        medicineDao.update(medicine)
    }

    override suspend fun findById(id: Int): Medicine {
       return medicineDao.findById(id)
    }
}