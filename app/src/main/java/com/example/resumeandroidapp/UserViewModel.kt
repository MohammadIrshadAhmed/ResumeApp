package com.example.resumeandroidapp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(context: Context): ViewModel() {
    private val database = DataBaseBuilder.getInstance(context)
    private val userDao = database.userDao()
    private val _isUserDataLoading = MutableStateFlow(true)
    val isUserDataLoading: StateFlow<Boolean> = _isUserDataLoading

    val users: StateFlow<List<User>> = userDao.getAllUsers()
        .onStart {
            _isUserDataLoading.value = true
        }
        .onEach {
            _isUserDataLoading.value = false
        }
        .catch {
            _isUserDataLoading.value = false
        }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addUser(user: User) {
        viewModelScope.launch {
            userDao.insertUser(user)
        }
    }

    //    fun getAllUsers(): Flow<List<User>> = liveData {
    //        emit(userDao.getAllUsers())
    //    }

    //
    //    init {
    //        fetchAllUsers()
    //    }
    //
    //    private fun fetchAllUsers() {
    //        viewModelScope.launch {
    //            _users.value = getAllUsers().state
    //        }
    //    }

    fun deleteAllUsers() {
        viewModelScope.launch {
            userDao.deleteAllUsers()
        }
    }
}