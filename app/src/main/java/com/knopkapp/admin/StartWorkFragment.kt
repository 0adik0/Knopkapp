package com.knopkapp.admin

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import com.knopkapp.R
import com.knopkapp.databinding.FragmentDivisionOfTablesBinding
import com.knopkapp.databinding.FragmentStartWorkBinding
import java.util.Calendar

class StartWorkFragment : Fragment() {
    private lateinit var binding: FragmentStartWorkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartWorkBinding.inflate(layoutInflater)

        binding.startWorkEditText.setOnClickListener {
            timePickerDialog()
        }

        return binding.root
    }

    private fun timePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context,
            { view: TimePicker, hourOfDay: Int, minute: Int ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                binding.startWorkEditText.setText(selectedTime)
            },
            hour,
            minute,

            true
        )

        timePickerDialog.show()
    }

}