package com.example.selfieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.component1
import com.google.firebase.storage.storage


/**
 * A simple [Fragment] subclass.
 * Use the [FirstScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstScreen : Fragment(), CustomAdapter.ItemClickListner {
    private lateinit var recycle: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_screen, container, false)
        val storage = Firebase.storage
        val listRef = storage.reference.child("images")
        val pics = mutableListOf<String>()
        listRef.listAll().addOnSuccessListener { (items) ->
            for (item in items) {
                Log.i("Hi", "Hello")
                Log.i("lets see if it works", item.name)
                val imageLink = item.name
                pics.add(imageLink)
            }
            Log.i("What are you", pics.size.toString())
            Log.i("hi", "I made it here")
            manager = LinearLayoutManager(view.context)
            recycle = view.findViewById<RecyclerView>(R.id.recycle).apply {
                layoutManager = manager
                myAdapter = CustomAdapter(pics,this@FirstScreen)
                adapter = myAdapter
            }
            recycle.setOnClickListener {
                val full = full_screen_picture()
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.holder,full)
                    commit()
                }
            }

        }
            .addOnFailureListener {
                Log.e("error", "I failed")
            }

        return view
    }

    override fun onItemClick(imageLink: String) {
        val full = full_screen_picture.newInstance(imageLink)
        activity?.supportFragmentManager!!.beginTransaction().apply {
            hide(activity?.supportFragmentManager!!.findFragmentByTag("First_Screen")!!)
            add(R.id.holder,full,"Full_Screen")
            commit()
        }
    }


}