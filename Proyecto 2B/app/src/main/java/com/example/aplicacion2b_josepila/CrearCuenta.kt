package com.example.aplicacion2b_josepila

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CrearCuenta : AppCompatActivity() {
    private lateinit var textEmail: EditText
    private lateinit var textContraseña: EditText
    private lateinit var textNombre: EditText
    private lateinit var botonRegistrarse: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        supportActionBar?.hide()

        textEmail = findViewById(R.id.email)
        textContraseña = findViewById(R.id.password_et)
        textNombre = findViewById(R.id.name)
        mAuth = FirebaseAuth.getInstance()
        botonRegistrarse = findViewById(R.id.btn_registrarse)
        botonRegistrarse.setOnClickListener{
            val nombre= textNombre.text.toString()
            val email = textEmail.text.toString()
            val contraseña = textContraseña.text.toString()



            registrarUsuario(nombre, email, contraseña)
        }
    }
    fun registrarUsuario(nombre: String, email:String, contraseña:String) {
        mAuth.createUserWithEmailAndPassword(email,contraseña)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful) {
                    agregarUsuarioBase(nombre, email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@CrearCuenta, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@CrearCuenta,"Error inesperado", Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun agregarUsuarioBase(nombre: String, email:String, uid:String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(Usuario(nombre,email, uid))



    }
}