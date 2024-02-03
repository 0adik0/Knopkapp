package com.knopkapp.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knopkapp.R
import com.knopkapp.databinding.FragmentAdminCreateAccountBinding
import com.knopkapp.databinding.FragmentAdminMainMenuBinding

class AdminCreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentAdminCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminCreateAccountBinding.inflate(layoutInflater)



        return binding.root
    }


}