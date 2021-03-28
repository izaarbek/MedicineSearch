package com.example.medicinesearch.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.medicinesearch.ui.data.dao.StockDao
import com.example.medicinesearch.ui.data.db.AppDatabase
import com.example.medicinesearch.ui.data.models.Stock
import com.example.medicinesearch.ui.data.repository.StockRepository
import kotlinx.coroutines.launch

class StockViewModel (application: Application): AndroidViewModel(application) {

    private val stockRepository:StockRepository
    lateinit var stockList:LiveData<List<Stock>>

    init {
        val stockDao= AppDatabase.getDatabase(application).stockDao()
        stockRepository= StockRepository(stockDao)

    }

    fun getStockByDrugid(id:Int)=viewModelScope.launch {
        stockList=stockRepository.selectStocksbyDrugId(id)
    }



}