package com.knopkapp.waiter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.knopkapp.R
import com.knopkapp.activities.LoginActivity
import com.knopkapp.databinding.ActivityWaiterMainScreenBinding
import com.knopkapp.db.SessionManager

class WaiterMainScreenActivity : AppCompatActivity() {


    private lateinit var binding: ActivityWaiterMainScreenBinding
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaiterMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)

        binding.notificationButton.setOnClickListener {
            startActivity(Intent(this, WaiterTablesActivity::class.java))
        }


        binding.logoutButton.setOnClickListener {

            AlertDialog.Builder(this)
                .setMessage("Вы хотите выйти?")
                .setPositiveButton("yes") { dialog, which ->
                    sessionManager.isRegistered = false
                    sessionManager.restaurantName = ""
                    sessionManager.status = ""
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                .setNegativeButton("no") { dialog, which ->
                    dialog.dismiss();
                }
                .show();
        }
    }
}