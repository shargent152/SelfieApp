package com.example.selfieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_LINK = "link"


class full_screen_picture : Fragment() {
    private var link: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            link = it.getString(ARG_LINK)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_full_screen_picture, container, false)
        val img = view.findViewById<ImageView>(R.id.bigboy2)
        val picLink = Firebase.storage.reference.child("images/" + link)
        Glide.with(view.context).load(picLink).into(img)
        Toast.makeText(context,"Tap Image To go Back or Shake Screen to Take Selfie",Toast.LENGTH_SHORT).show()
        img.setOnClickListener {
            val back = FirstScreen()
            activity?.supportFragmentManager!!.beginTransaction().apply {
                hide(activity?.supportFragmentManager!!.findFragmentByTag("Full_Screen")!!)
                add(R.id.holder,back,"First_Screen")
                commit()
            }
        }
        return view
    }
    companion object{
        @JvmStatic
        fun newInstance(link: String)=
            full_screen_picture().apply {
                arguments = Bundle().apply {
                    putString(ARG_LINK,link)
                }
            }
    }

}