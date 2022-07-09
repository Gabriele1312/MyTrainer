package com.example.mytrainer

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MyAdapterSchedaPT(private val eserciziList: ArrayList<Esercizi>): RecyclerView.Adapter<MyAdapterSchedaPT.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapterSchedaPT.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_scheda_pt_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapterSchedaPT.MyViewHolder, position: Int) {

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