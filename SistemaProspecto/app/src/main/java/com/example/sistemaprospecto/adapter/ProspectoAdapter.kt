package com.example.sistemaprospecto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemaprospecto.data.ProspectoModel
import com.example.sistemaprospecto.databinding.ItemProspectoBinding
import com.example.sistemaprospecto.R

class ProspectoAdapter (private val dataset: List<ProspectoModel>, private val listener: (ProspectoModel) -> Unit)
    : RecyclerView.Adapter<ProspectoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val binding = ItemProspectoBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prospecto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prospecto = dataset[position]

        val concatName = "${prospecto.nombre} ${prospecto.primerapellido} "+ if (!prospecto.segundoapellido.isNullOrEmpty()) "${prospecto.segundoapellido}" else " "

        holder.binding.txtNombre2List.text = concatName
        holder.binding.txtEstatusList.text = prospecto.estatus

        holder.binding.root.setOnClickListener{ listener(prospecto) }
    }

    override fun getItemCount(): Int = dataset.size

}