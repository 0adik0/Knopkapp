package com.knopkapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knopkapp.adapters.MoodAdapter
import com.knopkapp.R
import com.knopkapp.databinding.ActivityMoodBinding
import com.knopkapp.db.SessionManager

class MoodActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var moodAdapter: MoodAdapter

    private lateinit var binding: ActivityMoodBinding
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        val moods = listOf(
            "\uD83D\uDE34 Хочу спать",
            "\uD83D\uDE10 Нормально",
            "\uD83D\uDE0A Хорошо",
            "\uD83D\uDE04 Отлично",
            "\uD83D\uDE0E Всё шикарно"
        )

        moodAdapter = MoodAdapter(moods) { mood ->

        }

        recyclerView = findViewById(R.id.moodRecyclerView)
        // Используйте GridLayoutManager с 3 столбцами
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = moodAdapter

        binding.buttonSaveMood.setOnClickListener {

            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)

        }
    }
}