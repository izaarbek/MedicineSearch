package com.example.medicinesearch.ui.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pharmacy")
data class Pharmacy(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_аптеки")
    var id: Int,
    @ColumnInfo(name = "аптека")
    var name: String,
    @ColumnInfo(name = "телефон")
    var phone: Int,
    @ColumnInfo(name = "Geolat")
    var lat: Double,
    @ColumnInfo(name = "Geolong")
    var lng: Double
)