package com.example.sistemaprospecto

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.text.BoringLayout
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.sistemaprospecto.data.ProspectoModel
import com.example.sistemaprospecto.databinding.ActivityAgregarBinding
import com.example.sistemaprospecto.databinding.ActivityDetalleBinding
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AgregarActivity : AppCompatActivity() {

    lateinit var binding: ActivityAgregarBinding

    private val prospectoViewModel: ProspectoViewModel by viewModels()

    private var vNombre: String = ""
    private var vPrimer: String = ""
    private var vSegundo: String = ""
    private var vCalle: String = ""
    private var vNumero: String = ""
    private var vColonia: String = ""
    private var vCPostal: String = ""
    private var vTelefono: String = ""
    private var vRfc: String = ""
    private var vEstatus: String = "enviado"


    private var v: Boolean = false

    private var validacion = mutableListOf(false,false,false,false,false,false,false,false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtAgregarNombre.addTextChangedListener{
            if(binding.txtAgregarNombre.text.isNullOrEmpty() ){
                Toast.makeText(this,"Ingrese el nombre",Toast.LENGTH_SHORT).show()
                validacion[0] = false
                validaciones()
            } else {
                validacion[0] = true
                validaciones()
                vNombre =  binding.txtAgregarNombre.text.toString()
            }
        }
        binding.txtAgregarPrimer.addTextChangedListener{
            if(binding.txtAgregarPrimer.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese el primer apellido",Toast.LENGTH_SHORT).show()
                validacion[1] = false
                validaciones()
            } else {
                validacion[1] = true
                validaciones()
                vPrimer =  binding.txtAgregarPrimer.text.toString()
            }
        }
        binding.txtAgregarCalle.addTextChangedListener{
            if(binding.txtAgregarCalle.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese la calle",Toast.LENGTH_SHORT).show()
                validacion[2] = false
                validaciones()
            } else {
                validacion[2] = true
                validaciones()
                vCalle = binding.txtAgregarCalle.text.toString()
            }
        }
        binding.txtAgregarNumero.addTextChangedListener{
            if(binding.txtAgregarNumero.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese el numero de casa",Toast.LENGTH_SHORT).show()
                validacion[3] = false
                validaciones()
            } else {
                validacion[3] = true
                validaciones()
                vNumero = binding.txtAgregarNumero.text.toString()
            }
        }
        binding.txtAgregarColonia.addTextChangedListener{
            if(binding.txtAgregarColonia.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese la calle",Toast.LENGTH_SHORT).show()
                validacion[4] = false
                validaciones()
            } else {
                validacion[4] = true
                validaciones()
                vColonia = binding.txtAgregarColonia.text.toString()
            }
        }
        binding.txtAgregarCPostal.addTextChangedListener{
            if(binding.txtAgregarCPostal.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese la calle",Toast.LENGTH_SHORT).show()
                validacion[5] = false
                validaciones()
            } else {
                validacion[5] = true
                validaciones()
                vCPostal = binding.txtAgregarCPostal.text.toString()
            }
        }
        binding.txtAgregarTelefono.addTextChangedListener{
            if(binding.txtAgregarTelefono.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese la calle",Toast.LENGTH_SHORT).show()
                validacion[6] = false
                validaciones()
            } else {
                validacion[6] = true
                validaciones()
                vTelefono = binding.txtAgregarTelefono.text.toString()
            }
        }
        binding.txtAgregarRFC.addTextChangedListener{
            if(binding.txtAgregarRFC.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingrese la calle",Toast.LENGTH_SHORT).show()
                validacion[7] = false
                validaciones()
            } else {
                validacion[7] = true
                validaciones()
                vRfc = binding.txtAgregarRFC.text.toString()
            }
        }

        binding.BtnAgregarGuardar.setOnClickListener{
            if(!binding.txtAgregarSegundo.text.isNullOrEmpty())
            {
                vSegundo = binding.txtAgregarSegundo.text.toString()
            }
            saveProspecto()
        }

        binding.BtnAgregarSalir.setOnClickListener {
            AlertDialog.Builder(this).apply {
                if(validacion.contains(true)){
                    setTitle("Esta seguro de salir")
                    setMessage("¿Estas seguro de salir sin guardar?")
                    setPositiveButton("Si"){ _: DialogInterface, _: Int ->
                        finish()
                    }
                    setNegativeButton("No", null)
                }else{
                    finish()
                }
            }.show()
        }
    }

    fun saveProspecto() {
        val values = JSONObject()
        values.put("nombre", "$vNombre")
        values.put("primerapellido", "$vPrimer")
        values.put("segundoapellido", "$vSegundo")
        values.put("calle", "$vCalle")
        values.put("numero", "$vNumero")
        values.put("colonia", "$vColonia")
        values.put("codigopostal", "$vCPostal")
        values.put("telefono", "$vTelefono")
        values.put("rfc", "$vRfc")
        values.put("estatus", "$vEstatus")

        val jsonObjectString = values.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        prospectoViewModel.save(requestBody)
        limpiarText()
    }

    private fun validaciones(){
        if(validacion.contains(false)){
            binding.BtnAgregarGuardar.visibility = View.INVISIBLE
        }else{
            binding.BtnAgregarGuardar.visibility = View.VISIBLE
        }
    }

    private fun limpiarText(){
        binding.txtAgregarNombre.setText("")
        binding.txtAgregarPrimer.setText("")
        binding.txtAgregarSegundo.setText("")
        binding.txtAgregarCalle.setText("")
        binding.txtAgregarNumero.setText("")
        binding.txtAgregarColonia.setText("")
        binding.txtAgregarCPostal.setText("")
        binding.txtAgregarTelefono.setText("")
        binding.txtAgregarRFC.setText("")

    }
}