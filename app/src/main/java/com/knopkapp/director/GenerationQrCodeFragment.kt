package com.knopkapp.director

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.knopkapp.R
import com.knopkapp.databinding.FragmentGenerationQrCodeBinding
import es.dmoral.toasty.Toasty


class GenerationQrCodeFragment : Fragment() {

    private lateinit var binding: FragmentGenerationQrCodeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGenerationQrCodeBinding.inflate(layoutInflater)

        binding.generationCode.setOnClickListener {

            if (binding.tablesCount.text.toString().isEmpty()) {
                Toasty.info(requireContext(), "Выберите стол", Toasty.LENGTH_SHORT).show()
            } else {
                val bottomSheetMenu = BottomQrCodeGeneration()
                bottomSheetMenu.show(requireActivity().supportFragmentManager, "menu_bottom_sheet")
            }
        }

        return binding.root
    }

}