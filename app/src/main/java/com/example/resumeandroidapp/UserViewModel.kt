package com.example.resumeandroidapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val database = DataBaseBuilder.getInstance(application.applicationContext)
    private val userDao = database.userDao()
    private val _isUserDataLoading = MutableStateFlow(true)
    val isUserDataLoading: StateFlow<Boolean> = _isUserDataLoading

    val usersData: StateFlow<List<User>> = userDao.getAllUsers()
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

    fun updateCategory(userName: String, updatedDetail: String) {
        viewModelScope.launch {
            userDao.updateDetail(userName, updatedDetail)
        }
    }

//        fun getAllUsers(): Flow<List<User>> = liveData {
//            emit(userDao.getAllUsers())
//        }

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