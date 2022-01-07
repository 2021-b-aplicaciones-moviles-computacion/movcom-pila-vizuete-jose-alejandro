package com.example.proyecto1_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class CIntentExplicitParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicit_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad",0)
        Log.i("intent","${nombre}, ${apellido}, ${edad}")

    }
}