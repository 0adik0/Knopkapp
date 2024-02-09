package com.knopkapp.owner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.knopkapp.R
import com.knopkapp.databinding.FragmentRegistrationandverification2Binding
import com.knopkapp.models.OwnerDates

class Registrationandverification2Fragment : Fragment() {
    private lateinit var binding: FragmentRegistrationandverification2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationandverification2Binding.inflate(layoutInflater)

        typeOfBuild()

        binding.regnext2.setOnClickListener {
            binding.apply {
                OwnerDates.bin = binEditText.text.toString().toLong()
                OwnerDates.address = addressEditText.text.toString()
                OwnerDates.name = binding.nameOfRestaurant.text.toString()
            }
            findNavController().navigate(R.id.registrationandverification3Fragment)

        }
        return binding.root
    }

    private fun typeOfBuild(){
        // Задайте список категорий
        val categories = arrayOf("Ресторан", "Бар", "Кафе", "Столовая", "Кофейня", "Кулинария", "Другое")

        // Создайте адаптер для спиннера
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)

        // Определите, какие данные отображать, когда выбрана конкретная категория
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Примените адаптер к спиннеру
        binding.typeSpinner.adapter = adapter

        // Установите слушатель выбора для спиннера
        binding.typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Обработайте выбор элемента
                val selectedCategory = categories[position]
                OwnerDates.type = selectedCategory
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Действие при отсутствии выбора
            }
        }
    }


    /*  private fun replaceFragment(fragment: Fragment) {
          activity!!.supportFragmentManager.beginTransaction()
              .replace(R.id.fragmentContainer, fragment)
              .addToBackStack(null)
              .commit()
      }*/

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