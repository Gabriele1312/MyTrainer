package com.example.mytrainer

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList: ArrayList<Atleti>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_atleti_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val user: Atleti = userList[position]
        holder.UID.text = user.UIDatleti
        holder.nome.text = user.nome
        //immagine
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val UID: TextView = itemView.findViewById(R.id.tvUID)
        val nome: TextView = itemView.findViewById(R.id.tvnome)
        //immagine


        val btnAggiungiScheda = itemView.findViewById<Button>(R.id.btn_aggiungiScheda).setOnClickListener(View.OnClickListener {
            Log.i("PersonalTrainer" ,"APRO INSERISCI ESERCIZIO")
            val context = it.context //context activity recycler view (getContext)
            val aggiungi = Intent(context, AggiuntaEsercizi::class.java )
            aggiungi.putExtra("uidAtleti", UID.text)
            aggiungi.putExtra("nomeAtleta", nome.text)
            context.startActivity(aggiungi)
        })


        val btnVisualizzaScheda = itemView.findViewById<Button>(R.id.btn_vediScheda).setOnClickListener(View.OnClickListener {
            Log.i("PersonalTrainer" ,"APRO VISUALIZZA ESERCIZI")
            val context = it.context //context activity recycler view (getContext)
            val vedi = Intent(context, VisualizzaEserciziPT::class.java )
            vedi.putExtra("uidAtleti", UID.text)
            context.startActivity(vedi)
        })
    }


}
