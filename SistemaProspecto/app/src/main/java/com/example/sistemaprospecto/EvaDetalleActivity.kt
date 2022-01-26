package com.example.sistemaprospecto

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.MutableLiveData
import com.example.sistemaprospecto.databinding.ActivityEvadetalleBinding
import androidx.lifecycle.Observer
import com.example.sistemaprospecto.data.ProspectoModel
import com.example.sistemaprospecto.databinding.ActivityDetalleBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class EvaDetalleActivity : AppCompatActivity() {

    lateinit var binding: ActivityEvadetalleBinding

    private val  prospectoViewModel: ProspectoViewModel by viewModels()

    var mId: Int? = null

    var opcion: String = ""

    var observaciones : String = ""

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityEvadetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            mId = it.getInt("id")
        }

        mId?.let {
            prospectoViewModel.getById(it)
        }

        prospectoViewModel.prospecto.observe(this, Observer {
            binding.mViewModel = prospectoViewModel
        })

        binding.ButtonEvaluar.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Evaluacion")
                setMessage("¿Quiere rechazar o autorizar")
                setPositiveButton("Rechazar"){ _: DialogInterface, _: Int ->
                    opcion = "rechazado"
                    binding.txtEvaDetalleObservaciones.visibility = View.VISIBLE
                    binding.ettObservaciones.visibility = View.VISIBLE
                    binding.ButtonGuardar.visibility = View.INVISIBLE
                }
                setNegativeButton("Autorizar") { _: DialogInterface, _: Int ->
                    opcion = "autorizado"
                    limpiaTexto()
                    binding.ButtonGuardar.visibility = View.VISIBLE
                }
            }.show()
        }

        binding.ettObservaciones.addTextChangedListener {
            if(binding.ettObservaciones.text.isNullOrEmpty()) {
                binding.ButtonGuardar.visibility = View.INVISIBLE
            } else {
                binding.ButtonGuardar.visibility = View.VISIBLE
            }
        }

        binding.ButtonGuardar.setOnClickListener {
            observaciones = binding.ettObservaciones.text.toString()
            updatePros()
        }

    }

    fun updatePros(){

        val nombre : String = binding.txtAuxDetalleNombre.text.toString()
        val primer : String = binding.txtEvaDetallePrimer.text.toString()
        val segundo : String = binding.txtEvaDetalleSegundo.text.toString()
        val calle : String = binding.txtEvaDetalleCalle.text.toString()
        val numero : String = binding.txtEvaDetalleNumero.text.toString()
        val colonia : String = binding.txtEvaDetalleColonia.text.toString()
        val cp : String = binding.txtEvaDetalleCodigo.text.toString()
        val telefono : String = binding.txtEvaDetalleTelefono.text.toString()
        val rfc : String = binding.txtEvaDetalleRFC.text.toString()

        val values = JSONObject()
        values.put("id","$mId")
        values.put("estatus","$opcion")
        values.put("observaciones","$observaciones")
        values.put("nombre", "$nombre")
        values.put("primerapellido", "$primer")
        values.put("segundoapellido", "$segundo")
        values.put("calle", "$calle")
        values.put("numero", "$numero")
        values.put("colonia", "$colonia")
        values.put("codigopostal", "$cp")
        values.put("telefono", "$telefono")
        values.put("rfc", "$rfc")
        values.put("observaciones","$observaciones")

        val jsonObjectString = values.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        prospectoViewModel.update(requestBody)

        finish()

        intent = Intent(this, EvaluarActivity::class.java)
        startActivity(intent)
    }

    fun limpiaTexto(){
        binding.txtEvaDetalleObservaciones.visibility = View.INVISIBLE
        binding.ettObservaciones.visibility = View.INVISIBLE
        binding.ettObservaciones.setText("")
    }
}