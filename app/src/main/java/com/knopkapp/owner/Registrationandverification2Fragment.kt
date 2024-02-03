package com.knopkapp.owner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.databinding.FragmentRegistrationandverification2Binding
import com.knopkapp.models.OwnerDates

class Registrationandverification2Fragment : Fragment() {
    private lateinit var binding: FragmentRegistrationandverification2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationandverification2Binding.inflate(layoutInflater)


        binding.regnext2.setOnClickListener {
            binding.apply {
                OwnerDates.name = nameEdtiText.text.toString()
                OwnerDates.bin = binEditText.text.toString().toLong()
                OwnerDates.address = addressEditText.text.toString()
                OwnerDates.type = typeEditText.text.toString()
            }
            findNavController().navigate(R.id.registrationandverification3Fragment)

        }
        return binding.root
    }

    /*  private fun replaceFragment(fragment: Fragment) {
          activity!!.supportFragmentManager.beginTransaction()
              .replace(R.id.fragmentContainer, fragment)
              .addToBackStack(null)
              .commit()
      }*/

 /*   private fun updateProgressBar(progress: Int) {
        val activity: Activity? = activity
        if (activity != null) {
            val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar)
            val animation =
                ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
            animation.duration = 1000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }
    }*/
}