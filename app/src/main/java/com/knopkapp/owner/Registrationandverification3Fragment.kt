package com.knopkapp.owner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentRegistrationandverification3Binding
import com.knopkapp.models.OwnerDates

class Registrationandverification3Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationandverification3Binding

    lateinit var firebaseFireStore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationandverification3Binding.inflate(layoutInflater)

        firebaseFireStore = FirebaseFirestore.getInstance()
        binding.regpay.setOnClickListener {

            OwnerDates.amountTables = binding.amountTableEditText.text.toString().toInt()
            OwnerDates.status = "Owner"
            firestoreAdd()
            findNavController().navigate(R.id.registrationandverification4Fragment)
        }
        return binding.root
    }


    private fun firestoreAdd() {

        val userDate = HashMap<String, Any>()
        userDate["FIO"] = OwnerDates.fio.toString()
        userDate["Login"] = OwnerDates.email.toString()
        userDate["Password"] = OwnerDates.password.toString()
        userDate["Phone"] = OwnerDates.phoneNumber!!.toLong()
        userDate["Status"] = OwnerDates.status.toString()
        userDate["Restaurant"] = OwnerDates.name.toString()

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${OwnerDates.name}").document("User Date").collection("Owner")
            .document("${OwnerDates.email}")
            .set(userDate)
            .addOnSuccessListener {
                Toast.makeText(context, "Сохранено", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }

        val buildingDate = HashMap<String, Any>()
        buildingDate["Name"] = OwnerDates.name.toString()
        buildingDate["BIN"] = OwnerDates.bin.toString()
        buildingDate["Address"] = OwnerDates.address.toString()
        buildingDate["Type"] = OwnerDates.type.toString()
        buildingDate["Tables"] = OwnerDates.amountTables!!.toInt()

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${OwnerDates.name}").document("Restaurant Date")
            .set(buildingDate)
            .addOnSuccessListener {
                Toast.makeText(context, "Сохранено", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

 /*   private fun updateProgressBar(progress: Int) {
        val activity: Activity? = activity
        if (activity != null) {
            val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar)
            val animation =
                ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
            animation.duration = 1000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }
    }*/
}