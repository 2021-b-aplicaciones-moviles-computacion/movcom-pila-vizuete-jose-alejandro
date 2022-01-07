package com.example.proyecto1_1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ACicloVida : AppCompatActivity() {
    var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aciclo_vida)
        Log.d("ciclo-vida","onCreate")
        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            aumentarTotal()
        }
    }
    fun aumentarTotal(){
        total += 1
        val textViewCicloVida = findViewById<TextView>(R.id.txt_ciclo_vida)
        textViewCicloVida.text = total.toString()
    }

    override fun onSaveInstanceState(outState: Bundle){
            outState.run{
            putInt("totalGuardado", total)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val totalRecuperado: Int? = savedInstanceState.getInt("totalGuardado")
        if(totalRecuperado != null){
            this.total = totalRecuperado
            val txtCicloVida = findViewById<TextView>(R.id.txt_ciclo_vida)
            txtCicloVida.text = total.toString()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("ciclo-vida","onStart")
    }

    override fun onResume(){
        super.onResume()
        Log.d("ciclo-vida", "onResume")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("ciclo-vida", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ciclo-vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ciclo.vida", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ciclo-vida", "onDestroy")
    }
}