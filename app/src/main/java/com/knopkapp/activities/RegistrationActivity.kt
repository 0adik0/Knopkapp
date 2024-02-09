package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.ActivityRegistrationBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.UniversalDate
import com.knopkapp.waiter.WaiterMainScreenActivity
import com.knopkapp.waiter.WaiterTablesActivity

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    lateinit var firebaseFireStore: FirebaseFirestore

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        firebaseFireStore = FirebaseFirestore.getInstance()

    }

    override fun onResume() {
        super.onResume()
        if (sessionManager.isRegistered){
            when (sessionManager.status) {
                "Owner" -> {
                    findNavController(R.id.fragmentContaner).navigate(R.id.ownerMainMenuFragment)
                }
                "Director" -> {
                    findNavController(R.id.fragmentContaner).navigate(R.id.directorMainScreenFragment)
                }
                "Administrator" -> {
                    findNavController(R.id.fragmentContaner).navigate(R.id.adminMainMenuFragment)
                }
                "Waiter" -> {
                    val intent = Intent(this, WaiterMainScreenActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }



    private fun firestoreGet(){

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${sessionManager.restaurantName}").document("User Date")
            .collection("${sessionManager.status}")
            .document("${UniversalDate.email}")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val fio = documentSnapshot.getString("FIO")
                    if (fio != null) {

                    }
                    else{
                        startActivity(Intent(this, RegistrationActivity::class.java))
                        finish()
                    }
                }
                else{
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    finish()
                }
            }
            .addOnFailureListener {

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
