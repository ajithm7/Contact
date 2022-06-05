package com.example.contact

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.database.ContactDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var add: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        add = findViewById(R.id.add)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL

        setData()

        add.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddContact::class.java))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        val database = ContactDatabase.getInstance(applicationContext)
        val noteDao = database!!.contactDao()

//        database operation should not be in main thread. so use corooutine
//        val list=noteDao?.getAllNotes()
        CoroutineScope(Dispatchers.IO).launch {
            val list = noteDao.getAllContacts()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity,"inside withContext adapter creation", Toast.LENGTH_SHORT).show()
                adapter = ContactAdapter(list)
                recyclerView.adapter=adapter
                adapter.notifyDataSetChanged()


            }
        }

    }
}