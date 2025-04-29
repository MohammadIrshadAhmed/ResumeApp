package com.example.resumeandroidapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val name: String,
    val password: String,
    var detail: String
)
