package com.listadopaises.network

import com.listadopaises.model.Pais
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("v3.1/all")
    fun getPaises(): Call<List<Pais>>
}