package com.example.sistemaprospecto.data

import okhttp3.RequestBody
import org.json.JSONObject

class ProspectoRepository {
    private var retService = RetrofitInstance.getRetrofitInstance()
        .create(RetrofitService::class.java)

    suspend fun getAll() = retService.getAll()

    suspend fun getById(id : Int) = retService.getById(id)

    suspend fun getByEstatus(estatus: String) = retService.getByEstatus(estatus)

    suspend fun save(pros: RequestBody) = retService.saveProspecto(pros)

    suspend fun update(pros: RequestBody) = retService.updateProspecto(pros)
}