package com.example.proyecto1_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicit_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad",0)
        Log.i("intent","${nombre}, ${apellido}, ${edad}")

        //llamado al boton
        val boton = findViewById<Button>( R.id.btn_devolver_respuesta)
        boton.setOnClickListener{
            devolverRespuesta()
        }
    }
    //Funcion para retorno
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombremodificado","Alejandro")
        intentDevolverParametros.putExtra("edadmofdificada","25")
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish() //metodo para terminara el Intent

    }
}