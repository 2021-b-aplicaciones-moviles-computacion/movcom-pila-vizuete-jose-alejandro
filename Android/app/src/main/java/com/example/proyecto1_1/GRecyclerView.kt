package com.example.proyecto1_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
/*Clase 17*/
class GRecyclerView: AppCompatActivity() {
    var totalLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador.add(
            BEntrenador("Jose","1726008301")
        )
        listaEntrenador.add(
            BEntrenador("Andres","0512884391")
        )
        val recyclerViewEntrenador= findViewById<RecyclerView>(R.id.rv_Entrenador)
        inicializarRecyclerView(listaEntrenador, this, recyclerViewEntrenador)
    }
    fun inicializarRecyclerView(lista: List<BEntrenador>,
    actividad: GRecyclerView, recyclerView: RecyclerView){
        /*Accedemos al adaptador*/
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            actividad, lista, recyclerView)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }
    fun aumentarTotalLikes(){
        totalLikes = totalLikes+1
        val textView = findViewById<TextView>(R.id.tv_total_likes)
        textView.text = totalLikes.toString()
    }
}