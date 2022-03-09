package com.example.aplicacion2b_josepila

class Mensaje {
    var mensaje: String? = null
    var idEmisor: String?= null
    constructor(){ }
    constructor(mensaje:String?, idEmisor: String?){
        this.mensaje = mensaje
        this.idEmisor = idEmisor
    }
}