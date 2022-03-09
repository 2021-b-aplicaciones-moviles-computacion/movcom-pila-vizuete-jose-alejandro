package com.example.proyecto_1b

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
/*  var selec_biblioteca: Int =-1
    var CODIGO_RESPUESTA_INTENT_EXPLICITO: Int = 401
    var arreglo: ArrayList<Biblioteca> = BaseMemoria.arregloBiblioteca
    var arreglo_l: ArrayList<Libro> = BaseMemoria.arregloLibroszzz
*/
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_ventana_login)
    val botonLogin = findViewById<Button>(R.id.btn_registrarse)
    botonLogin.setOnClickListener {
        llamarLoginUsuario()
        }
    }
    fun irActividad(clase: Class<*>,){
        val intent = Intent(this, clase)
        startActivity(intent)
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
        irActividad(VentanaInicio::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            200->{
                if(resultCode == Activity.RESULT_OK){
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if(usuario!= null){
                        if(usuario.isNewUser == true) {
                            registrarUsuarioPrimeraVez(usuario)
                        }else{
                            setearUsuarioFirebase()
                        }
                    }
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
                    //setearUsuarioFirebase()
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
                        irActividad(VentanaInicio::class.java)
                        Log.i("firebase-firestore", "Usuario cargado")
                    }
                    .addOnFailureListener {
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
            }
        }

    }
}
