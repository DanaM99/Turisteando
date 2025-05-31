package com.example.turisteando

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class detalles_lugares_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_lugares)

        // Obtener el ID del lugar desde el Intent
        val idLugar = intent.getIntExtra("idLugar", -1)

        // Obtener los datos del lugar
        val lugar = obtenerDatosLugar(idLugar)

        // Referencias a los elementos de la interfaz
        val imgLugar = findViewById<ImageView>(R.id.imgLugar)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtDireccion = findViewById<TextView>(R.id.txtDireccion)
        val btnMapa = findViewById<Button>(R.id.btnMapa)
        val btnEscucharSpotify = findViewById<Button>(R.id.btnEscucharSpotify)
        val galeriaImagenes = findViewById<LinearLayout>(R.id.galeriaImagenes)
        val footerLayout = findViewById<LinearLayout>(R.id.footerLayout)
        val btnInstagramFooter = findViewById<ImageView>(R.id.btnInstagram)
        val btnFacebookFooter = findViewById<ImageView>(R.id.btnFacebook)
        val btnYouTubeFooter = findViewById<ImageView>(R.id.btnYoutube)
        val btnSpotifyFooter = findViewById<ImageView>(R.id.btnSpotify)

        btnInstagramFooter.setOnClickListener {
            lugar.instagram?.let {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }

        btnFacebookFooter.setOnClickListener {
            lugar.facebook?.let {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }

        btnYouTubeFooter.setOnClickListener {
            lugar.youtube?.let {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }

        btnSpotifyFooter.setOnClickListener {
            lugar.podcastSpotify?.let {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
            }
        }

        btnInstagramFooter.visibility = if (lugar.instagram != null) View.VISIBLE else View.GONE
        btnFacebookFooter.visibility = if (lugar.instagram != null) View.VISIBLE else View.GONE
        btnYouTubeFooter.visibility = if (lugar.instagram != null) View.VISIBLE else View.GONE
        btnSpotifyFooter.visibility = if (lugar.instagram != null) View.VISIBLE else View.GONE

        //Cargo la imagen principal
        Glide.with(this)
            .load(lugar.imagenPrincipal)
            .into(imgLugar)

        // Establecer el texto de los elementos
        txtTitulo.text = lugar.titulo
        txtDescripcion.text = lugar.descripcion
        txtDireccion.text = lugar.direccion

        // Configurar el botón de Mapa
        btnMapa.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(lugar.enlaceMapa)
                )
            )
        }

        // Configurar el botón de Spotify
        if (lugar.enlaceSpotify != null) {
            btnEscucharSpotify.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(lugar.enlaceSpotify)
                    )
                )
            }
        } else {
            btnEscucharSpotify.isEnabled = false
        }

        // Cargar las imágenes de la galería
        for (resId in lugar.imagenesGaleria) {
            val iv = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(500, 400).also {
                    it.setMargins(8, 0, 8, 0)
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(resId)
            }
            galeriaImagenes.addView(iv)
        }

        //Muestro footer segun el lugar
        footerLayout.visibility = if (idLugar in listOf(1 /*,2,...*/)) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun abrirUrl(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    data class LugarTuristico(
        val titulo: String,
        val descripcion: String,
        val direccion: String,
        val telefono: String,
        val email: String,
        val horarios: List<String>,
        val imagenPrincipal: Any,
        val imagenesGaleria: List<Int>,
        val enlaceMapa: String,
        val enlaceSpotify: String?,
        val instagram: String?,
        val facebook: String?,
        val youtube: String?,
        val podcastSpotify: String?
    )

    // Función para obtener los datos del lugar según su ID
    private fun obtenerDatosLugar(id: Int): LugarTuristico {
        return when (id) {
            1 -> LugarTuristico(
                titulo = "Basílica Menor Nuestra Señora del Rosario",
                descripcion = "Ubicada en el centro histórico de la ciudad, la Basílica Menor “Nuestra Señora del Rosario” es una magnífica construcción neorenacentista digna de admirar, no solo por la belleza de su arquitectura, sino también por la historia que guarda y que transmite a los habitantes. Alzada entre 1883 y 1894 gracias al esfuerzo de los párrocos y las donaciones de los fieles, se constituye como uno de los puntos más importantes y atractivos de la zona. Un edificio que daría inicio a una nueva era en nuestra ciudad, dejando atrás la impronta colonial y apostando al legado levítico por más de 100 años.",
                direccion = "25 de Mayo 800",
                telefono = "+54 9 3573 422248",
                email = "villadelrosarioturismo@gmail.com",
                horarios = listOf(
                    "Lunes: 17:00hs a 20:00hs",
                    "Martes a Domingo: 08:00 a 12:00 y 17:00 a 21:00"
                ),
                imagenPrincipal = R.drawable.basilica0,
                imagenesGaleria = listOf(
                    R.drawable.basilica1,
                    R.drawable.basilica2,
                    R.drawable.basilica3,
                    R.drawable.basilica4,
                    R.drawable.basilica5,
                    R.drawable.basilica6
                ),
                enlaceMapa = "https://maps.app.goo.gl/Qd9pWVZwcgkwvPn6A",
                enlaceSpotify = "https://open.spotify.com/playlist/5jIBWGQ4srdqBkHYnFBlRv",
                instagram = "https://www.instagram.com/municipalidadvdr/",
                facebook = "https://www.facebook.com/BasilicaVlladelRosario/",
                youtube = "https://www.youtube.com/channel/UC-IY3rRdeknO5fwr3vpLuQg?view_as=subscriber",
                podcastSpotify = "https://open.spotify.com/playlist/5jIBWGQ4srdqBkHYnFBlRv"
            )

            2 -> LugarTuristico(
                titulo = "Capilla Hermanas Adoratrices",
                descripcion = "La historia del colegio Adoratrices de Villa del Rosario se remonta al año 1890, cuando Monseñor Lindor Ferreyra tiene como iniciativa construir un edificio destinado a la educación para niñas y a la generación de nuevas maestras vinculadas al catecismo. Pero no sería hasta 1891 que comenzaría su construcción, gracias al apoyo de la Congregación de Hermanas Adoratrices. El edificio original fue construído en un terreno donado por Angela de Liendo, y si bien las clases comenzaron el 22 de junio de 1892, después de una primera etapa constructiva, la culminación del gigantesco edificio se daría más de 30 años después, en 1928. Si bien gran parte de la construcción original fue demolida en el año 1949, para levantar un año más tarde el edificio que vemos actualmente, aún quedan reminiscencias de su arquitectura que nos permiten hacernos una idea de su grandilocuencia, como lo son la Capilla, la fachada de un salón sobre la calle Monseñor Lindor Ferreyra, y un ala de aulas e internado sobre la calle Colón.",
                direccion = "Colón Nº 1015",
                telefono = "+54 9 3573 424820",
                email = "adoratricesvilla@hotmail.com",
                horarios = listOf("07:30 a 20:00"),
                imagenPrincipal = R.drawable.capilla7,
                imagenesGaleria = listOf(
                    R.drawable.capilla1,
                    R.drawable.capilla2,
                    R.drawable.capilla3,
                    R.drawable.capilla4,
                    R.drawable.capilla5,
                    R.drawable.capilla6
                ),
                enlaceMapa = "https://maps.app.goo.gl/XAv2wfAsXzYBkPx96",
                enlaceSpotify = "https://open.spotify.com/episode/04DqtaP04QE8DNTOOi7kfG?si=82_d_KQeS6WwrCczAdbw0w&nd=1&dlsi=4643d4f255ea449d",
                instagram = "",
                facebook = "",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/04DqtaP04QE8DNTOOi7kfG?si=82_d_KQeS6WwrCczAdbw0w&nd=1&dlsi=4643d4f255ea449d"
            )

            3 -> LugarTuristico(
                titulo = "Cementerio Municipal El Salvador",
                descripcion = "Dicen que recorrer un cementerio es dar un vistazo a la historia de su ciudad, y es que este espacio reúne todos los apellidos familiares, todas las profesiones, las clases sociales, y las orientaciones políticas y religiosas de los habitantes de una ciudad a lo largo de los años. Surgido a comienzos del siglo XX ante la imposibilidad de continuar dando sepultura en el viejo cementerio, el Cementerio Municipal se destaca por su particular arquitectura. Con ornamentados mausoleos alzados por reconocidos constructores de la ciudad, como Tomás Algueró y Juan M. Basile, recorrer sus pasillos se siente como recorrer una pequeña versión de Villa del Rosario. Si bien en la actualidad su uso se encuentra diezmado gracias al nuevo Cementerio Parque, este patrimonio funerario no deja de asombrar a los lugareños y visitantes, en particular aquellos atraídos por las místicas siluetas de los panteones, mausoleos y cofradías, que se observan desde las rutas circundantes.",
                direccion = "Ruta Prov. N° 10",
                telefono = "+54 9 3573 42232",
                email = "villadelrosarioturismo@gmail.com",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.cem2,
                imagenesGaleria = listOf(
                    R.drawable.cem3,
                    R.drawable.cem4,
                    R.drawable.cem5,
                    R.drawable.cem6,
                    R.drawable.cem7,
                    R.drawable.cem8
                ),
                enlaceMapa = "https://maps.app.goo.gl/LdbkRpLFbAD5wiWr8",
                enlaceSpotify = "https://open.spotify.com/episode/2hcwQrnecXWcYhcaUCy5sd",
                instagram = "https://www.instagram.com/municipalidadvdr/",
                facebook = "https://www.facebook.com/municipalidadvilladelrosario",
                youtube = "https://www.youtube.com/channel/UC-IY3rRdeknO5fwr3vpLuQg?view_as=subscriber",
                podcastSpotify = "https://open.spotify.com/episode/2hcwQrnecXWcYhcaUCy5sd"
            )

            4 -> LugarTuristico(
                titulo = "Complejo Cultrual Villa del Rosario",
                descripcion = "A solo una cuadra del centro de la ciudad se encuentra el Complejo Cultural Villa del Rosario – CVAR, un nuevo pulmón cultural en donde turistas y ciudadanos tienen garantizadas experiencias creativas, educativas, culturales e históricas, gracias a los nodos que lo componen. Con su conexión con la Plaza General Paz, el CVAR se presenta como una extensión de la misma, así como un importante punto de visita obligada y referencia cultural, no solo de Villa del Rosario, sino también de la región.",
                direccion = "San Martin 780",
                telefono = "+54 9 3573 45-4111",
                email = "",
                horarios = listOf(
                    "Lunes: 16:00hs a 20:00hs",
                    "Martes a Viernes: 08:00 a 13:00 y 16:00 a 20:00",
                    "Sábado: 16:00 a 20:00",
                    "Domingo: 16:00 a 21:00",
                    "Feriados: 16:00 a 20:00"
                ),
                imagenPrincipal = R.drawable.cvar6,
                imagenesGaleria = listOf(
                    R.drawable.cvar1,
                    R.drawable.cvar2,
                    R.drawable.cvar3,
                    R.drawable.cvar4,
                    R.drawable.cvar5,
                    R.drawable.cvar7
                ),
                enlaceMapa = "https://maps.app.goo.gl/GdL3t9E69wW872bh8",
                enlaceSpotify = "https://open.spotify.com/episode/6a5YuMNCq0CQ3A149Jthui?si=ou1xencdTc2tFH9HMZsewA&nd=1",
                instagram = "https://www.instagram.com/cvarvdr/",
                facebook = "https://www.facebook.com/CVARvdr",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/6a5YuMNCq0CQ3A149Jthui?si=ou1xencdTc2tFH9HMZsewA&nd=1"
            )

            5 -> LugarTuristico(
                titulo = "Festival Nacional del Folclore en el Agua",
                descripcion = "Con un nacimiento que se remonta a las noches del canto, risas, y asados en el Rancho del Dr. David “Bicho” Ferreyra, el Festival Nacional de Folclore en el Agua se instaura como un evento que celebra las más profundas raíces nativas. Desde su primera edición en enero de 1969, año a año recibe a las más grandes figuras del folklore nacional y latinoamericano, sobre un escenario enclavado en el mismo cauce del río Xanaes, en un espectáculo de alto valor artístico y cultural. Con 54 ediciones en su haber, el Festival Nacional de Folclore en el Agua se ha convertido en uno de los cinco festivales más importantes de la Provincia de Córdoba, así como en el evento de mayor envergadura de nuestra ciudad, atrayendo turistas de todos los rincones del país.",
                direccion = "Avenida Ángel Mastri",
                telefono = "+54 9 3573 42-2321",
                email = "villadelrosarioturismo@gmail.com",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.fes4,
                imagenesGaleria = listOf(
                    R.drawable.fes2,
                    R.drawable.fes3,
                    R.drawable.fes5,
                    R.drawable.fes6,
                    R.drawable.fes7
                ),
                enlaceMapa = "https://maps.app.goo.gl/Qn8LBroeix9tgCsY7",
                enlaceSpotify = "https://open.spotify.com/episode/1Xf90uvgcTffKEHbcm93PP",
                instagram = "https://www.instagram.com/folcloreenelagua/",
                facebook = "https://www.facebook.com/folcloreenelagua",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/1Xf90uvgcTffKEHbcm93PP"
            )

            6 -> LugarTuristico(
                titulo = "Museo Histórico Rodolfo Rivarola",
                descripcion = "Ubicado sobre Av. San Martín, y formando parte íntegra del Complejo Cultural Villa del Rosario, se encuentra el Museo Histórico “Rodolfo Rivarola”. Con el nombre de quien fuera el propulsor e ideólogo de este espacio en 1980, y funcionando en la propiedad que pertenecía a una de las familias más renombradas de la Villa: los Ferreyra. Con 6 salas y una zona institucional de recepción y orientación al visitante, su colección reúne elementos del tipo arqueológico, pictórico, de arte religioso, fotográfico, y demás artefactos de índole histórica y patrimonial. En este espacio se llevan a cabo muestras temporales y dinámicas durante todo el año, y en las que se busca que la colección del Museo dialogue con obras de artistas de alcance local, provincial y nacional.",
                direccion = "San Martin 780",
                telefono = "+54 9 3573 44-5531",
                email = "museohistoricovilladelrosario@gmail.com",
                horarios = listOf(
                    "Lunes: 16:00 a 20:00",
                    "Martes–Viernes: 08:00–13:00 y 16:00–20:00",
                    "Sábado: 16:00–20:00",
                    "Domingo: 16:00–21:00",
                    "Feriados: 16:00–20:00"
                ),
                imagenPrincipal = R.drawable.m,
                imagenesGaleria = listOf(
                    R.drawable.m1,
                    R.drawable.m5,
                    R.drawable.m6,
                    R.drawable.m7,
                    R.drawable.m8,
                    R.drawable.m9
                ),
                enlaceMapa = "https://maps.app.goo.gl/GdL3t9E69wW872bh8",
                enlaceSpotify = "https://open.spotify.com/episode/2K4ZYK3p9C1okfTYLDDNQp?si=UB-pLj6dTXOzJcjxOgB-6A&utm_source=whatsapp&nd=1",
                instagram = "https://www.instagram.com/cvarvdr/",
                facebook = "https://www.facebook.com/CVARvdr",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/2K4ZYK3p9C1okfTYLDDNQp?si=UB-pLj6dTXOzJcjxOgB-6A&utm_source=whatsapp&nd=1"
            )

            7 -> LugarTuristico(
                titulo = "Paso de las Tropas",
                descripcion = "El 9 de julio de 1816 se declara la independencia de la Corona Española, pero la nueva constitución resultó rechazada por algunas provincias, dando lugar a la guerra civil entre unitarios y federales. Es en este contexto que el unitario Juan Bautista Bustos llega a esta zona, ya que tenía un acceso más directo a La Herradura, lugar donde enfrentaría al federal Estanislao López. Esta batalla resulta en un empate, por lo que Bustos regresa a Villa del Rosario, y se suman a su comitiva los coroneles José María Paz y Gregorio Aráoz de Lamadrid. Así, el 22 de febrero de 1819 reciben al General Manuel Belgrano, junto con el grueso del Ejército del Norte, ubicando su campamento sobre las márgenes del Xanaes, pero alejados del paso del Camino Real. Estuvieron instalados por 22 días, durante los cuales planificaron la campaña, recuperaron fuerzas y recibieron ayuda de los lugareños, antes de salir nuevamente contra López y consagrarse con la victoria.",
                direccion = "Paso de las Tropa Nº 1850",
                telefono = "+54 9 3573 42-2321",
                email = "villadelrosarioturismo@gmail.com",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.pdt1,
                imagenesGaleria = listOf(
                    R.drawable.pdt4,
                    R.drawable.pdt5,
                    R.drawable.pdt6,
                    R.drawable.pd5
                ),
                enlaceMapa = "https://maps.app.goo.gl/zuSwzTTfmrqioEtU9",
                enlaceSpotify = "https://open.spotify.com/episode/6UWVkuzkuQxdOSYwNk9CuI?si=UJDmqh42RAGjxN2qIO2WYg&nd=1",
                instagram = "https://www.instagram.com/municipalidadvdr/",
                facebook = "https://www.facebook.com/municipalidadvilladelrosario",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/6UWVkuzkuQxdOSYwNk9CuI?si=UJDmqh42RAGjxN2qIO2WYg&nd=1"
            )

            8 -> LugarTuristico(
                titulo = "Río Xanaes",
                descripcion = "Con una extensión de 3 kilómetros…",
                direccion = "Av. Ángel Mastri",
                telefono = "+54 9 3573 42-2321",
                email = "villadelrosarioturismo@gmail.com",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.rio4,
                imagenesGaleria = listOf(
                    R.drawable.rio1,
                    R.drawable.rio2,
                    R.drawable.rio3,
                    R.drawable.rio5,
                    R.drawable.rio6,
                    R.drawable.rio
                ),
                enlaceMapa = "https://maps.app.goo.gl/7Yk1sSUaUqLFDzPq9",
                enlaceSpotify = "https://open.spotify.com/episode/4jvyABaqkDppLU9ELzscGH",
                instagram = "https://www.instagram.com/municipalidadvdr/",
                facebook = "https://www.facebook.com/municipalidadvilladelrosario",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/4jvyABaqkDppLU9ELzscGH"
            )

            9 -> LugarTuristico(
                titulo = "Plaza General Paz",
                descripcion = "Ya sea Plaza Mayor, Plaza San Martín o Plaza General Paz; con piso de tierra, ladrillo o mosaico; con bancos de madera o cemento; la plaza es el epicentro del desarrollo urbano de nuestra ciudad. Trazada originalmente en 1795 por Dalmacio Vélez Sarsfield buscando fundar la Villa Real del Rosario, la plaza ha visto la construcción, demolición, y reconstrucción de innumerables edificios del tipo religioso, político e histórico, siendo testigo silencioso de la historia y el progreso de la villa. Su fácil acceso, sus veredas amplias, sus fuentes de agua, su vegetación y sus zonas de esparcimiento para la familia son algunas de las particularidades que la convirtieron, a lo largo de los años, en el punto de encuentro predilecto de los ciudadanos.",
                direccion = "25 de Mayo 800",
                telefono = "+54 9 3573 42-2321",
                email = "villadelrosarioturismo@gmail.com",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.p,
                imagenesGaleria = listOf(
                    R.drawable.p2,
                    R.drawable.p3,
                    R.drawable.p1
                ),
                enlaceMapa = "https://maps.app.goo.gl/xXoxDd9x1dAYtPXVA",
                enlaceSpotify = "https://open.spotify.com/episode/3DhkFZI9ySVH4Y29rzvlbS",
                instagram = "https://www.instagram.com/municipalidadvdr/",
                facebook = "https://www.facebook.com/municipalidadvilladelrosario",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/3DhkFZI9ySVH4Y29rzvlbS"
            )

            10 -> LugarTuristico(
                titulo = "Primera Población y Oratorio de San José de los Ranchos",
                descripcion = "El pueblo de Nobosacat fue uno de los primeros asentamientos de los que se tiene constancia en la zona. Se encontraba a las márgenes del Río Xanaes, y en él vivían pacificamente miembros del pueblo sanavirón y comechingón. Incluído en el sistema de encomiendas españolas en 1573, que buscaba sacar provecho del trabajo aborigen a cambio de “protección y evangelización”, el pueblo de Nabosacate quedó bajo la custodia de varios estancieros españoles a lo largo de los años, hasta finalmente terminar bajo la órbita de Gerónimo de Funes, en 1645. Pero no sería hasta 1694 que el cacique Juan Pibala pudiera repartir las tierras a los naturales para el desarrollo de quintas sobre la margen del río, organizándose en ranchos agrupados por familias, y adquiriendo el rango de residentes de un poblado. Este pequeño asentamiento fue denominado “San José” por don Funes, pero el nombre de “Los Ranchos” fue el que más perduró. Con una población más sólida y la necesidad de rendir culto a su religión, aquí comenzaría la construcción de un nuevo oratorio con cementerio, un espacio muy humilde, pero que pronto se convertiría en el principal espacio de culto de la zona.",
                direccion = "San José Esq. Norberto Dutari",
                telefono = "+54 9 3573 422321",
                email = "villadelrosarioturismo@gmail.com",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.sjo,
                imagenesGaleria = listOf(
                    R.drawable.sj2,
                    R.drawable.sj3,
                    R.drawable.sj4,
                    R.drawable.sj5,
                    R.drawable.sj6,
                    R.drawable.sj7
                ),
                enlaceMapa = "https://maps.app.goo.gl/9Fir82x6fG2MLGnY7",
                enlaceSpotify = "https://open.spotify.com/episode/0UGo0gJlQ4vwz55vtQtVrR",
                instagram = "https://www.instagram.com/municipalidadvdr/",
                facebook = "https://www.facebook.com/municipalidadvilladelrosario",
                youtube = "",
                podcastSpotify = "https://open.spotify.com/episode/0UGo0gJlQ4vwz55vtQtVrR"
            )

            else -> LugarTuristico(
                titulo = "Lugar no encontrado",
                descripcion = "No se encontró información para este lugar.",
                direccion = "",
                telefono = "",
                email = "",
                horarios = emptyList(),
                imagenPrincipal = R.drawable.image_not_found,
                imagenesGaleria = emptyList(),
                enlaceMapa = "",
                enlaceSpotify = "",
                instagram = "",
                facebook = "",
                youtube = "",
                podcastSpotify = ""
            )
        }
    }

    fun abrirInstagram(view: View) {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/municipalidadvdr/"))
        startActivity(intent)
    }

    fun abrirFacebook(view: View) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.facebook.com/municipalidadvilladelrosario")
        )
        startActivity(intent)
    }

    fun abrirYouTube(view: View) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/channel/UC-IY3rRdeknO5fwr3vpLuQg")
        )
        startActivity(intent)
    }

    fun abrirSpotifyFooter(view: View) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://open.spotify.com/show/5Ccc7JnbIv2MwZh7XHKDXK")
        )
        startActivity(intent)
    }

}