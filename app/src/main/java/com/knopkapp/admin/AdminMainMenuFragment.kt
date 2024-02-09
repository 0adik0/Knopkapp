package com.knopkapp.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.activities.LoginActivity
import com.knopkapp.databinding.FragmentAdminMainMenuBinding
import com.knopkapp.db.SessionManager


class AdminMainMenuFragment : Fragment() {

    private lateinit var binding: FragmentAdminMainMenuBinding
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminMainMenuBinding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())

        binding.apply {
            createAccountButton.setOnClickListener {
                findNavController().navigate(R.id.adminCreateAccountFragment)
            }

            tableDivisionButton.setOnClickListener {
                findNavController().navigate(R.id.divisionOfTablesFragment)
            }
        }

        binding.logoutButton.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setMessage("Вы хотите выйти?")
                .setPositiveButton("yes") { dialog, which ->
                    sessionManager.isRegistered = false
                    sessionManager.restaurantName = ""
                    sessionManager.status = ""
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                }
                .setNegativeButton("no") { dialog, which ->
                    dialog.dismiss();
                }
                .show();

        }


        return binding.root
    }



}