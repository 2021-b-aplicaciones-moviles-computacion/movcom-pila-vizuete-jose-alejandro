package com.example.proyecto1_1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/*Clase 14 07-01-2022
* Desarrollo de la clase principal para conectar a la base de datos*/

class ESqliteHelperUsuario (contexto: Context?): SQLiteOpenHelper(contexto,
"moviles", null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario =
            """
                CREATE TABLE USUARIO (
                id, INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR(50)
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        Log.i("bbd","Creando la tabla usuario...")
        db?.execSQL(scriptCrearTablaUsuario)
    }
    fun crearUsuarioFormulario(nombre: String, descripcion:String):Boolean{
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descripcion",descripcion)
        val resultadoEscritura = baseDatosEscritura
            .insert(
                "Usuario", null, valoresAGuardar
            )
        baseDatosEscritura.close()
        return if(resultadoEscritura.toInt() == -1) false else true
    }
    fun consultarUsuarioporId(id: Int): EUsuarioBDD{
        val baseDatosLectura = readableDatabase
        val scriptBuscaUsuario = "SELECT * FROM USUARIO WHERE id = ${id}"
        val resultadoConsulta = baseDatosLectura.rawQuery(
            scriptBuscaUsuario,
            null
        )
        val existeUsuario = resultadoConsulta.moveToFirst()
        val usuarioBDD = EUsuarioBDD(0,"","")
        if (existeUsuario){
            do{
                val id = resultadoConsulta.getInt(0)
                val nombre = resultadoConsulta.getString(1)
                val descripcion = resultadoConsulta.getString(2)
                if(id!= null){
                    usuarioBDD.id= id
                    usuarioBDD.nombre = nombre
                    usuarioBDD.descripcion = descripcion
                }
            }while (resultadoConsulta.moveToNext())
        }
        resultadoConsulta.close()
        baseDatosLectura.close()
        return usuarioBDD
    }
    fun eliminarUsuarioFormulario(id: Int): Boolean{
        val conexionEliminar = writableDatabase
        val resultadoEliminar = conexionEliminar
            .delete(
                "USUARIO",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEliminar.close()
        return if (resultadoEliminar.toInt() == -1) false else true
    }
    fun actualizarUsuarioFormulario(
        nombre: String, descripcion: String,idActualizar: Int):Boolean{
        val conexionActualizar = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre", nombre)
        valoresActualizar.put("descripcion", descripcion)
        val resultadoActualizar = conexionActualizar
            .update(
                "USUARIO", valoresActualizar, "id=?",
                arrayOf(idActualizar.toString())
            )
        conexionActualizar.close()
        return if (resultadoActualizar.toInt() == -1) false else true
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1:Int, p2: Int) {}
}