package com.listadopaises.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.listadopaises.R
import com.listadopaises.model.Pais

class PaisAdapter(private var paises: List<Pais>) : RecyclerView.Adapter<PaisAdapter.PaisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pais, parent, false)
        return PaisViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val pais = paises[position]
        holder.bind(pais)
    }

    override fun getItemCount(): Int = paises.size

    fun actualizarLista(nuevaLista: List<Pais>) {
        paises = nuevaLista
        notifyDataSetChanged()
    }

    inner class PaisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombrePais)
        private val banderaImageView: ImageView = itemView.findViewById(R.id.banderaPais)

        fun bind(pais: Pais) {
            nombreTextView.text = pais.name?.common ?: "Desconocido"

            val urlBandera = pais.flags?.png
            if (!urlBandera.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(urlBandera)
                    .into(banderaImageView)
            } else {
                banderaImageView.setImageResource(R.drawable.placeholder_bandera)
            }
        }
    }
}