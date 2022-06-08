package com.example.contact

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var add: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        add = findViewById(R.id.add)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.getData().observe(this) {
            recyclerView.adapter = ContactAdapter(it, this@MainActivity)
            ContactAdapter(it, this@MainActivity).notifyDataSetChanged()
        }

        add.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddContact::class.java))
        }

    }

    override fun onBackPressed() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

        builder.setMessage("Do you want to exit ?")

        builder.setTitle("Alert !")

        builder.setCancelable(true)

        builder
            .setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog, yes ->
                    finish()
                })

        builder
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, no ->
                    dialog.cancel()
                })

        val alertDialog: AlertDialog = builder.create()

        alertDialog.show()
    }

    fun removeAt(position : Int) {
        viewModel.deleteOne(position)
    }

    fun showDetailsDialog(){
        val dialog=Dialog(this,R.style.PopUpDialogStyle)
        dialog.setContentView(R.layout.custom_dialog)
//        dialog.getWindow().setBackgroundBlurRadius("12dp")
        dialog.show()
    }
    fun dialogClose(){
        val dialog=Dialog(this,R.style.PopUpDialogStyle)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.cancel()

    }


}

