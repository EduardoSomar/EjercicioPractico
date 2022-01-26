package com.example.sistemaprospecto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistemaprospecto.databinding.ActivityEvaluarBinding
import androidx.lifecycle.Observer
import com.example.sistemaprospecto.adapter.ProspectoAdapter

class EvaluarActivity  : AppCompatActivity(){
    lateinit var binding: ActivityEvaluarBinding

    private val prospectoViewModel: ProspectoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEvaluarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prospectoViewModel.getByEstatus("enviado")

        prospectoViewModel.prospectoList.observe(this, {
            binding.rvItem.adapter = ProspectoAdapter(it){
                    prospectoModel ->
                val intent = Intent(this, EvaDetalleActivity::class.java)
                intent.putExtra("id", prospectoModel.id)
                startActivity(intent)
                finish()
            }
        })

        binding.rvItem.setHasFixedSize(true)
        binding.rvItem.layoutManager = LinearLayoutManager(this)

        prospectoViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, "it", Toast.LENGTH_SHORT).show()
        })
    }
}