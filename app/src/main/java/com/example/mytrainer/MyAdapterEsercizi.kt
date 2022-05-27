package com.example.mytrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterEsercizi(private val eserciziList: ArrayList<Esercizi>): RecyclerView.Adapter<MyAdapterEsercizi.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterEsercizi.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_esercizi_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapterEsercizi.MyViewHolder, position: Int) {

        val esercizi: Esercizi = eserciziList[position]
        holder.nome.text = esercizi.nome
        holder.serie.text = esercizi.serie.toString()
        holder.ripetizioni.text = esercizi.reps.toString()

    }

    override fun getItemCount(): Int {
        return eserciziList.size
    }

    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nome: TextView = itemView.findViewById(R.id.tvnome)
        val serie: TextView = itemView.findViewById(R.id.tvserie)
        val ripetizioni: TextView = itemView.findViewById(R.id.tvripetizioni)

    }
}