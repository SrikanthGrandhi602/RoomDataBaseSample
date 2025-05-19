package com.example.roomdatabasesample.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabasesample.data.User
import com.example.roomdatabasesample.interfaces.UserDao

class UserRepository(private val userDao:UserDao) {
    val userDetails: LiveData<List<User>> = userDao.getAllUsers()
    suspend fun insert(user:User){
        userDao.insert(user)

    }
}