package com.example.resumeandroidapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    val id: Int = 0,
    @PrimaryKey val name: String,
    val password: String
)
