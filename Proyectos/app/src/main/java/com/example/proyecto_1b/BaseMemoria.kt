package com.example.proyecto_1b

class BaseMemoria {
    companion object {
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
    }
}