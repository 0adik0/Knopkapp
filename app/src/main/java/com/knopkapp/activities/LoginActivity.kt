package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.knopkapp.R
import com.knopkapp.databinding.ActivityLoginBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.UniversalDate
import com.knopkapp.models.OwnerDates

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordInputLayout: TextInputLayout

    lateinit var auth: FirebaseAuth
    private lateinit var sessionManager: SessionManager
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Инициализация Firebase Authentication
        auth = FirebaseAuth.getInstance()

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
            // Попытка входа с использованием email и password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Вход успешен, пользователь существует
                        val user = auth.currentUser
                        sessionManager.isRegistered = true
                        // Выполните необходимые действия, например, обновление UI
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
            startActivity(Intent(this,RegistrationActivity::class.java))
            finish()
        }
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
