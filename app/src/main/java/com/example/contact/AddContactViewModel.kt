package com.example.contact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.database.Contact
import com.example.contact.database.ContactDao
import com.example.contact.database.ContactDatabase
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class AddContactViewModel(application: Application) : AndroidViewModel(application) {
    var contactdatabase = ContactDatabase.getInstance(application)
    var contactDao: ContactDao = contactdatabase.contactDao()

     fun saveContact(contactName: String, contactNumber: String) {
        val date: String =
            SimpleDateFormat("EEE, dd MMM yy", Locale.getDefault()).format(Date())

        val contact = Contact(0, contactName, contactNumber, date)
        GlobalScope.launch {
            contactDao.insert(contact)

        }

    }
}


