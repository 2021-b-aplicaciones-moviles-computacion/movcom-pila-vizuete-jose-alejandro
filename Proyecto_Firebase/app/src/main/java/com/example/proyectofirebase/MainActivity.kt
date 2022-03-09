package com.example.proyectofirebase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonLogin = findViewById<Button>(R.id.btn_logging)
        botonLogin. setOnClickListener{
            llamarLoginUsuario()
        }
        val botonLogOut = findViewById<Button>(R.id.btn_logout)
        botonLogOut
            .setOnClickListener{
                solicitarSalirAplicativo()
            }
        val botonProducto = findViewById<Button>(R.id.btn_irproducto)
        botonProducto.setOnClickListener{
            val intent = Intent(
                this,
                CProducto::class.java
            )
            startActivity(intent)

        }
        val botonRestaurant = findViewById<Button>(R.id.btn_ir_restaurante)
        botonRestaurant.setOnClickListener{
            val intent = Intent(
                this, DRestaurant::class.java
            )
            startActivity(intent)

        }
    }
    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls("https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),200
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode:Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            200 -> {
                if(resultCode == Activity.RESULT_OK){
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if(usuario !=null){
                        if(usuario.isNewUser == true) {
                            Log.i("firebase-login","Nuevo Usuario")
                            registrarUsuarioPrimeraVez(usuario)
                        } else {
                           setearUsuarioFirebase()
                            Log.i("firebase-login","Usuario Antiguo")
                        }
                    }
                }else{
                    Log.i("firebase-login", "Usuario Cancela Accion")
                }
            }
        }
    }
    fun registrarUsuarioPrimeraVez(usuario:IdpResponse){
        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .getCurrentUser()
        if(usuario.email != null && usuarioLogeado != null){
            val db = Firebase.firestore
            val roles = arrayListOf("usuario")
            val email= usuario.email
            val uid = usuarioLogeado.uid
            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to roles,
                "uid" to uid,
                "email" to email.toString()
            )
            db.collection("usuario")
                .document(email.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firebase-login","Se creo el usuario")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener{
                    Log.i("firebase-login","Fallo la creación de usuario")
                }
        }else{
            Log.i("firebase-login","Error no email ni usuario")
        }
    }
    fun setearUsuarioFirebase() {
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser
        if(usuarioLocal != null){
            if (usuarioLocal.email != null) {
                val db = Firebase.firestore
                val referencia = db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString()) // /usuario/a@...com
                referencia
                    .get()
                    .addOnSuccessListener {
                        val usuarioCargado: FirestoreUsuarioDto? =
                            it.toObject(FirestoreUsuarioDto::class.java)
                        if(usuarioCargado != null){
                            BAuthUsuario.usuario = usuarioCargado
                        }
                        setearBienvenida()
                        Log.i("firebase-firestore", "Usuario cargado")
                    }
                    .addOnFailureListener {
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
            }
        }

    }
    fun setearBienvenida(){
        val textBienvenida = findViewById<TextView>(R.id.textView)
        val botonLogin = findViewById<Button>(R.id.btn_logging)
        val botonLogOut = findViewById<Button>(R.id.btn_logout)
        val botonProducto = findViewById<Button>(R.id.btn_irproducto)
        val botonRestaurant = findViewById<Button>(R.id.btn_ir_restaurante)
        Log.i("firebase-login",BAuthUsuario.usuario.toString())
        if (BAuthUsuario.usuario!=null){
            textBienvenida.text = "Bienvenido usuario ${BAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonLogOut.visibility = View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonRestaurant.visibility = View.VISIBLE

        }
        else{
            textBienvenida.text = "Ingrese al aplicativo"
            botonLogOut.visibility = View.INVISIBLE
            botonLogin.visibility = View.VISIBLE
            botonProducto.visibility = View.INVISIBLE
            botonRestaurant.visibility = View.INVISIBLE
        }

    }
    fun solicitarSalirAplicativo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnSuccessListener {
                BAuthUsuario?.usuario = null
                setearBienvenida()
            }
    }


}