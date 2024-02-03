package com.knopkapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.knopkapp.databinding.FragmentRegistrationandverification1Binding
import com.knopkapp.models.DirectorDates
import com.knopkapp.models.OwnerDates

class RegistrationAndVarification1Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationandverification1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationandverification1Binding.inflate(layoutInflater)

        binding.editTextTel.setText("+998221234567")

        binding.regnext1.setOnClickListener {

            val phoneNumber = binding.editTextTel.text.toString().replace(" ","")
            val phone = binding.editTextTel.text.toString().replace("+", "")

            OwnerDates.fio = binding.editTextFIO.text.toString()
            OwnerDates.phoneNumber = phone.toLong()

            DirectorDates.fio = binding.editTextFIO.text.toString()
            DirectorDates.phoneNumber = phone.toLong()

            findNavController().navigate(R.id.writeSMSFragment, bundleOf("phoneNumber" to phoneNumber))

        }
        return binding.root
    }

   /* private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }*/

  /*  private fun updateProgressBar(progress: Int) {
        val progressBar: ProgressBar? = activity?.findViewById(R.id.progressBar)
        if (progressBar != null) {
            val animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
            animation.duration = 1000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }
        *//*val progressBar = activity?.findViewById<ProgressBar>(R.id.progressBar)
        val animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar!!.progress, progress)
        animation.duration = 1000
        animation.interpolator = AccelerateDecelerateInterpolator()
        animation.start()*//*
      *//*  val activity: Activity? = activity
        if (activity != null) {
            val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar)
            val animation =
                ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
            animation.duration = 1000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }*//*
    }*/
}