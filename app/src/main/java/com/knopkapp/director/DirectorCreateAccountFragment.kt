package com.knopkapp.director

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentDirectorCreateAccountBinding
import com.knopkapp.models.OwnerDates

class DirectorCreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentDirectorCreateAccountBinding

    lateinit var firebaseFireStore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDirectorCreateAccountBinding.inflate(layoutInflater)
        firebaseFireStore = FirebaseFirestore.getInstance()

        binding.createButton.setOnClickListener {

        }

        return binding.root
    }




}