package com.example.resumeandroidapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("select * from user_table")
    fun getAllUsers(): Flow<List<User>>

    @Query("delete from user_table")
    suspend fun deleteAllUsers()

    @Query("update user_table set detail = :newDetail where username = :userName")
    suspend fun updateDetail(userName: String, newDetail: String)

//    @Query("delete from sqlite_sequence where name='user_table'") // I added this to reset the counter
//    suspend fun resetTable()
}