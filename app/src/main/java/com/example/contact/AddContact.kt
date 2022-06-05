package com.example.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.contact.database.Contact
import com.example.contact.database.ContactDao
import com.example.contact.database.ContactDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AddContact : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var number: EditText
    private lateinit var save: Button
    private lateinit var nameText:String
    private lateinit var numberText:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        setUI()
        save.setOnClickListener{
            nameText=name.text.toString()
            numberText=number.text.toString()
            if(validate()){
                saveNote()
            }
        }
    }


    private fun saveNote() {
        val database: ContactDatabase?=ContactDatabase.getInstance(context = this)
        val noteDao: ContactDao?=database?.contactDao()
        val date:String=
            SimpleDateFormat("EEE, DD MM yy").format(Date())
        Toast.makeText(this,"$date", Toast.LENGTH_LONG).show()
        val note= Contact(0,nameText,numberText,date)
        GlobalScope.launch{
            noteDao?.insert(note)
            withContext(Dispatchers.Main){
                Toast.makeText(this@AddContact,"Note saved $date", Toast.LENGTH_LONG).show()
                finish()
            }
        }

    }

    fun validate():Boolean{
        if(nameText.trim().isBlank() or numberText.trim().isBlank()){
            Toast.makeText(this,"please enter title and description", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun setUI() {
        name=findViewById(R.id.name)
        number=findViewById(R.id.number)
        save=findViewById(R.id.save)

    }
}