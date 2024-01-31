package com.knopkapp.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knopkapp.R
import com.knopkapp.databinding.FragmentDivisionOfTablesBinding
import com.knopkapp.databinding.FragmentStartWorkBinding

class StartWorkFragment : Fragment() {
    private lateinit var binding: FragmentStartWorkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartWorkBinding.inflate(layoutInflater)



        return binding.root
    }

}