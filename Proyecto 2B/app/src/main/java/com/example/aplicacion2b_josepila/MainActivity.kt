package com.example.aplicacion2b_josepila

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var usuarioRecycler: RecyclerView
    private lateinit var usuarioLista: ArrayList<Usuario>
    private lateinit var adaptador: UsuarioAdaptador
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()

        usuarioLista = ArrayList()
        adaptador= UsuarioAdaptador(this,usuarioLista)

        usuarioRecycler = findViewById(R.id.recyclerView_usuarios)
        usuarioRecycler.layoutManager = LinearLayoutManager(this)
        usuarioRecycler.adapter = adaptador

        //Lectura de la base
        mDbRef.child("user").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                usuarioLista.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(Usuario::class.java)
                    if (mAuth.currentUser?.uid !=currentUser?.uid){
                        usuarioLista.add(currentUser!!)
                    }
                }
                adaptador.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        } )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_logout){
            mAuth.signOut()
            val inten = Intent(this@MainActivity, Registrarse::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}