package com.knopkapp.admin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.activities.RegistrationActivity
import com.knopkapp.adapters.DivisionTablesAdapter
import com.knopkapp.databinding.FragmentListOfWaitersBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.WaiterDates


class ListOfWaitersFragment : Fragment() {

    private lateinit var binding: FragmentListOfWaitersBinding
    lateinit var firebaseFireStore: FirebaseFirestore

    lateinit var divisionTablesAdapter: DivisionTablesAdapter

    private lateinit var sessionManager: SessionManager

    private var list = ArrayList<WaiterDates>()
    lateinit var myTable:ArrayList<Int>
    var myLogin = ""
    var myName = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfWaitersBinding.inflate(layoutInflater)
        firebaseFireStore = FirebaseFirestore.getInstance()
        sessionManager = SessionManager(requireContext())

        firestoreCheck()

        return binding.root

    }

    private fun firestoreCheck() {

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${sessionManager.restaurantName}").document("User Date")
            .collection("Waiter")
            .document(myLogin)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val restaurant = documentSnapshot.getString("Restaurant")
                    if (restaurant != null) {
                        if (restaurant == sessionManager.restaurantName) {

                            waitersList()

                        }
                    }
                } else {

                }
            }
            .addOnFailureListener {

            }

    }

    private fun waitersList() {


        firebaseFireStore.collection("Waiters").document(myLogin)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Документ существует
                    val name = documentSnapshot.getString("name")
                    val table = documentSnapshot.get("table")
                    if (name != null || table != null) {

                        list.add(WaiterDates(name, table as ArrayList<Int>?))

                        divisionTablesAdapter = DivisionTablesAdapter(list)

                        binding.waitersRv.adapter = divisionTablesAdapter

                    }
                } else {

                }
            }
            .addOnFailureListener {

            }

    }

}