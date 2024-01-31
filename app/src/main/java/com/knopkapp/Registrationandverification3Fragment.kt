package com.knopkapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.knopkapp.databinding.FragmentRegistrationandverification3Binding

class Registrationandverification3Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationandverification3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationandverification3Binding.inflate(layoutInflater)
        updateProgressBar(80)

        binding.regpay.setOnClickListener {
            findNavController().navigate(R.id.registrationandverification4Fragment)
        }
        return binding.root
    }

  /*  private fun replaceFragment(fragment: Fragment) {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }*/

    private fun updateProgressBar(progress: Int) {
        val activity: Activity? = activity
        if (activity != null) {
            val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar)
            val animation =
                ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
            animation.duration = 1000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }
    }
}