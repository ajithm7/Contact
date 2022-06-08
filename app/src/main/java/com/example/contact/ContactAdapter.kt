package com.example.contact


import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.database.Contact

class ContactAdapter(private var list:List<Contact>,val activity: Activity): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemViewRoot:View=view.findViewById(R.id.itemViewRoot)
//        val item_close:View=view.findViewById(R.id.close)
        var name: TextView =view.findViewById(R.id.name)
        var number: TextView =view.findViewById(R.id.number)
        var date: TextView =view.findViewById(R.id.date)
        var delete: ImageView =view.findViewById(R.id.delete_image)
        val personImage: ImageView =view.findViewById(R.id.person_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = list[position]
        holder.name.text = contact.name
        holder.number.text = contact.number
        holder.date.text = contact.date
        holder.personImage.setImageResource(R.drawable.person_item)
//        holder.delete.setImageResource(R.drawable.recyclerbin)



        holder.itemViewRoot.setOnClickListener {
//            Log.i("ContactAdapter",list[position].name)
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setMessage(list[position].name)
            builder.setTitle("Delete")
            builder.setCancelable(true)
            builder
                .setPositiveButton(
                    "Delete",
                    DialogInterface.OnClickListener{ dialog, delete ->
                        list.toMutableList().removeAt(position)
                        notifyDataSetChanged()
                        (activity as MainActivity).removeAt((list[position].contactId).toInt())
                        dialog.cancel()
                    })
            builder
                .setNegativeButton(
                    "Cancel",
                    DialogInterface.OnClickListener { dialog, cancel ->
                        dialog.cancel()
                    })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }

        holder.itemViewRoot.setOnClickListener {
            (activity as MainActivity).showDetailsDialog()
////            holder.item_close.setOnClickListener{
////                activity.dialogClose()
////            }
//
////            Log.i("ContactAdapter",list[position].name)
////            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
////            builder.setMessage(list[position].name)
////            builder.setTitle("Delete")
////            builder.setCancelable(true)
////            builder
////                .setPositiveButton(
////                    "Delete",
////                    DialogInterface.OnClickListener{ dialog, delete ->
////                        list.toMutableList().removeAt(position)
////                        notifyDataSetChanged()
////                        (activity as MainActivity).removeAt((list[position].contactId).toInt())
////                        dialog.cancel()
////                    })
////            builder
////                .setNegativeButton(
////                    "Cancel",
////                    DialogInterface.OnClickListener { dialog, cancel ->
////                        dialog.cancel()
////                    })
////            val alertDialog: AlertDialog = builder.create()
//            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


