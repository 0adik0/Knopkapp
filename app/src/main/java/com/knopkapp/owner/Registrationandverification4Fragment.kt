package com.knopkapp.owner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentRegistrationandverification4Binding
import com.knopkapp.models.OwnerDates
import es.dmoral.toasty.Toasty

class Registrationandverification4Fragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    lateinit var firebaseFireStore: FirebaseFirestore

    private lateinit var binding: FragmentRegistrationandverification4Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationandverification4Binding.inflate(layoutInflater)
        // Инициализация Firebase Authentication
        auth = FirebaseAuth.getInstance()
        firebaseFireStore = FirebaseFirestore.getInstance()

        binding.btnlogin2.setOnClickListener {
            registerUser()

        }
        return binding.root

    }

    private fun firestoreAdd() {

        val userDate = HashMap<String, Any>()
        userDate["Login"] = binding.emailEditText.text.toString()
        userDate["Password"] = binding.passwordEditText.text.toString()
        userDate["Status"] = "Director"
        userDate["Restaurant"] = OwnerDates.name.toString()

        firebaseFireStore.collection("Users").document("Dates")
            .collection("${OwnerDates.name}").document("User Date").collection("Director")
            .document("${binding.emailEditText.text}")
            .set(userDate)
            .addOnSuccessListener {
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }

    }

    private fun registerUser() {

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