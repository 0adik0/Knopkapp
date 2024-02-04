package com.knopkapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.knopkapp.R
import com.knopkapp.customviews.TypeWriter
import com.knopkapp.db.SessionManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var typeWriter: TypeWriter
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sessionManager = SessionManager(this)
        typeWriter = findViewById(R.id.typeWriterTextView)
        typeWriter.setCharacterDelay(100)
        typeWriter.animateText("- в одной кнопке")

        Handler().postDelayed({
            typeWriter.appendText(" - твой мир -")
        }, "- твой мир -".length * 160.toLong())

        Handler().postDelayed({
            val imageView3: ImageView = findViewById(R.id.imageView3)
            imageView3.setImageResource(R.drawable.btnofficon)
        }, 2000)

        Handler().postDelayed({
            if (sessionManager.isRegistered){
                val intent = Intent(this@SplashActivity, MoodActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 4000)
    }
}
