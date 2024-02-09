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

            AlertDialog.Builder(requireContext())
                .setMessage("Вы хотите выйти?")
                .setPositiveButton("yes") { dialog, which ->
                    sessionManager.isRegistered = false
                    sessionManager.restaurantName = ""
                    sessionManager.status = ""
                    startActivity(Intent(requireContext(),LoginActivity::class.java))
                }
                .setNegativeButton("no") { dialog, which ->
                    dialog.dismiss();
                }
                .show();

        }


        return binding.root
    }


}