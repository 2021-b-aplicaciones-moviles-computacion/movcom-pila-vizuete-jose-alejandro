package com.example.aplicacion2b_josepila

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Registrarse : AppCompatActivity() {
    private lateinit var textEmail: EditText
    private lateinit var textContraseña: EditText
    private lateinit var botonLogin: Button
    private lateinit var botonRegistrarse: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        textEmail = findViewById(R.id.email)
        textContraseña = findViewById(R.id.password_et)
        botonLogin = findViewById(R.id.btn_loggearse)
        botonRegistrarse = findViewById(R.id.btn_registrarse)
        botonRegistrarse.setOnClickListener{
            val intent = Intent(this, Registrarse::class.java)
            startActivity(intent)
        }
        botonLogin.setOnClickListener{
            val email = textEmail.text.toString()
            val password = textContraseña.toString()
            iniciarSesion(email, password)
        }
    }
    fun iniciarSesion(email:String, contraseña:String){
        mAuth.signInWithEmailAndPassword(email,contraseña)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    val intent = Intent(this@Registrarse,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                }else{
                    Toast.makeText(this@Registrarse,"El usuario no existe",Toast.LENGTH_SHORT).show()
                }
            }
    }
}