package com.example.contact

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.database.ContactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@SuppressLint("StaticFieldLeak")
class ContactViewModel(var context: Application) : AndroidViewModel(context) {
    private  val mainActivity:MainActivity=MainActivity()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter
    fun setRecyclerView(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        val llm = LinearLayoutManager(mainActivity)
        llm.orientation = LinearLayoutManager.VERTICAL

        setData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        val database = ContactDatabase.getInstance(context)
        val noteDao = database!!.contactDao()

//        database operation should not be in main thread. so use corooutine
//        val list=noteDao?.getAllNotes()
        CoroutineScope(Dispatchers.IO).launch {
            val list = noteDao.getAllContacts()
            withContext(Dispatchers.Main) {
                Toast.makeText(mainActivity,"inside withContext adapter creation", Toast.LENGTH_SHORT).show()
                adapter = ContactAdapter(list)
                recyclerView.adapter=adapter
                adapter.notifyDataSetChanged()


            }
        }

    }

}