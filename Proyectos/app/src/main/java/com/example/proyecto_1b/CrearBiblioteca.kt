package com.example.proyecto_1b

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearBiblioteca: AppCompatActivity() {
//    val arreglo: ArrayList<Biblioteca> = BaseMemoria.arregloBiblioteca
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_biblioteca)
        //tomamos los valores
        val boton_Ejecutar_crear = findViewById<Button>(R.id.btn_ejecutar_crear)
        val boton_Regresar = findViewById<Button>(R.id.btn_regresar_main)
        boton_Ejecutar_crear
            .setOnClickListener{
               añadirBiblioteca()
            }
        boton_Regresar
            .setOnClickListener{
                irActividad(MainActivity::class.java)
            }
    }
    fun añadirBiblioteca() {
        val txtNombre = findViewById<EditText>(R.id.txt_nombre_edit_biblioteca)
        val txtDireccion = findViewById<EditText>(R.id.txt_direccion_edit_biblioteca)
        val intTelefono = findViewById<EditText>(R.id.txt_numero_edit_biblioteca)
        val nuevaBiblioteca = hashMapOf<String, Any>(
            "nombre" to txtNombre.text.toString(),
            "direccion" to txtDireccion.text.toString(),
            "telefono" to intTelefono.text.toString().toInt()
        )
        val db = Firebase.firestore
        val referencia = db.collection("bibliotecas")
        referencia
            .add(nuevaBiblioteca)
            .addOnSuccessListener {
                dialogoCrear()
            }
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