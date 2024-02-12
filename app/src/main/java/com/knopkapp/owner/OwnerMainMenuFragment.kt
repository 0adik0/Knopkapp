package com.knopkapp.owner

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.activities.LoginActivity
import com.knopkapp.databinding.FragmentOwnerMainMenuBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.dialog.LogoutDialogFragment

class OwnerMainMenuFragment : Fragment() {

    private lateinit var binding: FragmentOwnerMainMenuBinding
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerMainMenuBinding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())

        binding.createAccountButton.setOnClickListener {

            findNavController().navigate(R.id.registrationandverification4Fragment)

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