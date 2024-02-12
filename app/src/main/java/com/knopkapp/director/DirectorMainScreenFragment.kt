package com.knopkapp.director

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
import com.knopkapp.databinding.FragmentDirectorMainScreenBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.dialog.LogoutDialogFragment

class DirectorMainScreenFragment : Fragment() {

    private lateinit var binding: FragmentDirectorMainScreenBinding

    lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDirectorMainScreenBinding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())
        binding.createAccountButton.setOnClickListener {

            findNavController().navigate(R.id.directorCreateAccountFragment)

        }

        binding.qrCodeScannerButton.setOnClickListener {

            findNavController().navigate(R.id.generationQrCodeFragment)

        }

        binding.logoutButton.setOnClickListener {
            fragmentDialog()
        }

        return binding.root
    }

    private fun fragmentDialog() {
        val dialogFragment = LogoutDialogFragment()
        dialogFragment.show(childFragmentManager, "MyDialogFragment")
    }

}