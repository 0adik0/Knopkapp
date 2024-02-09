package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController
import com.google.firebase.firestore.DocumentReference
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
    private lateinit var directorDocument: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)
        firebaseFireStore = FirebaseFirestore.getInstance()
    }

    override fun onResume() {
        super.onResume()

        if (sessionManager.fio != "") {
            sessionManager.isRegistered = true
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
}
