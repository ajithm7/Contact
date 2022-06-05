package com.example.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.database.Contact

class ContactAdapter(var list:List<Contact>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name: TextView =view.findViewById(R.id.name)
        var number: TextView =view.findViewById(R.id.number)
        var date: TextView =view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.contect_item,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact=list[position]
        holder.name.text=contact.name
        holder.number.text=contact.number
        holder.date.text=contact.date
    }

    override fun getItemCount(): Int {
        return list.size
    }
}