package com.example.resumeandroidapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}