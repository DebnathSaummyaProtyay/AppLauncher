package com.example.applauncher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLaunchCalculator = findViewById<Button>(R.id.btnLaunchCalculator)
        val btnLaunchTaskManager = findViewById<Button>(R.id.btnLaunchTaskManager)

        btnLaunchCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java).apply {
                putExtra("INITIAL_DATA", "Some initial data for Calculator")
            }
            startActivity(intent)
        }

        btnLaunchTaskManager.setOnClickListener {
            val intent = Intent(this, TaskManagerActivity::class.java).apply {
                putExtra("INITIAL_DATA", "Some initial data for Task Manager")
            }
            startActivity(intent)
        }
    }
}
