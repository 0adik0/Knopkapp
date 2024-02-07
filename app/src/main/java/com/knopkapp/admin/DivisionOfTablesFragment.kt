package com.knopkapp.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentDivisionOfTablesBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.OwnerDates
import com.knopkapp.models.WaiterDates
import es.dmoral.toasty.Toasty

class DivisionOfTablesFragment : Fragment() {

    private lateinit var binding: FragmentDivisionOfTablesBinding
    lateinit var firebaseFireStore: FirebaseFirestore

    lateinit var sessionManager: SessionManager

    lateinit var waiterName: String
    lateinit var waiterLogin: String
    private var tablesAmount: Int = 0
    private var TAG = "DivisionFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDivisionOfTablesBinding.inflate(layoutInflater)
        firebaseFireStore = FirebaseFirestore.getInstance()
        sessionManager = SessionManager(requireContext())
        showBlackAndProgress()
        getCountOfTable()

        typeOfWaiter()

        binding.regnext2.setOnClickListener {

            if (tablesAmount == 0 || waiterName.isNullOrEmpty() || waiterLogin.isNullOrEmpty()
            ) {
                Toasty.info(requireContext(), "Заполните ячейки", Toast.LENGTH_SHORT).show()
            } else if (waiterName == "Выберите официанта") {
                Toasty.info(requireContext(), "Выберите официанта", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putString("table", tablesAmount.toString())
                    putString("login", waiterLogin)
                    putString("name", waiterName)
                }
                findNavController().navigate(R.id.startWorkFragment, bundle)
            }

        }

        return binding.root
    }

    private fun getCountOfTable() {
        firebaseFireStore.collection("Users").document("Dates")
            .collection(sessionManager.restaurantName.toString()).document("Restaurant Date")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val tables = document.get("Tables")
                    if (tables != null) {
                        val tableCount = tables.toString()
                        val tableNumbers = (1..tableCount.toInt()).toList()

                        // Создаем адаптер для спиннера
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_item,
                            tableNumbers
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        // Присваиваем адаптер к спиннеру
                        binding.tableCountSpinner.adapter = adapter
                        binding.tableCountSpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    // Получаем выбранный элемент
                                    val selectedTables = parent?.getItemAtPosition(position) as Int
                                    // Обновляем глобальную переменную
                                    tablesAmount = selectedTables
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    // Действие при отсутствии выбранного элемента (можете оставить пустым или добавить нужное действие)
                                }
                            }


                        Log.d(TAG, "Tables: $tables")
                    } else {
                        Log.d(TAG, "No such document")
                    }
                } else {
                    Log.d(TAG, "Document does not exist")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    /* private fun getWaiters() {

         val usersCollection = firebaseFireStore.collection("Users").document("Dates")
             .collection(sessionManager.restaurantName.toString()).document("User Date")
             .collection("Waiter")

         usersCollection.get()
             .addOnSuccessListener { querySnapshot ->
                 val waiterList = mutableListOf<Map<String, Any>>()

                 for (document in querySnapshot.documents) {
                     // Получите только нужные поля "FIO" и "Login" из документа
                     val waiterData = mapOf(
                         "FIO" to document.get("FIO"),
                         "Login" to document.get("Login")
                         // Добавьте другие нужные поля по аналогии
                     )

                     // Добавьте данные в список, если они не равны null
                     waiterData?.let {
                         waiterList.add(it as Map<String, Any>)
                         Toast.makeText(context, "$waiterData", Toast.LENGTH_SHORT).show()
                         Log.d("MyLog", waiterData.toString())
                     }
                 }

                 // waiterList теперь содержит только поля "FIO" и "Login" из документов в коллекции "Waiter"
                 // Обработайте список waiterList в соответствии с вашими потребностями
             }
             .addOnFailureListener { exception ->
                 // Обработка ошибок
                 Log.e("Firestore", "Error getting documents: $exception")
             }
     }*/

    private fun typeOfWaiter() {
        // Создайте пустой список для хранения FIO
        val fioList = mutableListOf<String>()
        // Создайте Map для связи "FIO" и "Login"
        val fioLoginMap = mutableMapOf<String, String>()

        // Замените этот код на ваш фрагмент для получения данных из Firestore

        val waiterCollection = firebaseFireStore.collection("Users").document("Dates")
            .collection(sessionManager.restaurantName.toString()).document("User Date")
            .collection("Waiter")

        waiterCollection.get()
            .addOnSuccessListener { querySnapshot ->
                fioList.add(0, "Выберите официанта")
                hideBlackAndProgress()
                for (document in querySnapshot.documents) {
                    // Получите значения "FIO" и "Login" из документа
                    val fio = document.getString("FIO")
                    val login = document.getString("Login")

                    // Добавьте значения "FIO" и "Login" в список и Map, если они не равны null
                    if (fio != null && login != null) {
                        fioList.add(fio)
                        fioLoginMap[fio] = login
                    }
                }

                // categoriesArray теперь содержит все "FIO" из документов в коллекции "Waiter"
                val categoriesArray = fioList.toTypedArray()

                // Создайте адаптер для спиннера
                val adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    categoriesArray
                )

                // Определите, какие данные отображать, когда выбрана конкретная категория
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

                // Примените адаптер к спиннеру
                binding.waitersSpinner.adapter = adapter

                // Установите слушатель выбора для спиннера
                binding.waitersSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parentView: AdapterView<*>?,
                            selectedItemView: View?,
                            position: Int,
                            id: Long
                        ) {
                            // Обработайте выбор элемента
                            val selectedFIO = categoriesArray[position]

                            // Обработайте выбор официанта
                            val selectedLogin = fioLoginMap[selectedFIO]
                            waiterLogin = selectedLogin.toString()
                            waiterName = selectedFIO

                        }

                        override fun onNothingSelected(parentView: AdapterView<*>?) {
                            // Действие при отсутствии выбора
                        }
                    }
            }
            .addOnFailureListener { exception ->
                // Обработка ошибок
                Log.e("Firestore", "Error getting documents: $exception")
            }
    }

    private fun hideBlackAndProgress() {
        binding.darkLayer.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
    }

    private fun showBlackAndProgress() {
        binding.darkLayer.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }


}