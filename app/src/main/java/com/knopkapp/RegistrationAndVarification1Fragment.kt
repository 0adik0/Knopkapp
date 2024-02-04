package com.knopkapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.databinding.FragmentRegistrationandverification1Binding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.UniversalDate
import com.knopkapp.models.OwnerDates

class RegistrationAndVarification1Fragment : Fragment() {

    private lateinit var binding: FragmentRegistrationandverification1Binding

    lateinit var firebaseFireStore: FirebaseFirestore

    private lateinit var sessionManager: SessionManager

    private lateinit var directorDocument: DocumentReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationandverification1Binding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())
        firebaseFireStore = FirebaseFirestore.getInstance()
        binding.editTextTel.setText("+998221234567")

        binding.regnext1.setOnClickListener {

            val phoneNumber = binding.editTextTel.text.toString().replace(" ", "")
            val phone = binding.editTextTel.text.toString().replace("+", "")

            OwnerDates.fio = binding.editTextFIO.text.toString()
            OwnerDates.phoneNumber = phone.toLong()
            OwnerDates.name = binding.nameOfRestaurant.text.toString()

            UniversalDate.fio = binding.editTextFIO.text.toString()
            UniversalDate.phoneNumber = phone.toLong()
            UniversalDate.restaurant = binding.nameOfRestaurant.text.toString()

            firestreAdd()

            findNavController().navigate(
                R.id.writeSMSFragment,
                bundleOf("phoneNumber" to phoneNumber)
            )

        }
        return binding.root
    }

    private fun firestreAdd() {

        directorDocument = firebaseFireStore
            .collection("Users")
            .document("Dates")
            .collection(UniversalDate.restaurant.toString())
            .document("User Date")
            .collection("Director")
            .document("${UniversalDate.email}")

        directorDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val status = documentSnapshot.getString("Status")
                    if(status != null){
                        UniversalDate.status = status
                        Toast.makeText(context, "First $status", Toast.LENGTH_SHORT).show()
                    }
                    
                }
            }
            .addOnFailureListener { exception ->
                // Произошла ошибка при получении документа
            }

        directorDocument = firebaseFireStore
            .collection("Users")
            .document("Dates")
            .collection(UniversalDate.restaurant.toString())
            .document("User Date")
            .collection("Administrator")
            .document("${UniversalDate.email}")

        directorDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val status = documentSnapshot.getString("Status")
                    if(status != null){
                        UniversalDate.status = status
                        Toast.makeText(context, "Second $status", Toast.LENGTH_SHORT).show()

                    }


                }

            }
            .addOnFailureListener { exception ->
                // Произошла ошибка при получении документа
            }

        directorDocument = firebaseFireStore
            .collection("Users")
            .document("Dates")
            .collection(UniversalDate.restaurant.toString())
            .document("User Date")
            .collection("Waiter")
            .document("${UniversalDate.email}")

        directorDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val status = documentSnapshot.getString("Status")

                    if(status != null){
                        UniversalDate.status = status
                        Toast.makeText(context, "Third $status", Toast.LENGTH_SHORT).show()
                    }

                }

            }
            .addOnFailureListener { exception ->
                // Произошла ошибка при получении документа
            }




    }

    /* private fun replaceFragment(fragment: Fragment) {
         requireActivity().supportFragmentManager.beginTransaction()
             .replace(R.id.fragmentContainer, fragment)
             .addToBackStack(null)
             .commit()
     }*/

    /*  private fun updateProgressBar(progress: Int) {
          val progressBar: ProgressBar? = activity?.findViewById(R.id.progressBar)
          if (progressBar != null) {
              val animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
              animation.duration = 1000
              animation.interpolator = AccelerateDecelerateInterpolator()
              animation.start()
          }
          *//*val progressBar = activity?.findViewById<ProgressBar>(R.id.progressBar)
        val animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar!!.progress, progress)
        animation.duration = 1000
        animation.interpolator = AccelerateDecelerateInterpolator()
        animation.start()*//*
      *//*  val activity: Activity? = activity
        if (activity != null) {
            val progressBar = activity.findViewById<ProgressBar>(R.id.progressBar)
            val animation =
                ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, progress)
            animation.duration = 1000
            animation.interpolator = AccelerateDecelerateInterpolator()
            animation.start()
        }*//*
    }*/
}