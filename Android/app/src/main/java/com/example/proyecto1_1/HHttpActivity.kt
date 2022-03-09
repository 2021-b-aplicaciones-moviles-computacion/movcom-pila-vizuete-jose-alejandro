package com.example.proyecto1_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hhttp)

    }
    fun metodoGet(){
        val identificadorPost =1
        val url = "https://jsonplaceholder.typicode.com/posts/"+identificadorPost.toString()
        url
            .httpGet()
            .responseString{req, res, result ->
                when(result) {
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("klaxon", "${error}")
                    }
                    is Result.Success -> {
                        val respuestaString = result.get()
                        Log.i("klaxon","${respuestaString}")
                        val post = Klaxon()
                            .parse<IPostHttp>(respuestaString)
                            //.parseArray<IPostHttp>(getString)
                        Log.i("klaxon", "${post?.body}")
                    }
                }
            }
    }
    fun metodoPost(){
        val parametros: List<Pair<String, * >> = listOf(
            "title" to "Titulo Moviles",
            "body" to "descripcion ej1",
            "userId" to 1
        )
        val url = "https://jsonplaceholder.typicode.com/posts"
        url
            .httpPost(parametros)
            .responseString{req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                        Log.i("post-klaxon", "${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("post-klaxon","${postString}")
                        val post = Klaxon()
                            .parse<IPostHttp>(postString)
                        Log.i("post-klaxon","${post?.title}")
                    }
                }
            }

    }
}