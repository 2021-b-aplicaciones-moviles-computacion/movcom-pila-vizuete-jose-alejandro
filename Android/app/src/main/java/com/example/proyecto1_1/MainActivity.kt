package com.example.proyecto1_1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    //Codigo para la respuesta que se va a recibir del INTENT EXPLICITO
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    )
    { result ->
        /*Clase 11 17-12-2021*/
        if (result.resultCode == Activity.RESULT_OK) {
            if(result.data != null) {
                val data: Intent? = result.data
                Log.i("intent", "${data?.getStringExtra("nombremodificado")}")
                Log.i("intent", "${data?.getIntExtra("edadmodificada", 0)}")
            }
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
        /*Clase 12*/
        val botonIrIntIMplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIrIntIMplicito
            .setOnClickListener{
                val intentConRespuesta = Intent(
                    Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                )
                startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)

            }
        /*Clase 14*/
        EBaseDeDatos.tablaUsuario = ESqliteHelperUsuario(this)
        if(EBaseDeDatos.tablaUsuario !=null){
            EBaseDeDatos.tablaUsuario?.crearUsuarioFormulario(
                "Jose","Estudiante de la EPN")

            var consulta = EBaseDeDatos.tablaUsuario?.consultarUsuarioporId(1)
            Log.i("primera-consulta", "${consulta?.nombre}")

            EBaseDeDatos.tablaUsuario?.actualizarUsuarioFormulario(
                "Jose Pila", "Estudiante de la EPN", 1)
            consulta = EBaseDeDatos.tablaUsuario?.consultarUsuarioporId(1)
            Log.i("primera-consulta", "${consulta?.nombre}")

            EBaseDeDatos.tablaUsuario?.eliminarUsuarioFormulario(1)
            consulta = EBaseDeDatos.tablaUsuario?.consultarUsuarioporId(1)
            Log.i("primera-consulta", "${consulta?.nombre}")
        }

        /*Clase 15*/
        val btn_RecyclerView = findViewById<Button>(R.id.btn_ir_recycler_view)
        btn_RecyclerView
            .setOnClickListener{
                irActividadConParametros(GRecyclerView::class.java)
            }
        /*Clase 18 - HTTP*/
        val btn_irHttp = findViewById<Button>(R.id.btn_ir_http)
        btn_irHttp
            .setOnClickListener{
                irActividadConParametros(HHttpActivity::class.java)
            }
    }
    fun irActividad(clase: Class<*>,) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    //Crear conexion a otra Actividad con parametros
    fun irActividadConParametros(clase: Class<*>,){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("nombre", "Jose")
        intentExplicito.putExtra("apellido","Pila")
        intentExplicito.putExtra("edad",24)
        intentExplicito.putExtra("entrenador", BEntrenador("a","b"))
    /*Clase 11 17-12-2021*/
        //resultLauncher.launch(intentExplicito)
    /*Clase 12 21-12-2021*/
        //startActivityForResult(intent,CODIGO_RESPUESTA_INTENT_EXPLICITO)
        startActivityForResult(intentExplicito,CODIGO_RESPUESTA_INTENT_EXPLICITO)
       /* registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            when(it.resultCode){
                Activiy.RESULT_OK ->
                {

                }
            }
        }*/
    }

    /*Clase 12 21-12-2021*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO ->{
                if(resultCode == RESULT_OK){
                    Log.i("intent-epn","${data?.getStringExtra("nombremodificado")}")
                }else(resultCode == RESULT_CANCELED)
                    Log.i("intent-epn","Cancelado")
            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO ->{
                if(resultCode == RESULT_OK)
                {
                    if(data != null)
                    {
                        if(data.data !=null)
                        {
                            val uri: Uri = data.data!!
                            val cursor =  contentResolver.query(
                                uri,null,null,null,null,null)
                            cursor?.moveToFirst()
                            val indiceTelf = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelf!!
                            )
                            cursor?.close()
                            Log.i("telefono","Telefono: ${telefono}")
                        }
                    }
                }
            }
        }
    }


}