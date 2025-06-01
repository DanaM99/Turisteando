package com.example.turisteando

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class lugares_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lugares)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBasilica = findViewById<Button>(R.id.btnBasilica)
        btnBasilica.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 1)
            startActivity(intent)
        }

        val btnCapilla = findViewById<Button>(R.id.btnCapilla)
        btnCapilla.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 2)
            startActivity(intent)
        }

        val btnCementerio = findViewById<Button>(R.id.btnCementerio)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 3)
            startActivity(intent)
        }

        val btnCvar = findViewById<Button>(R.id.btnCvar)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 4)
            startActivity(intent)
        }

        val btnFestival = findViewById<Button>(R.id.btnFestival)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 5)
            startActivity(intent)
        }

        val btnMuseo = findViewById<Button>(R.id.btnMuseo)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 6)
            startActivity(intent)
        }

        val btnPaso = findViewById<Button>(R.id.btnPaso)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 7)
            startActivity(intent)
        }

        val btnRio = findViewById<Button>(R.id.btnRio)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 8)
            startActivity(intent)
        }

        val btnPlaza = findViewById<Button>(R.id.btnPlaza)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 9)
            startActivity(intent)
        }

        val btnOratoria = findViewById<Button>(R.id.btnOratoria)
        btnCementerio.setOnClickListener {
            val intent = Intent(this, detalles_lugares_activity::class.java)
            intent.putExtra("idLugar", 10)
            startActivity(intent)
        }



    }
}