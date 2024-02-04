package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.knopkapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroupLanguages = findViewById<RadioGroup>(R.id.radioGroupLanguages)

        val radioButtonRussian = findViewById<RadioButton>(R.id.radioButtonRussian)

        val radioButtonKazakh = findViewById<RadioButton>(R.id.radioButtonKazakh)

        radioGroupLanguages.clearCheck()

        findViewById<Button>(R.id.buttonContinuelanguage).setOnClickListener {
            if (radioGroupLanguages.checkedRadioButtonId == -1) {
                val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
                radioButtonRussian.startAnimation(bounceAnimation)
                radioButtonKazakh.startAnimation(bounceAnimation)

            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        radioGroupLanguages.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButtonRussian) {
                radioButtonRussian.setBackgroundResource(R.drawable.radio_button_background_language)
                radioButtonRussian.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_radio_checked, 0
                )
            } else {
                radioButtonRussian.setBackgroundResource(R.drawable.radio_button_background_language_false)
                radioButtonRussian.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_radio_unchecked, 0
                )
            }

            // Для казахского языка
            if (checkedId == R.id.radioButtonKazakh) {
                radioButtonKazakh.setBackgroundResource(R.drawable.radio_button_background_language)
                radioButtonKazakh.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_radio_checked, 0
                )
            } else {
                radioButtonKazakh.setBackgroundResource(R.drawable.radio_button_background_language_false)
                radioButtonKazakh.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.ic_radio_unchecked, 0
                )
            }
        }
    }
}
