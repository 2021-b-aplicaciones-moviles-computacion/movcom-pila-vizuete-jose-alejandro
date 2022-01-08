package com.example.proyecto1_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    //Codigo para la respuesta que se va a recibir del INTENT EXPLICITO
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
            {
            result ->
                if(result.resultCode ==Activity.RESULT_OK)
                 {
                 val data: Intent? = result.data
                 Log.i("intent","${data?.getStringExtra("nombremodificado")}")
                 Log.i("intent", "${data?.getIntExtra("edadmodificada", 0)}")
                }
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*tomar valores del contenido de la ventana*/
        setContentView(R.layout.activity_main)
        /*Permite establecer una accion para el boton*/
        val botonCicloVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)//Permite tomar los objetos de la interfaz
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }
        val botonIrListView =findViewById<Button>(R.id.btn_ir_list_view)
        botonIrListView.setOnClickListener{
            irActividad(BListView::class.java)
        }
        //Desarrollo de boton para Activity IntentExplicito
        val botonIntentExplicito = findViewById<Button>(R.id.btn_intent)
        botonIntentExplicito
            .setOnClickListener{
                irActividadConParametros(CIntentExplicitParametros::class.java)
        }

    }
    fun irActividad(clase: Class<*>,){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    //Crear conexion a otra Actividad con parametros
    fun irActividadConParametros(clase: Class<*>,){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombre", "Jose")
        intentExplicito.putExtra("apellido","Pila")
        intentExplicito.putExtra("edad",24)
        startActivityForResult(intent,CODIGO_RESPUESTA_INTENT_EXPLICITO)
        resultLauncher.launch(intentExplicito)

       /* registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            when(it.resultCode){
                Activity.RESULT_OK ->
                {

                }
            }
        }*/
    }
}