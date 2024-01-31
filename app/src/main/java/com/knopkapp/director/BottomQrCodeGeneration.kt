package com.knopkapp.director

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.knopkapp.R
import com.knopkapp.databinding.FragmentBottomQrCodeGenerationBinding

class BottomQrCodeGeneration : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomQrCodeGenerationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomQrCodeGenerationBinding.inflate(layoutInflater)
        binding.root.setBackgroundResource(R.drawable.rounded_bottom_sheer)

        binding.closeBtn.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

}