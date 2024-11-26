package com.listadopaises.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.listadopaises.R
import com.listadopaises.model.Pais
import com.listadopaises.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var paisAdapter: PaisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        paisAdapter = PaisAdapter(emptyList())
        recyclerView.adapter = paisAdapter

        obtenerListaDePaises()
    }

    private fun obtenerListaDePaises() {
        val api = ApiClient.apiService

        api.getPaises().enqueue(object : Callback<List<Pais>> {
            override fun onResponse(call: Call<List<Pais>>, response: Response<List<Pais>>) {
                if (response.isSuccessful) {
                    val paises = response.body() ?: emptyList()
                    paisAdapter.actualizarLista(paises)
                } else {
                    Log.e("API_ERROR", "Código de error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                Log.e("API_FAILURE", "Error en la llamada a la API", t)
            }
        })
    }
}