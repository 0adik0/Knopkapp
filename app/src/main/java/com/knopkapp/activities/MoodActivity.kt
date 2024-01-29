package com.knopkapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knopkapp.MoodAdapter
import com.knopkapp.R

class MoodActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var moodAdapter: MoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood)

        val moods = listOf("\uD83D\uDE34 Хочу спать", "\uD83D\uDE10 Нормально", "\uD83D\uDE0A Хорошо", "\uD83D\uDE04 Отлично", "\uD83D\uDE0E Всё шикарно")

        moodAdapter = MoodAdapter(moods) { mood ->

        }

        recyclerView = findViewById(R.id.moodRecyclerView)
        // Используйте GridLayoutManager с 3 столбцами
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = moodAdapter

        findViewById<Button>(R.id.buttonSaveMood).setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}