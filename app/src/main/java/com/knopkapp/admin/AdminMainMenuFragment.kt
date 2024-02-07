package com.knopkapp.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.databinding.FragmentAdminMainMenuBinding


class AdminMainMenuFragment : Fragment() {

    private lateinit var binding: FragmentAdminMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminMainMenuBinding.inflate(layoutInflater)

        binding.apply {
            createAccountButton.setOnClickListener {
                findNavController().navigate(R.id.adminCreateAccountFragment)
            }

            tableDivisionButton.setOnClickListener {
                findNavController().navigate(R.id.divisionOfTablesFragment)
            }
        }

        return binding.root
    }



}