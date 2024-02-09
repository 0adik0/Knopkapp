package com.knopkapp.waiter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.knopkapp.R
import com.knopkapp.adapters.WaiterTablesAdapter
import com.knopkapp.databinding.ActivityWaiterTablesBinding

class WaiterTablesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWaiterTablesBinding
    lateinit var waiterTablesAdapter: WaiterTablesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaiterTablesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*waiterTablesAdapter = WaiterTablesAdapter() //нужно добивать ArrayList
        binding.tablesRv.adapter = waiterTablesAdapter */
    }
}