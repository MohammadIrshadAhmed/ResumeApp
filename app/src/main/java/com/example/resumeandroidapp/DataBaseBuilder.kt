package com.example.resumeandroidapp

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DataBaseBuilder {
    private var INSTANCE: AppDataBase? = null
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // SQL to migrate from version 1 to version 2 (e.g., adding a new column)
            database.execSQL("ALTER TABLE user ADD COLUMN newColumn INTEGER DEFAULT 0 NOT NULL")
        }
    }


    fun getInstance(context: Context): AppDataBase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_database"
            )
                .fallbackToDestructiveMigration() //   .addMigrations(MIGRATION_1_2)  // Add the migration here || when fallback is used all data from data base is cleared
                .build()
        }
        return INSTANCE!!
    }
}