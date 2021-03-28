package com.example.medicinesearch.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicinesearch.ui.data.db.AppDatabase
import com.example.medicinesearch.ui.data.models.Medicine
import com.example.medicinesearch.ui.data.repository.MedicineRepository
import kotlinx.coroutines.launch

class MedicineViewModel(application: Application):AndroidViewModel(application) {

    private val medicineRepository:MedicineRepository
    val medicineList:LiveData<List<Medicine>>


    init {
        val medicineDao=AppDatabase.getDatabase(application).medicineDao()
        medicineRepository=MedicineRepository(medicineDao);
        medicineList=medicineRepository.getAll()
    }


    fun searchMedicine(text:String)=viewModelScope.launch{
        medicineRepository.findByName(text)
    }






}