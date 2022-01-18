package com.example.proyecto1_1

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    var idItemSelected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val ListView = findViewById<ListView>(R.id.lv_list_view)
        val arreglo: ArrayList<Int> = arrayListOf(1,2,3,4,5)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            //arreglo
            BBaseDatosMemoria.arregloBEntrenador
        )
        ListView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        val botonAñadirListView = findViewById<Button>(R.id.btn_añadir_list_view)
/*        botonAñadirListView.setOnClickListener{
            añadirItem(adaptador,arreglo,1)
        }*/
        botonAñadirListView.setOnClickListener{
            añadirItem(adaptador,BBaseDatosMemoria.arregloBEntrenador,1)
        }       /* ListView.setOnItemLongClickListener { parent, view, position, id ->
            Log.i("list-view", "Long-click ${arreglo[position]}")
            return@setOnItemLongClickListener true
        }*/
       //Acceder a la opcion de menu - Registrar menu contextual
        registerForContextMenu(ListView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_prin, menu)
        val info =  menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSelected = id
        Log.i("intent-explicito", "ID:${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar -> {
                Log.i("context-menu", "Posicion ${idItemSelected}")
                abrirDialogo()
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Posicion ${idItemSelected}")
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Titulo")

        //Seleccion de opciones en base a lo que se ha generado
        val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
        val seleccion_previa = booleanArrayOf(true,false,false)
        builder.setMultiChoiceItems(
            opciones,
            seleccion_previa
        ) { dialog,
            which,
            isChecked ->
            Log.i("dialogo", "Dio click en el item ${which}")

        }

        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                Log.i("intent-explicit", "HOLA")
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        
         val dialogo = builder.create()
        dialogo.show()
    }
    fun añadirItem(
        adaptador: ArrayAdapter<BEntrenador>,
        //arreglo: ArrayList<Int>,
        arreglo: ArrayList<BEntrenador>,
        valor: Int
    ){
        //arreglo.add(valor)
        arreglo.add(BEntrenador("Daniel", "valornuevo"))
        adaptador.notifyDataSetChanged()

    }
}