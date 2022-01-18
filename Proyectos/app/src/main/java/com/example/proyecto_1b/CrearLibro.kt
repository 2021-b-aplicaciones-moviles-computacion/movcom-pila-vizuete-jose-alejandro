package com.example.proyecto_1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CrearLibro: AppCompatActivity() {
    var arregloLibro: ArrayList<Libro> = BaseMemoria.arregloLibros
    var selec_biblioteca: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_libro)
        selec_biblioteca = intent.getIntExtra("idbiblioteca", 0)
        val bib = findViewById<TextView>(R.id.txt_biblioteca_registrar)
        bib.setText("Registro de Libro para la ${BaseMemoria.arregloBiblioteca[selec_biblioteca].nombre}")
        val nomb = findViewById<EditText>(R.id.txt_nombre_libro)
        val aut = findViewById<EditText>(R.id.txt_autor_libro)
        val edic = findViewById<EditText>(R.id.txt_edic_libro)
        val disp = findViewById<Switch>(R.id.sw_disponibilidad)

//        Log.i("valor-switch","${bool}")


        val btn_crear = findViewById<Button>(R.id.btn_crear_libro)
        val btn_volver = findViewById<Button>(R.id.btn_volver_libro)

        btn_crear.setOnClickListener {

            val bool: Boolean = disp.isChecked
            crearLibro(
                nomb.text.toString(),
                aut.text.toString(),
                Integer.parseInt(edic.text.toString()),
                bool
            )
        }
        btn_volver.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun crearLibro(nombre: String, autor: String, edicion: Int, disponible: Boolean) {
        arregloLibro.add(Libro(selec_biblioteca, nombre, autor, edicion, disponible))
        dialogoCrear()
    }

    fun dialogoCrear() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cuadro de confirmación")
        builder.setMessage("Se ha ingresado el libro correctamente")
        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(clase: Class<*>, ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}