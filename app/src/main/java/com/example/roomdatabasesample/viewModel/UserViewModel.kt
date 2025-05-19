package com.example.roomdatabasesample.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasesample.data.AppDataBase
import com.example.roomdatabasesample.data.User
import com.example.roomdatabasesample.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {
     val allUsers:LiveData<List<User>> = userRepository.userDetails
    fun insertUser(name: String,email:String,age:Int) =viewModelScope.launch {
        val user =User(email =email,name =name, age = age)
       userRepository.insert(user)
    }
}