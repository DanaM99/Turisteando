package com.example.turisteando

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class sugerir_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugerir)

        val nombreLugar = findViewById<EditText>(R.id.editTextNombre)
        val descripcion = findViewById<EditText>(R.id.editTextDescripcion)
        val recomendacion = findViewById<EditText>(R.id.editTextRecomendacion)
        val botonEnviar = findViewById<Button>(R.id.btnEnviar)

        botonEnviar.setOnClickListener {
            val nombre = nombreLugar.text.toString()
            val desc = descripcion.text.toString()
            val reco = recomendacion.text.toString()

            if (nombre.isNotBlank() && desc.isNotBlank() && reco.isNotBlank()) {
                Toast.makeText(this, "¡Gracias por tu sugerencia!", Toast.LENGTH_SHORT).show()
                nombreLugar.text.clear()
                descripcion.text.clear()
                recomendacion.text.clear()
            } else {
                Toast.makeText(this, "Por favor completá todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}