package com.example.sistemaprospecto

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.sistemaprospecto.databinding.ActivityDetalleBinding

class DetalleActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetalleBinding

    private val  prospectoViewModel: ProspectoViewModel by viewModels()

    var mId: Int? = null

    var mEst: String? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            mId = it.getInt("id")
            mEst = it.getString("estatus")
        }

        mId?.let {
            prospectoViewModel.getById(it)
        }

        prospectoViewModel.prospecto.observe(this, Observer {
            binding.mViewModel = prospectoViewModel

        })

        mEst?.let{
            if(it.equals("rechazado")){
                binding.BtnObservaciones.visibility = View.VISIBLE
            }
        }

        var lblCalle : TextView = binding.AuxCalle
        var lblCP : TextView = binding.AuxCP
        var lblColonia : TextView = binding.AuxColonia
        var lblNumero : TextView = binding.AuxNumero
        var lblNombre : TextView = binding.AuxNombre
        var lblTelefono : TextView = binding.AuxTelefono
        var lblRFC : TextView = binding.AuxRFC
        var lblObservacion : TextView = binding.AuxObservaciones

        var txtCalle : TextView = binding.txtDetalleCalle
        var txtCP : TextView = binding.txtDetalleCodigo
        var txtColonia : TextView = binding.txtDetalleColonia
        var txtNumero : TextView = binding.txtDetalleNumero
        var txtNombre : TextView = binding.txtDetalleNombre
        var txtTelefono : TextView = binding.txtDetalleTelefono
        var txtRFC : TextView = binding.txtDetalleRFC
        var txtObservacion : TextView = binding.txtDetalleObservaciones

        fun InfoInvisible(){

            lblNombre.visibility = View.INVISIBLE
            lblTelefono.visibility = View.INVISIBLE
            lblRFC.visibility = View.INVISIBLE
            lblObservacion.visibility = View.INVISIBLE

            //--------------------------------Otros textos invisibles

            txtNombre.visibility = View.INVISIBLE
            txtTelefono.visibility = View.INVISIBLE
            txtRFC.visibility = View.INVISIBLE
            txtObservacion.visibility = View.INVISIBLE
        }

        fun DomInvicible(){
            lblCalle.visibility = View.INVISIBLE
            lblColonia.visibility = View.INVISIBLE
            lblCP.visibility = View.INVISIBLE
            lblNumero.visibility = View.INVISIBLE
            lblObservacion.visibility = View.INVISIBLE
            //----------------------------------Textos visibles
            txtCalle.visibility = View.INVISIBLE
            txtCP.visibility = View.INVISIBLE
            txtColonia.visibility = View.INVISIBLE
            txtNumero.visibility = View.INVISIBLE
            txtObservacion.visibility = View.INVISIBLE
        }


        binding.BtnDomicilio.setOnClickListener {
            //-----------------------------------label visibles
            lblCalle.visibility = View.VISIBLE
            lblColonia.visibility = View.VISIBLE
            lblCP.visibility = View.VISIBLE
            lblNumero.visibility = View.VISIBLE
            //----------------------------------Textos visibles
            txtCalle.visibility = View.VISIBLE
            txtCP.visibility = View.VISIBLE
            txtColonia.visibility = View.VISIBLE
            txtNumero.visibility = View.VISIBLE

            InfoInvisible()
        }

        binding.BtnInfoPer.setOnClickListener {
            lblNombre.visibility = View.VISIBLE
            lblTelefono.visibility = View.VISIBLE
            lblRFC.visibility = View.VISIBLE

            txtNombre.visibility = View.VISIBLE
            txtTelefono.visibility = View.VISIBLE
            txtRFC.visibility = View.VISIBLE

            DomInvicible()
        }

        binding.BtnObservaciones.setOnClickListener {
            DomInvicible()
            InfoInvisible()
            lblObservacion.visibility = View.VISIBLE
            txtObservacion.visibility = View.VISIBLE
        }

    }

}