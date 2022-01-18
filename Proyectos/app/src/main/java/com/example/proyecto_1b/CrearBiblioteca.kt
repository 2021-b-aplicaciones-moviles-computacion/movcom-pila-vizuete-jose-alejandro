package com.example.proyecto_1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class CrearBiblioteca: AppCompatActivity() {
    val arreglo: ArrayList<Biblioteca> = BaseMemoria.arregloBiblioteca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_biblioteca)
        //tomamos los valores
        val txtNombre = findViewById<EditText>(R.id.txt_nombre_edit_biblioteca)
        val txtDireccion =findViewById<EditText>(R.id.txt_direccion_edit_biblioteca)
        val intTelefono = findViewById<EditText>(R.id.txt_numero_edit_biblioteca)
        val boton_Ejecutar_crear = findViewById<Button>(R.id.btn_ejecutar_crear)
        val boton_Regresar = findViewById<Button>(R.id.btn_regresar_main)
        boton_Ejecutar_crear
            .setOnClickListener{
                val nombre: String = txtNombre.text.toString()
                val direccion: String = txtDireccion.text.toString()
                val telefono: Int = Integer.parseInt(intTelefono.text.toString())
                añadirBiblioteca(arreglo, nombre,direccion,telefono)
            }
        boton_Regresar
            .setOnClickListener{
                irActividad(MainActivity::class.java)
            }
    }
    fun añadirBiblioteca(
        arreglo: ArrayList<Biblioteca>,
        nombre:String,
        direccion:String,
        numero: Int
    ){
        arreglo.add(Biblioteca(nombre,direccion,numero))
        dialogoCrear()
        Log.i("nuevo-elemento", BaseMemoria.arregloBiblioteca.toString())
    }
    fun irActividad(clase: Class<*>,){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    fun dialogoCrear(){
        val builder = AlertDialog.Builder(this)
            builder.setTitle("Cuadro de confirmación")
            builder.setMessage("Se ha ingresado el usuario correctamente")
        val dialogo = builder.create()
        dialogo.show()
    }
}