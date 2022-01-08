package com.example.proyecto1_1

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