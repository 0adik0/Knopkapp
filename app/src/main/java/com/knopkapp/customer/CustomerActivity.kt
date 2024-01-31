package com.knopkapp.customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.knopkapp.R
import com.knopkapp.databinding.ActivityCustomerBinding

class CustomerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioCommands.clearCheck()
        binding.apply {

            sendButton.setOnClickListener {

                if (binding.radioCommands.checkedRadioButtonId == -1) {
                    val bounceAnimation =
                        AnimationUtils.loadAnimation(this@CustomerActivity, R.anim.bounce)
                    billRadio.startAnimation(bounceAnimation)
                    waiterRadio.startAnimation(bounceAnimation)
                    callHookahMakerRadio.startAnimation(bounceAnimation)
                    instrumentsRadio.startAnimation(bounceAnimation)

                } else {
                    hideMainScreen()
                    //Здесь будет код чтобы отправит пуш уведомление с Firebase
                }

            }

            backToMainButton.setOnClickListener {
                showMainScreen()
            }

            closeButton.setOnClickListener {
                finishAffinity()
            }
            

            radioButtons()

        }

    }

    private fun hideMainScreen() {
        binding.apply {
            mainScreen.visibility = View.GONE
            completeAnimation.visibility = View.VISIBLE
            closeButton.visibility = View.VISIBLE
            backToMainButton.visibility = View.VISIBLE
            completeAnimation.playAnimation()
        }
    }

    private fun showMainScreen() {
        binding.apply {
            mainScreen.visibility = View.VISIBLE
            completeAnimation.visibility = View.GONE
            closeButton.visibility = View.GONE
            backToMainButton.visibility = View.GONE
        }
    }

    private fun radioButtons() {

        binding.apply {

            radioCommands.setOnCheckedChangeListener { group, checkedId ->

//для кнопки "счет"
                if (checkedId == R.id.billRadio) {
                    billRadio.setBackgroundResource(R.drawable.radio_button_background_language)
                    billRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_checked, 0
                    )
                } else {
                    billRadio.setBackgroundResource(R.drawable.radio_button_background_language_false)
                    billRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_unchecked, 0
                    )
                }

                //для кнопки "официанта"
                if (checkedId == R.id.waiterRadio) {
                    waiterRadio.setBackgroundResource(R.drawable.radio_button_background_language)
                    waiterRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_checked, 0
                    )
                } else {
                    waiterRadio.setBackgroundResource(R.drawable.radio_button_background_language_false)
                    waiterRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_unchecked, 0
                    )
                }

                //для кнопки "кальянщика"
                if (checkedId == R.id.callHookahMakerRadio) {
                    callHookahMakerRadio.setBackgroundResource(R.drawable.radio_button_background_language)
                    callHookahMakerRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_checked, 0
                    )
                } else {
                    callHookahMakerRadio.setBackgroundResource(R.drawable.radio_button_background_language_false)
                    callHookahMakerRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_unchecked, 0
                    )
                }

                //для кнопки "приборы"
                if (checkedId == R.id.instrumentsRadio) {
                    instrumentsRadio.setBackgroundResource(R.drawable.radio_button_background_language)
                    instrumentsRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_checked, 0
                    )
                } else {
                    instrumentsRadio.setBackgroundResource(R.drawable.radio_button_background_language_false)
                    instrumentsRadio.setCompoundDrawablesWithIntrinsicBounds(
                        0, 0, R.drawable.ic_radio_unchecked, 0
                    )
                }
            }
        }
    }

}