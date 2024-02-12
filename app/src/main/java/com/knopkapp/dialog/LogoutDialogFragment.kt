package com.knopkapp.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.widget.Button
import com.knopkapp.R
import com.knopkapp.activities.LoginActivity
import com.knopkapp.db.SessionManager

class LogoutDialogFragment : DialogFragment() {

    lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_dialog, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.refer_dilog_back)
        sessionManager = SessionManager(requireContext())

        val noButton = rootView.findViewById<Button>(R.id.noButton)
        val yesButton = rootView.findViewById<Button>(R.id.yesButton)

        noButton.setOnClickListener {
            dismiss() // Закрываем диалоговое окно
        }

        yesButton.setOnClickListener {
            sessionManager.isRegistered = false
            sessionManager.restaurantName = ""
            sessionManager.status = ""
            sessionManager.fio = ""
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            dismiss() // Закрываем диалоговое окно после перехода
        }

        return rootView
    }
}
