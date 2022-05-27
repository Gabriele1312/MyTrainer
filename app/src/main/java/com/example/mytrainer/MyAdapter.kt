package com.example.mytrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList: ArrayList<Atleti>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_atleti_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val user: Atleti = userList[position]
        holder.firstName.text = user.nome
        holder.lastName.text = user.cognome
        //immagine
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val firstName: TextView = itemView.findViewById(R.id.tvfirstName)
        val lastName: TextView = itemView.findViewById(R.id.tvlastName)
        //immagine
    }
}