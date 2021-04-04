package com.example.medicinesearch.ui.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.medicinesearch.ui.data.models.Medicine
import com.example.medicinesearch.ui.data.models.Stock

@Dao
interface StockDao {

    @Query("SELECT * FROM stock WHERE кодаптеки=:id LIMIT 500")
    fun selectStocksbyDrugId(id:Int): LiveData<List<Stock>>
}