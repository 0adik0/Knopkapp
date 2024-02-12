package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.ActivityLoginBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.UniversalDate
import com.knopkapp.models.OwnerDates
import com.knopkapp.waiter.WaiterMainScreenActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordInputLayout: TextInputLayout

    lateinit var firebaseFireStore: FirebaseFirestore
    private lateinit var directorDocument: DocumentReference
    lateinit var auth: FirebaseAuth

    private lateinit var sessionManager: SessionManager

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebaseFireStore = FirebaseFirestore.getInstance()
        sessionManager = SessionManager(this)

        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextPassword)
        passwordInputLayout = findViewById(R.id.textInputPassword)

        binding.btnlogin.setOnClickListener {
            loginUser()
        }

    }

    private fun loginUser() {

        // Получение значений из EditText
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        OwnerDates.email = emailEditText.text.toString()
        OwnerDates.password = passwordEditText.text.toString()

        UniversalDate.email = emailEditText.text.toString()

        // Проверка наличия текста в полях email и password
        if (email.isNotEmpty() && password.isNotEmpty()) {
            showBlackAndProgress()
            // Попытка входа с использованием email и password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Вход успешен, пользователь существует
                        val user = auth.currentUser

                        updateUI(user)
                    } else {
                        // Если вход не удался, отобразите сообщение об ошибке
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        } else {
            validateAndAnimate()

        }
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            firebaseFireStore.collection("AllUsers")
                .document(binding.editTextTextEmailAddress.text.toString())
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        // Документ существует
                        val restaurant = documentSnapshot.getString("Restaurant")
                        if (restaurant != null) {
                            sessionManager.restaurantName = restaurant
                            /*
                                                        Toast.makeText(this, "First $restaurant", Toast.LENGTH_SHORT).show()
                            */
                            firestreAdd()

                        }
                    } else {
                        startActivity(Intent(this, RegistrationActivity::class.java))
                        finish()
                        hideBlackAndProgress()

                    }
                }
                .addOnFailureListener {

                }
        }
    }

    private fun firestreAdd() {
        val array = arrayOf("Director", "Administrator", "Waiter")

        for (s in array) {
            directorDocument = firebaseFireStore
                .collection("Users")
                .document("Dates")
                .collection(sessionManager.restaurantName.toString())
                .document("User Date")
                .collection(s)
                .document("${UniversalDate.email}")

            directorDocument.get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        sessionManager.status = s
                        UniversalDate.status = s
                        firestoreGet()
                    }
                }
                .addOnFailureListener { exception ->
                    // Произошла ошибка при получении документа
                }
        }

    }

    private fun firestoreGet() {

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${sessionManager.restaurantName}").document("User Date")
            .collection("${UniversalDate.status}")
            .document("${UniversalDate.email}")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val fio = documentSnapshot.getString("FIO")
                    if (fio!=null){
                        hideBlackAndProgress()

                        sessionManager.fio = fio
                        startActivity(Intent(this, RegistrationActivity::class.java))
                        finish()
                    }
                    else{
                        startActivity(Intent(this, RegistrationActivity::class.java))
                        finish()
                    }

                } else {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    finish()
                }
            }
            .addOnFailureListener {

            }
    }


    private fun hideBlackAndProgress() {
        binding.darkLayer.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun showBlackAndProgress() {
        binding.darkLayer.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun validateAndAnimate() {

        if (emailEditText.text.isNullOrEmpty()) {
            emailEditText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
        }
        if (passwordEditText.text.isNullOrEmpty()) {
            passwordInputLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
        }

    }
}
