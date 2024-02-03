package com.knopkapp.owner

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.databinding.FragmentOwnerMainMenuBinding

class OwnerMainMenuFragment : Fragment() {

    private lateinit var binding: FragmentOwnerMainMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOwnerMainMenuBinding.inflate(layoutInflater)

        binding.createAccountButton.setOnClickListener {

            findNavController().navigate(R.id.registrationandverification4Fragment)

        }

        return binding.root
    }


}