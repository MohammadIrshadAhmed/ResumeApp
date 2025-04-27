package com.example.resumeandroidapp

import android.content.Context
import androidx.room.Room

object DataBaseBuilder {
    private var INSTANCE: AppDataBase? = null

    fun getInstance(context: Context): AppDataBase {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "app_database"
            ).build()
        }
        return INSTANCE!!
    }
}