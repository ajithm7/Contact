package com.example.contact.database

import androidx.room.*
@Dao
interface ContactDao {
    @Insert
    fun insert(contact:Contact)
    @Update
    fun update(contact:Contact)
    @Query("DELETE FROM contact_table")
    fun clear()
    @Query("SELECT * FROM contact_table")
    fun getAllContacts():List<Contact>

}