package com.example.aplicacion2b_josepila

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {
    private lateinit var mensajeRecyclerView: RecyclerView
    private lateinit var cajaMensaje: EditText
    private lateinit var botonEnvio: ImageView
    private lateinit var mensajeAdaptador: MensajeAdaptador
    private lateinit var mensajeLista: ArrayList<Mensaje>
    private lateinit var mDbRef: DatabaseReference

    var recibidoValores: String? = null
    var enviadoValores: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val intent = Intent()
        val nombre = intent.getStringExtra("name")
        val recibido_uid = intent.getStringExtra("uid")
        val enviado_uid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance().getReference()

        enviadoValores = recibido_uid + enviado_uid
        recibidoValores = enviado_uid + recibido_uid
        supportActionBar?.title= nombre


        mensajeRecyclerView = findViewById(R.id.recycler_chat)
        cajaMensaje = findViewById(R.id.mensaje_cuadro)
        botonEnvio = findViewById(R.id.boton_enviar_mensaje)
        mensajeLista = ArrayList()
        mensajeAdaptador = MensajeAdaptador(this, mensajeLista)

        botonEnvio.setOnClickListener{
            val message = cajaMensaje.text.toString()
            val messageObject = Mensaje(message, enviado_uid)
            mDbRef.child("chats").child(enviadoValores!!).child("mensajes").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(recibidoValores!!).child("mensajes").push()
                        .setValue(messageObject)
                }
            cajaMensaje.setText("")

        }

    }
}