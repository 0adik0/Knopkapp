package com.knopkapp.waiter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.knopkapp.R
import com.knopkapp.activities.LoginActivity
import com.knopkapp.databinding.ActivityWaiterMainScreenBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.dialog.LogoutDialogFragment

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
            fragmentDialog()
        }
    }

    private fun fragmentDialog() {
        val dialogFragment = LogoutDialogFragment()
        dialogFragment.show(supportFragmentManager, "MyDialogFragment")
    }
}