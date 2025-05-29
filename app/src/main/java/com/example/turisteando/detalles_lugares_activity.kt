package com.example.turisteando

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detalles_lugares_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_lugares)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lugar = intent.getStringExtra("lugar")

        when (lugar) {
            "paraiso" -> {
                findViewById<TextView>(R.id.txtTitulo).text = "Parque Natural El Paraíso"
                findViewById<ImageView>(R.id.imgLugar).setImageResource(R.drawable.parque_paraiso)
                findViewById<TextView>(R.id.txtDescripcion).text =
                    "Un espacio natural ideal para disfrutar de caminatas, aire puro y tranquilidad en Villa del Rosario."
            }

            "termas" -> {
                findViewById<TextView>(R.id.txtTitulo).text = "Termas Villa del Rosario"
                findViewById<ImageView>(R.id.imgLugar).setImageResource(R.drawable.villa)
                findViewById<TextView>(R.id.txtDescripcion).text =
                    "Un complejo termal reconocido por sus aguas curativas, piscinas y espacios de relax en contacto con la naturaleza."
            }
        }

    }
}