package com.example.roomdatabasesample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabasesample.interfaces.UserDao


@Database(entities = [User:: class], version = 1)
abstract  class  AppDataBase:RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object {
@Volatile private var INSTANCE : AppDataBase? = null
        fun getDatabase(context: Context):AppDataBase{
            return INSTANCE?: synchronized(this){
                val instances = Room.databaseBuilder(context.applicationContext,
                    AppDataBase::class.java,"sample_database").build()
                INSTANCE = instances
                instances
            }

        }
    }
}