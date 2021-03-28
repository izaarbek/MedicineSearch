package com.example.medicinesearch.ui.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.medicinesearch.ui.data.dao.MedicineDao
import com.example.medicinesearch.ui.data.models.Medicine

@Database(entities = arrayOf((Medicine::class)), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "medicine_search"
                ).createFromAsset("medicine_search.db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}