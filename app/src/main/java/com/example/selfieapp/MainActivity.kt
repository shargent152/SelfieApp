package com.example.selfieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.storage.component1
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.checkerframework.common.returnsreceiver.qual.This
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var recycle : RecyclerView
    private lateinit var manager : RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val storage = Firebase.storage
        val listRef = storage.reference.child("images")
        val pics = mutableListOf<String>()
        listRef.listAll().addOnSuccessListener { (items) ->
            for (item in items) {
                Log.i("Hi","Hello")
                Log.i("lets see if it works",item.name)
                val imageLink = item.name
                pics.add(imageLink)
            }
            Log.i("What are you",pics.size.toString())
            Log.i("hi","I made it here")
            manager = LinearLayoutManager(this@MainActivity)
            recycle = findViewById<RecyclerView>(R.id.recycle).apply {
                layoutManager = manager
                myAdapter = CustomAdapter(pics)
                adapter = myAdapter
            }
        }
            .addOnFailureListener {
                Log.e("error","I failed")
            }

    }


}