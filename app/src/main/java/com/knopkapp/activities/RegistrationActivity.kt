package com.knopkapp.activities

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.knopkapp.R
import com.knopkapp.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            findNavController(R.id.fragmentContaner).navigate(R.id.registrationAndVarification1Fragment)
        }


    }
    /*fun setFragment(destinationId: Int) {
        findNavController(R.id.fragmentContaner).navigate(destinationId)
        updateProgressBar(destinationId)
    }
    fun updateProgressBar(destinationId: Int) {
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        when (destinationId) {
            R.id.registrationAndVarification1Fragment -> progressBar.progress = 20
            R.id.writeSMSFragment -> progressBar.progress = 40
            R.id.registrationandverification2Fragment -> progressBar.progress = 60
            R.id.registrationandverification3Fragment -> progressBar.progress = 80
            R.id.registrationandverification4Fragment -> progressBar.progress = 100
        }
    }*/
}
