package com.knopkapp.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.databinding.FragmentAdminMainMenuBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.dialog.LogoutDialogFragment


class AdminMainMenuFragment : Fragment() {

    private lateinit var binding: FragmentAdminMainMenuBinding
    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminMainMenuBinding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())

        binding.apply {
            createAccountButton.setOnClickListener {
                findNavController().navigate(R.id.adminCreateAccountFragment)
            }

            tableDivisionButton.setOnClickListener {
                findNavController().navigate(R.id.divisionOfTablesFragment)
            }

            listOfWaiterButton.setOnClickListener {
                findNavController().navigate(R.id.listOfWaitersFragment)
            }

            logoutButton.setOnClickListener {
                fragmentDialog()
            }
        }

        return binding.root
    }

    private fun fragmentDialog() {
        val dialogFragment = LogoutDialogFragment()
        dialogFragment.show(childFragmentManager, "MyDialogFragment")
    }


}