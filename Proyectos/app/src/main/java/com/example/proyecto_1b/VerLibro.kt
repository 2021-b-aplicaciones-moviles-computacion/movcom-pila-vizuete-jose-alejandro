package com.example.proyecto_1b

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerLibro(): AppCompatActivity() {
    var biblioteca: Int = -1
    val arreglo: ArrayList<Libro> = BaseMemoria.arregloLibros
    val libros=ArrayList<Libro>()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_libros)
        biblioteca = intent.getIntExtra("idbiblioteca",0)
        Log.i("seleccion-biblioteca","${biblioteca}")
        valoresArreglo()
 //       imprimirArreglo()
        mostrarLibros()
        val texto = findViewById<TextView>(R.id.txt_nom_biblioteca)
        texto.text = BaseMemoria.arregloBiblioteca[biblioteca].nombre
    }
    fun valoresArreglo(){
        arreglo.forEachIndexed{Indice: Int,valorActual: Libro->
            if(arreglo[Indice].idBiblioteca == biblioteca){
                libros.add(arreglo[Indice])
            }
        }
    }
    fun mostrarLibros(){
        val Lista = findViewById<ListView>(R.id.lv_ver_libros)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,libros)
        Lista.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
    fun imprimirArreglo(){
        Log.i("valores",libros.toString())
    }
}