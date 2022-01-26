package com.example.sistemaprospecto

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.sistemaprospecto.databinding.ActivityListadoBinding
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaprospecto.adapter.ProspectoAdapter

class ListadoActivity : AppCompatActivity(){

    lateinit var binding: ActivityListadoBinding

    private val prospectoViewModel: ProspectoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prospectoViewModel.getAll()

        prospectoViewModel.prospectoList.observe(this, {
            binding.rvItem.adapter = ProspectoAdapter(it)
            {
                prospectoModel ->
                val intent = Intent(this, DetalleActivity::class.java)
                intent.putExtra("id", prospectoModel.id)
                intent.putExtra("estatus", prospectoModel.estatus)
                startActivity(intent)
            }
        })

        binding.rvItem.setHasFixedSize(true)
        binding.rvItem.layoutManager = LinearLayoutManager(this)

        prospectoViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, "it",Toast.LENGTH_SHORT).show()
        })
    }


}