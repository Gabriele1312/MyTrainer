package com.example.mytrainer

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
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

        val btnTimer = itemView.findViewById<Button>(R.id.btn_timer_go).setOnClickListener(View.OnClickListener {
            Log.i("Timer" ,"APRO TIMER")
            val context = it.context //context activity recycler view (getContext)
            val timer = Intent(context, Timer::class.java )
            context.startActivity(timer)
        })

    }


}