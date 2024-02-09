package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.knopkapp.R
import com.knopkapp.databinding.FragmentWriteSMSBinding
import com.knopkapp.db.SessionManager
import com.knopkapp.models.UniversalDate
import com.knopkapp.waiter.WaiterMainScreenActivity
import com.knopkapp.waiter.WaiterTablesActivity
import es.dmoral.toasty.Toasty
import java.util.concurrent.TimeUnit

private const val TAG = "WriteSMSFragment"

class WriteSMSFragment : Fragment() {

    private lateinit var binding: FragmentWriteSMSBinding

    lateinit var auth: FirebaseAuth

    lateinit var storedVerificationId: String

    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    private lateinit var sessionManager: SessionManager

    private var countDownTimer: CountDownTimer? = null

    private var timeLeftInMillis: Long = 59000 // 59 секунд в миллисекундах

    private val countDownInterval: Long = 1000 // интервал обновления таймера в миллисекундах
    lateinit var firebaseFireStore: FirebaseFirestore

    private lateinit var directorDocument: DocumentReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteSMSBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        sessionManager = SessionManager(requireContext())
        firebaseFireStore = FirebaseFirestore.getInstance()
        val receiveInfo = arguments?.getString("phoneNumber")
        binding.phoneNumberTextView.text = receiveInfo.toString()
        sendVerificationCode(receiveInfo.toString())
        countDown()

        binding.regnext.setOnClickListener {
            val codeNumber = binding.codeEditText.text.toString()

            if (codeNumber.length < 6) {
                Toasty.info(requireContext(), "Введите код полностью", Toasty.LENGTH_SHORT).show()
            } else {

                if (::storedVerificationId.isInitialized) {
                    binding.progBar.visibility = View.VISIBLE
                    // Use storedVerificationId here
                    val credential =
                        PhoneAuthProvider.getCredential(storedVerificationId, codeNumber)
                    signInWithPhoneAuthCredential(credential)

                } else {
                    // Handle the case where storedVerificationId is not initialized
                    Toast.makeText(
                        context,
                        "Идентификатор верификации ещё не получен",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.resendCode.setOnClickListener {
            val codeNumber = binding.codeEditText.text.toString()
            resentCode(codeNumber)
        }

        return binding.root
    }

    private fun firestoreAdd() {
        if (sessionManager.restaurantName.toString() != "") {
            sessionManager.isRegistered = true
            val userDate = HashMap<String, Any>()
            userDate["FIO"] = UniversalDate.fio.toString()
            userDate["Phone"] = UniversalDate.phoneNumber.toString()

            directorDocument = firebaseFireStore
                .collection("Users")
                .document("Dates")
                .collection(sessionManager.restaurantName.toString())
                .document("User Date")
                .collection(UniversalDate.status.toString())
                .document("${UniversalDate.email}")

            if (UniversalDate.status == "") {
                Toast.makeText(context, "status pust", Toast.LENGTH_SHORT).show()
            } else if (UniversalDate.status == "Director") {
                directorDocument.update(userDate)
                    .addOnSuccessListener {
                        Toasty.info(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT)
                            .show()


                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Error ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                findNavController().navigate(R.id.directorMainScreenFragment)
                sessionManager.status = UniversalDate.status

            } else if (UniversalDate.status == "Administrator") {
                directorDocument.update(userDate)
                    .addOnSuccessListener {
                        Toasty.info(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Error ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                findNavController().navigate(R.id.adminMainMenuFragment)
                sessionManager.status = UniversalDate.status
            } else if (UniversalDate.status == "Waiter") {
                directorDocument.update(userDate)
                    .addOnSuccessListener {
                        Toasty.info(requireContext(), "Успешно добавлено", Toast.LENGTH_SHORT)
                            .show()

                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Error ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                startActivity(Intent(requireContext(), WaiterMainScreenActivity::class.java))
                sessionManager.status = UniversalDate.status
            }
        } else {
            findNavController().navigate(R.id.registrationandverification2Fragment)

        }

    }

    fun countDown() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountdownText()
            }

            override fun onFinish() {
                binding.countLinear.visibility = View.GONE
                binding.resendCode.visibility = View.VISIBLE
            }
        }

        (countDownTimer as CountDownTimer).start()

    }

    private fun updateCountdownText() {
        val seconds = (timeLeftInMillis / 1000).toInt()
        val timeLeftFormatted = String.format("%02d", seconds)
        binding.counterText.text = timeLeftFormatted
    }

    fun sendVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun resentCode(phoneNimber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNimber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendToken)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                Toasty.info(requireContext(), "Количество запросов превышена", Toasty.LENGTH_SHORT)
                    .show()
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    binding.progBar.visibility = View.GONE

                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user

                    firestoreAdd()


                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    /*
                         Toasty.error(requireContext(), "Не успешно",Toast.LENGTH_SHORT).show()
                    */
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(context, "Код введен неправильно", Toast.LENGTH_SHORT).show()
                    }
                }
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

    /*private fun replaceFragment(fragment: Fragment) {
      requireActivity().supportFragmentManager.beginTransaction()
          .replace(R.id.fragmentContainer, fragment)
          .addToBackStack(null)
          .commit()
    }
    */
}


