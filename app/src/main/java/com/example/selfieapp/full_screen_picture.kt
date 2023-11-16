package com.example.selfieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class full_screen_picture : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_full_screen_picture, container, false)
        val img = view.findViewById<ImageView>(R.id.bigboy2)
        val viewModel: storageViewModel = ViewModelProvider(requireActivity()).get(storageViewModel::class.java)
        var picLink = viewModel.selectedLink
        Glide.with(view.context).load(picLink).into(img)
        return view
    }

}