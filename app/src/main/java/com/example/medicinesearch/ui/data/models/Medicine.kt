package com.example.medicinesearch.ui.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "medicine")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "release_form")
    var releaseForm: String,
    @ColumnInfo(name = "dosage")
    var dosage: String,
    @ColumnInfo(name = "release_country")
    var countryProducer: String,
    @ColumnInfo(name = "manufacturer")
    var manufacturer: String,
    @ColumnInfo(name = "pharm_group")
    var pharmGroup:String,
    @ColumnInfo(name = "is_favorite")
    var is_favorite: Int
)