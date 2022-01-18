package com.example.proyecto_1b

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class EditarBiblioteca: AppCompatActivity() {
    var biblioteca: Int = -1
    var arreglo : ArrayList<Biblioteca> = BaseMemoria.arregloBiblioteca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editar_biblioteca)
        biblioteca = intent.getIntExtra("idbiblioteca",0)
        mostrarBiblioteca()
    }
    fun mostrarBiblioteca(){
        asignarTexto()
        val nombre = findViewById<TextView>(R.id.txt_nombre_edit_biblioteca)
        val direccion = findViewById<TextView>(R.id.txt_direccion_edit_biblioteca)
        val numero = findViewById<TextView>(R.id.txt_numero_edit_biblioteca)
        val actualizar = findViewById<Button>(R.id.btn_actualizar)
        actualizar.setOnClickListener{
            dialogoActualizar(nombre.text.toString(),direccion.text.toString(), Integer.parseInt(numero.text.toString()))
        }
        val volver = findViewById<Button>(R.id.btn_volver_editar)
        volver.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }
    fun asignarTexto(){
        val nombre = findViewById<TextView>(R.id.txt_nombre_edit_biblioteca)
        val direccion = findViewById<TextView>(R.id.txt_direccion_edit_biblioteca)
        val numero = findViewById<TextView>(R.id.txt_numero_edit_biblioteca)
        nombre.setText(arreglo[biblioteca].nombre)
        direccion.setText(arreglo[biblioteca].direccion)
        numero.setText(Integer.toString(arreglo[biblioteca].numeroTel))
    }
    fun actualizarDatos(nombre:String, direccion:String,numero: Int){
        arreglo[biblioteca].nombre = nombre
        arreglo[biblioteca].direccion = direccion
        arreglo[biblioteca].numeroTel = numero

    }
    fun irActividad(clase: Class<*>,){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    fun dialogoActualizar(nombre:String, direccion:String, numero:Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmación Actualización")
        builder.setMessage("Desea Actualizar la información correspondiente? : ")
        builder.setPositiveButton("Si", DialogInterface.OnClickListener{ dialog, which ->
            actualizarDatos(nombre,direccion,numero)
            dialogoActualizado()

        })
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val dialogo = builder.create()
        dialogo.show()
    }
    fun dialogoActualizado(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Dato Actualizado")
        builder.setMessage("Se ha actualizado el dato correctamente")
        builder.setNeutralButton("Continuar", DialogInterface.OnClickListener{dialog, which->
            irActividad(MainActivity::class.java)
        })
        val dialogo = builder.create()
        dialogo.show()
    }
}