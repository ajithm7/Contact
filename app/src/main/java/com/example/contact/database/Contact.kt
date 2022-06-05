package com.example.contact.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var contactId:Long,
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="number")
    var number:String,
    @ColumnInfo(name="date")
var date:String
)