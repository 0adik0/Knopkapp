package com.knopkapp.admin

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentDivisionOfTablesBinding
import com.knopkapp.databinding.FragmentStartWorkBinding
import es.dmoral.toasty.Toasty
import java.util.Calendar

class StartWorkFragment : Fragment() {

    private lateinit var binding: FragmentStartWorkBinding

    lateinit var firebaseFireStore: FirebaseFirestore

    lateinit var time: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartWorkBinding.inflate(layoutInflater)

        firebaseFireStore = FirebaseFirestore.getInstance()

        binding.apply {

            startWorkEditText.setOnClickListener {
                timePickerDialog()
            }

            saveButton.setOnClickListener {
                if (time.isNullOrEmpty()) {
                    Toasty.info(requireContext(), "Укажите начала рабочего дня", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    firestoreAdd()
                }
            }

        }




        return binding.root
    }

    private fun firestoreAdd() {

        val table = arguments?.getIntegerArrayList("table")
        val login = arguments?.getString("login")
        val name = arguments?.getString("name")

        val userDate = HashMap<String, Any>()

        userDate["name"] = name.toString()
        userDate["table"] = table.toString()
        userDate["startTime"] = time

        firebaseFireStore.collection("Waiters").document(login.toString())
            .set(userDate)
            .addOnSuccessListener {
                Toasty.info(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.adminMainMenuFragment)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
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
                time = selectedTime
            },
            hour,
            minute,

            true
        )

        timePickerDialog.show()
    }

}