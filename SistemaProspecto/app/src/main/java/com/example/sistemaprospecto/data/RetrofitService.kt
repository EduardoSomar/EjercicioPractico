package com.example.sistemaprospecto.data

import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @GET("all")
    suspend fun getAll(): Response<List<ProspectoModel>>

    @GET("id/{id}")
    suspend fun getById(@Path("id") id: Int): Response<List<ProspectoModel>>

    @GET("estatus/{estatus}")
    suspend fun getByEstatus(@Path("estatus") estatus: String): Response<List<ProspectoModel>>

    @POST("save")
    suspend fun saveProspecto(@Body prospectoBody: RequestBody): ResponseBody

    @PUT("revisar")
    suspend fun  updateProspecto(@Body prospectoBody: RequestBody): ResponseBody
}