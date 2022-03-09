package com.example.aplicacion2b_josepila

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MensajeAdaptador(val context: Context,val mensajeList:ArrayList<Mensaje> ): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         if(viewType == 1){
             val view: View= LayoutInflater.from(context).inflate(R.layout.activity_mensaje_recibido,parent,false)
             return ReceiveViewHolder(view)
         }else{
             val view: View = LayoutInflater.from(context).inflate(R.layout.activity_mensaje_enviado, parent, false)
              return SentViewHolder(view)
         }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = mensajeList[position]
        if(holder.javaClass == SentViewHolder::class.java){

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.mensaje
        }else{
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.mensaje
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = mensajeList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.idEmisor))
        {
            return ITEM_SENT
        }else{
            return ITEM_RECEIVE
        }

    }
    override fun getItemCount(): Int {
        return mensajeList.size
    }
    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }
    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_messagee)
    }

}