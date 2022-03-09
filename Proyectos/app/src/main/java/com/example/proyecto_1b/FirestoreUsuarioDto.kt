package com.example.proyecto_1b


data class FirestoreUsuarioDto(
    var uid: String ="",
    var email: String = "",
    var roles: ArrayList<String> = arrayListOf())
{

}