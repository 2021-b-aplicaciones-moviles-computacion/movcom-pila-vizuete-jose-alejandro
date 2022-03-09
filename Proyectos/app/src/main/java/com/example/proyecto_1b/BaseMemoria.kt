package com.example.proyecto_1b

import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



class BaseMemoria {
    /*companion object {
        val arregloBiblioteca = arrayListOf<Biblioteca>()
        val arregloLibros = arrayListOf<Libro>()

        init {
            arregloBiblioteca.add(Biblioteca("Biblioteca Central","Av.10 de Agosto y Palmera",2321234))
            arregloBiblioteca.add(Biblioteca("Biblioteca EPN"," Ladrón de Guevara E11·253",29763000))
            arregloLibros.add(Libro(0,"Ciencias de la Computación", "Carles Vernes",1,true))
            arregloLibros.add(Libro(1,"Programación Avanzada C/C++","Carlos Vinueza",2,true))
            arregloLibros.add(Libro(1,"Internetworking","Andrew Rio",3,true))
            arregloLibros.add(Libro(0,"Medicina General y Allegados","Carmen Gonzales",1,false))
            arregloLibros.add(Libro(2,"Biología Marina","Carmen Gonzales",1,false))
            arregloLibros.add(Libro(2,"Historia del Ecuador","Enrique Ayala Mora",1,false))
        }
    }*/
    fun CargarDatosBiblioteca(){
        val db = Firebase.firestore
        val biblioteca = db.collection("bibliotecas")
        if(biblioteca.get()==null) {
            val data1 = hashMapOf(
                "nombre" to "Biblioteca Central",
                "direccion" to "Av.10 de Agosto y Palmera",
                "numero" to 2321234
            )
            biblioteca.document("B1").set(data1)
            val data2 = hashMapOf(
                "nombre" to "Biblioteca EPN",
                "direccion" to " Ladrón de Guevara E11·253",
                "numero" to 29763000
            )
            biblioteca.document("B1").set(data2)
        }
    }
    fun CargarDatosLibros(){
        val db = Firebase.firestore
        val libro = db.collection("Libros")
        if(libro.get()==null){
            val data1 = hashMapOf(
                "nombre" to "Ciencias de la Computación",
                "autor" to "Carles Vernes",
                "biblioteca" to "Biblioteca Central",
                "edicion" to 1,
                "disponibilidad" to true
            )
        libro.document("L1").set(data1)
            val data2 = hashMapOf(
                "nombre" to "Programación Avanzada C/C++",
                "autor" to "Carlos Vinueza",
                "biblioteca" to "Biblioteca EPN",
                "edicion" to 2,
                "disponibilidad" to true
            )
            libro.document("L2").set(data2)
            val data3 = hashMapOf(
                "nombre" to "Internetworking",
                "autor" to "Andrew Rio",
                "biblioteca" to "Biblioteca EPN",
                "edicion" to 3,
                "disponibilidad" to true
            )
            libro.document("L3").set(data3)
        }
    }
}