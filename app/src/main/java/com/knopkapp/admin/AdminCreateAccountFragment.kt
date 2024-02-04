package com.knopkapp.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentAdminCreateAccountBinding
import com.knopkapp.databinding.FragmentAdminMainMenuBinding
import com.knopkapp.db.SessionManager
import es.dmoral.toasty.Toasty

class AdminCreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentAdminCreateAccountBinding
    private lateinit var auth: FirebaseAuth

    lateinit var firebaseFireStore: FirebaseFirestore

    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminCreateAccountBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        firebaseFireStore = FirebaseFirestore.getInstance()

        sessionManager = SessionManager(requireContext())

        binding.createButton.setOnClickListener {
            registerUser()
        }

        return binding.root
    }

    private fun firestoreAdd() {

        val userDate = HashMap<String, Any>()

        userDate["Login"] = binding.emailEditText.text.toString()
        userDate["Password"] = binding.passwordEditText.text.toString()
        userDate["Status"] = "Waiter"
        userDate["Restaurant"] = sessionManager.restaurantName.toString()

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${sessionManager.restaurantName}").document("User Date")
            .collection("Waiter")
            .document("${binding.emailEditText.text}")
            .set(userDate)
            .addOnSuccessListener {
                Toasty.info(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.adminMainMenuFragment)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun registerUser() {
        binding.progressBar.visibility = View.VISIBLE
        // Получение значений из EditText
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        // Проверка наличия текста в полях email и password
        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Создание пользователя с использованием email и password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Регистрация успешна, новый пользователь создан
                        val user = auth.currentUser
                        // Выполните необходимые действия, например, обновление UI
                        updateUI(user)
                        binding.progressBar.visibility = View.GONE
                    } else {
                        // Если регистрация не удалась, отобразите сообщение об ошибке
                        Toast.makeText(
                            context, "Registration failed. ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        } else {
            validateAndAnimate()
            // Если поля email и/или password пусты, предупредите пользователя
            Toasty.info(
                requireContext(), "Please enter email and password.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        // Ваш код для обновления пользовательского интерфейса, например, навигация к другой активности
        // Пример: если пользователь успешно зарегистрирован, перейдите к главному экрану
        if (user != null) {
            firestoreAdd()
            Toast.makeText(context, "Registered", Toast.LENGTH_SHORT).show()

            /*startActivity(Intent(this, MainActivity::class.java))
            finish()*/
        }
    }

    private fun validateAndAnimate() {
        binding.apply {
            if (emailEditText.text.isNullOrEmpty()) {
                emailEditText.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce))
            }
            if (passwordEditText.text.isNullOrEmpty()) {
                textInputPassword.startAnimation(
                    AnimationUtils.loadAnimation(
                        context,
                        R.anim.bounce
                    )
                )
            }

        }
    }


}