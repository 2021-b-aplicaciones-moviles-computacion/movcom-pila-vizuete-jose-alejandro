package com.example.aplicacion2b_josepila

class Usuario {
    var nombre: String? = null
    var email: String?= null
    var uid: String? = null

    constructor()
    constructor(nombre:String?, email:String?, uid: String?){
        this.nombre = nombre
        this.email = email
        this.uid = uid
    }
}