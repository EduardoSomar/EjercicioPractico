package com.example.sistemaprospecto

import androidx.lifecycle.MutableLiveData
import com.example.sistemaprospecto.data.ProspectoModel
import com.example.sistemaprospecto.data.ProspectoRepository
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import org.json.JSONObject

class ProspectoViewModel : ViewModel() {

    private val prospectoRepository = ProspectoRepository()

    var prospectoList = MutableLiveData<List<ProspectoModel>>()

    var prospecto = MutableLiveData<ProspectoModel>()

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getAll() {
        viewModelScope.launch {
            val response = prospectoRepository.getAll()
            if(response.isSuccessful){
                response.body()?.let {
                    prospectoList.postValue(it)
                }
            }
        }
    }

    fun getById(id: Int){
        viewModelScope.launch {
            val response = prospectoRepository.getById(id)
            if (response.isSuccessful){
                response.body()?.let {
                    prospecto.postValue(it[0])
                }
            } else {
                onError("Error: ${response.message()}")
            }

        }
    }

    fun save(prosp: RequestBody){
        viewModelScope.launch {
            val response = prospectoRepository.save(prosp)
        }
    }

    fun getByEstatus(estatus: String){
        viewModelScope.launch {
            val response = prospectoRepository.getByEstatus(estatus)
            if(response.isSuccessful){
                response.body()?.let {
                    prospectoList.postValue(it)
                }
            }
        }
    }

    fun update(prosp: RequestBody){
        viewModelScope.launch {
            val response = prospectoRepository.update(prosp)
        }
    }

    fun onError(m:String){
        errorMessage.value = m
        loading.value = false
    }
}