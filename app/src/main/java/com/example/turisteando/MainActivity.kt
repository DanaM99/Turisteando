package com.example.turisteando

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLugares = findViewById<Button>(R.id.btnLugares)
        val btnSugerir = findViewById<Button>(R.id.btnSugerir)
        val btnSobre = findViewById<Button>(R.id.btnSobre)

        btnLugares.setOnClickListener {
            startActivity(Intent(this, lugares_activity::class.java))
        }

        btnSugerir.setOnClickListener {
            startActivity(Intent(this, sugerir_activity::class.java))
        }

        btnSobre.setOnClickListener {
            startActivity(Intent(this, sobre_activity::class.java))
        }
    }
}