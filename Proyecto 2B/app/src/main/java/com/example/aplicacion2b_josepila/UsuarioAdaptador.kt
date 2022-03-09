package com.example.aplicacion2b_josepila

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class UsuarioAdaptador(val context: Context, val userList:ArrayList<Usuario>):
    RecyclerView.Adapter<UsuarioAdaptador.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_usuarios,parent,false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioAdaptador.UsuarioViewHolder, position: Int){
        val currentUsuario = userList[position]
        holder.textoNombre.text  = currentUsuario.nombre
        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("name", currentUsuario.nombre)
            intent.putExtra("uid", currentUsuario.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return userList.size
    }
    class UsuarioViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textoNombre = itemView.findViewById<TextView>(R.id.lista_nombre)
    }
}
