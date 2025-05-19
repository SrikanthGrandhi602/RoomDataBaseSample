package com.example.roomdatabasesample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "user_table")
data class User(@PrimaryKey
   val email:String,
   val name : String?,
    val age : Int
)

