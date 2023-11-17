package com.example.selfieapp

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

class CustomAdapter(private val dataSet: MutableList<String>, val clickListner: ItemClickListner) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val firepic = view.findViewById<ImageButton>(R.id.firebasepic)
        fun bind(pic: String){
            Log.i("beep boop","image/" + pic)

            val picLink = Firebase.storage.reference.child("images/" + pic)
            val bigfirepic = view.findViewById<ImageView>(R.id.bigboy)
            Glide.with(view.context).load(picLink).into(firepic)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycleview, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
        viewHolder.firepic.setOnClickListener {
            clickListner.onItemClick(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

    interface ItemClickListner{
        fun onItemClick(imageLink: String)
    }

}