package com.example.proyecto_1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VentanaInicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_login)
        cargarDatosBiblioteca()
        consultaBibliotecas()
        val botonCrear = findViewById<Button>(R.id.btn_crear_biblioteca)
        botonCrear.setOnClickListener{
            irActividad(CrearBiblioteca::class.java)
        }
    }
    fun irActividad(clase: Class<*>,){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    fun cargarDatosBiblioteca(){
        val db = Firebase.firestore
        val biblioteca = db.collection("bibliotecas")
        if(biblioteca.get() == null) {
            val data1 = hashMapOf(
                "nombre" to "Biblioteca Central",
                "direccion" to "Av.10 de Agosto y Palmera",
                "numero" to 2321234
            )
            biblioteca.document("B1").set(data1)
            val data2 = hashMapOf(
                "nombre" to "Biblioteca EPN",
                "direccion" to " Ladrón de Guevara E11·253",
                "numero" to 29763000
            )
            biblioteca.document("B1").set(data2)
        }
    }
    fun consultaBibliotecas(){
        val db = Firebase.firestore
        val arregloLista: ArrayList<String> = ArrayList()
        val referencia = db

            .collection("bibliotecas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    arregloLista.add("${document.id} ->${document.data}")
                    mostrarLista(arregloLista)
                    añadirMenuLista(arregloLista)
                }
            }

    }
    fun mostrarLista(arreglo:ArrayList<String>)
    {
        val Lista = findViewById<ListView>(R.id.list_biblioteca)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,arreglo)
        Lista.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
    fun añadirMenuLista(arreglo: ArrayList<String>){
        val lista = findViewById<ListView>(R.id.list_biblioteca)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,arreglo)
        lista.adapter = adaptador
        registerForContextMenu(lista)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_opciones_biblioteca,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
      //  selec_biblioteca = id
//        Log.i("valor-menu-seleccion","BIBLIOTECA ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_crear_libro->{
             //   verLibros(selec_biblioteca, CrearLibro::class.java)
                return true
            }
            R.id.mi_verLibros ->{
                //Log.i("Saludo","${item.itemId}")
//                verLibros(selec_biblioteca, VerLibro::class.java)
                return true
            }
            R.id.mi_editar ->{
                //recibirItemSelected(2)
//                verLibros(selec_biblioteca,EditarBiblioteca::class.java)
                return true
            }
            R.id.mi_eliminar-> {
//                recibirItemSelected(3)
//                dialogoEliminar()
                return true
            }
            else ->super.onContextItemSelected(item)
        }
    }
/*        //Mostrar lista de las bibliotecas que se encuentran registrada
        mostrarLista()
        añadirMenuLista()
        //Accionar el boton para crear un nuevo registro para la biblioteca


    }
    fun mostrarLista(){
        val Lista = findViewById<ListView>(R.id.list_biblioteca)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,BaseMemoria.arregloBiblioteca)
        Lista.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
    fun añadirMenuLista(){
        val lista = findViewById<ListView>(R.id.list_biblioteca)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_list_item_1,BaseMemoria.arregloBiblioteca)
        lista.adapter = adaptador
        registerForContextMenu(lista)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_opciones_biblioteca,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        selec_biblioteca = id
        Log.i("valor-menu-seleccion","BIBLIOTECA ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_crear_libro->{
                verLibros(selec_biblioteca, CrearLibro::class.java)
                return true
            }
            R.id.mi_verLibros ->{
                //Log.i("Saludo","${item.itemId}")
                verLibros(selec_biblioteca, VerLibro::class.java)
                return true
            }
            R.id.mi_editar ->{
                //recibirItemSelected(2)
                verLibros(selec_biblioteca,EditarBiblioteca::class.java)
                return true
            }
            R.id.mi_eliminar-> {
//                recibirItemSelected(3)
                dialogoEliminar()
                return true
            }
            else ->super.onContextItemSelected(item)
        }
    }
    fun verLibros(posicion: Int,clase: Class<*>,){
        val intent = Intent(this, clase)
        intent.putExtra("idbiblioteca",selec_biblioteca)
//        startActivity(intent)
        startActivityForResult(intent,CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }
    fun eliminarBiblioteca(){
        arreglo.remove(arreglo[selec_biblioteca])
        mostrarLista()
    }
    fun dialogoEliminar(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación eliminar")
        builder.setMessage("Desea eliminar el registro?: ")
        builder.setPositiveButton("Si",DialogInterface.OnClickListener{dialog, which ->
            eliminarBiblioteca()
        })
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()*/
}