package com.example.contact

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.contact.database.Contact
import com.example.contact.database.ContactDao
import com.example.contact.database.ContactDatabase
import kotlinx.coroutines.*

@SuppressLint("StaticFieldLeak")
class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private var job: Job? = null
    val database: ContactDatabase = ContactDatabase.getInstance(application)
    val contactDao: ContactDao = database.contactDao()

    fun getData(): LiveData<List<Contact>> {
        return contactDao.getAllContacts()
    }

    fun deleteOne(position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.deleteAt(position)
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}