package com.example.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class AddContact : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var number: EditText
    private lateinit var save: Button
    lateinit var nameText: String
    lateinit var numberText: String
    lateinit var viewModel: AddContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        setUI()
        viewModel = ViewModelProvider(this)[AddContactViewModel::class.java]
        save.setOnClickListener {
            nameText = name.text.toString()
            numberText = number.text.toString()
            if (validate()) {
               viewModel.saveContact(nameText,numberText)
                onBackPressed()

            }
        }
    }


    fun validate(): Boolean {
        var isValid=true
        val PATTERN="^(?=.*[0-9]).{10}$".toRegex()
        if (nameText.trim().isBlank() ) {
            Toast.makeText(this, "please enter contact name", Toast.LENGTH_SHORT).show()
            isValid= false
        }
        else if(numberText.trim().isBlank()){
            Toast.makeText(this, "please enter phone numbers ", Toast.LENGTH_SHORT).show()
            isValid= false
        }
        else if(!PATTERN.matches(numberText)){
            Toast.makeText(this, "Enter TEN numbers only", Toast.LENGTH_SHORT).show()
            isValid= false
        }

        return isValid
    }

    private fun setUI() {
        name = findViewById(R.id.name)
        number = findViewById(R.id.number)
        save = findViewById(R.id.save)

    }
}