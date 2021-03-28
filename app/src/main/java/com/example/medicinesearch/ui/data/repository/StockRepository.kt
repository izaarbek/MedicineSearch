package com.example.medicinesearch.ui.data.repository

import androidx.lifecycle.LiveData
import com.example.medicinesearch.ui.data.dao.StockDao
import com.example.medicinesearch.ui.data.models.Stock

class StockRepository(private val stockDao: StockDao):StockDao {
    override fun selectStocksbyDrugId(id: Int): LiveData<List<Stock>> {
        return stockDao.selectStocksbyDrugId(id)
    }
}