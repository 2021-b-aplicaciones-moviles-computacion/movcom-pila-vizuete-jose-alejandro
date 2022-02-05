package com.example.proyecto1_1
/*Clase 11 17-12-2021
* Creacion de almacenamiento local para base de datos*/
class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(BEntrenador("Jose","esto es nombre"))
            arregloBEntrenador
                .add(BEntrenador("Alejandro","casa"))
        }
    }
}