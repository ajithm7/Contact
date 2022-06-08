package com.example.contact.database

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface ContactDao {
    @Insert
    fun insert(contact:Contact)
    @Update
    fun update(contact:Contact)
    @Query("DELETE FROM contact_table")
    fun clear()
//    @Query("drop table contact_table")
//    fun deleteAll()
    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>
    @Query("DELETE from contact_table WHERE contactId=:id")
    fun deleteAt(id:Int)

}