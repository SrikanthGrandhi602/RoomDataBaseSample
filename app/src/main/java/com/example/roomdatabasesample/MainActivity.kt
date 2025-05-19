package com.example.roomdatabasesample

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasesample.data.AppDataBase
import com.example.roomdatabasesample.repository.UserRepository
import com.example.roomdatabasesample.viewModel.UserViewModel
import com.example.roomdatabasesample.viewModel.UserViewModelFactory
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = AppDataBase.getDatabase(this).userDao()
        val repository = UserRepository(dao)
        viewModel = ViewModelProvider(this,UserViewModelFactory(repository)).get(UserViewModel:: class.java)
        val nameInput:EditText = findViewById(R.id.nameEditText)
        val ageInput:EditText = findViewById(R.id.ageEditText)
        val emailInput:EditText = findViewById(R.id.emailInput)
        val addUserButton:Button = findViewById(R.id.addUserButton)
        val userList:TextView = findViewById(R.id.resultTextView)

        addUserButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()
            val age  = ageInput.text.toString().toIntOrNull() ?: 0
            if(name.isNotBlank() && email.isNotBlank()){
                viewModel.insertUser(name, email, age)
                nameInput.text.clear()
                emailInput.text.clear()
                ageInput.text.clear()
            }

        }
        viewModel.allUsers.observe(this){
            users -> userList.text = users.joinToString ("\n"){ "${it.name} - ${it.email} -${it.age} "}

        }

    }
}
