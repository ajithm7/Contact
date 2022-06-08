package com.example.contact.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Contact::class],version=1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {

    companion object{
        private var INSTANCE:ContactDatabase?=null
        fun getInstance(context: Context):ContactDatabase{
            synchronized(ContactDatabase::class.java) {
//            if(INSTANCE==null){
                INSTANCE =
                    Room.databaseBuilder(context, ContactDatabase::class.java, "contact_database")
                        .fallbackToDestructiveMigration()
                        .build()
                return INSTANCE!!
//            }}
//            return INSTANCE!!
            }
        }

    }
    abstract fun contactDao():ContactDao
}