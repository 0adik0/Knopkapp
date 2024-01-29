package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.knopkapp.R

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var passwordInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextPassword)
        passwordInputLayout = findViewById(R.id.textInputPassword)

        findViewById<Button>(R.id.btnlogin).setOnClickListener {
            validateAndAnimate()
        }
    }

    private fun validateAndAnimate() {
        var isEmpty = false

        if (emailEditText.text.isNullOrEmpty()) {
            emailEditText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
            isEmpty = true
        }

        if (passwordEditText.text.isNullOrEmpty()) {
            passwordInputLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))
            isEmpty = true
        }

        if (!isEmpty) {
            val intent = Intent(this, MoodActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
