package com.example.medicinesearch.ui.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock")
data class Stock(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "кодпрепарата")
    var drug_id: Int,
    @ColumnInfo(name = "кодаптеки")
    var pharmacy_id: Int,
    @ColumnInfo(name = "дата")
    var date: String,
    @ColumnInfo(name = "цена")
    var price: String
)