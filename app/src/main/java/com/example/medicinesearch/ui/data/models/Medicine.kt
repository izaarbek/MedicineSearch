package com.example.medicinesearch.ui.data.models

import androidx.annotation.NonNull
import androidx.room.*
import org.jetbrains.annotations.NotNull


@Entity(tableName = "medicine",indices = emptyArray(),foreignKeys = emptyArray())
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    var id: Int,
    @ColumnInfo(name = "name")
    @NonNull
    var name: String,
    @ColumnInfo(name = "manufacturer")
    @NonNull
    var manufacturer: String,
    @ColumnInfo(name = "expiryDate")
    @NonNull
    var expiryDate: String,
    @ColumnInfo(name = "isFavorite")
    @NonNull
    var isFavorite: Int
)