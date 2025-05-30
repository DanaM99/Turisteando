package com.example.turisteando

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AlertDialog

class sobre_activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Configurar el zoom de las imágenes de la galería
        setupGalleryImages()


        // Configurar los botones de redes sociales
        findViewById<View>(R.id.facebookButton).setOnClickListener {
            openSocialMedia("https://www.facebook.com/municipalidadvilladelrosario")
        }


        findViewById<View>(R.id.instagramButton).setOnClickListener {
            openSocialMedia("https://www.instagram.com/municipalidadvdr/")
        }


        // Configurar el botón de volver
        findViewById<View>(R.id.backButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }



        private fun setupGalleryImages() {
        val imageIds = listOf(
            R.id.foto1, R.id.foto2, R.id.foto3, R.id.foto4, R.id.foto5, R.id.foto6
        )


        imageIds.forEach { id ->
            findViewById<ImageView>(id).setOnClickListener { view ->
                showImageDialog((view as ImageView).drawable)
            }
        }
    }


    private fun showImageDialog(drawable: Drawable) {
        val imageView = ImageView(this).apply {
            setImageDrawable(drawable)
            adjustViewBounds = true
        }


        AlertDialog.Builder(this)
            .setView(imageView)
            .setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
            .show()
    }


    private fun openSocialMedia(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            // Si no hay aplicación para manejar el intent
            Toast.makeText(this, "No se pudo abrir el enlace", Toast.LENGTH_SHORT).show()
        }
    }
}
