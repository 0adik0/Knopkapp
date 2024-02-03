package com.knopkapp.director

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.databinding.FragmentDirectorMainScreenBinding

class DirectorMainScreenFragment : Fragment() {

    private lateinit var binding: FragmentDirectorMainScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDirectorMainScreenBinding.inflate(layoutInflater)

        binding.createAccountButton.setOnClickListener {
            findNavController().navigate(R.id.directorCreateAccountFragment)

        }

        binding.qrCodeScannerButton.setOnClickListener {

            findNavController().navigate(R.id.generationQrCodeFragment)

        }

        return binding.root
    }


}