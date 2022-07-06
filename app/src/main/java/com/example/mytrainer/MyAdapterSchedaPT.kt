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

        //elimina esercizio scheda
        val btnElimina = itemView.findViewById<Button>(R.id.btn_eliminaEsercizio).setOnClickListener(View.OnClickListener{ it: View ->

            val context = it.context //recupera context dove lavora adapter
            val TAG: String = "TAG"
            //creazione alert Dialog prima di eliminare
            AlertDialog.Builder(context)
            .setTitle("Sei Sicuro?")
            .setMessage("I dati non potranno essere recuperati")

            .setPositiveButton("Elimina"){dialog, which ->
                Log.i("PERSONAL TRAINER" , "Elimino esercizio")
                Toast.makeText(context, " Esercizio eliminato ", Toast.LENGTH_SHORT).show()
                var UID = VisualizzaEserciziPT.facebookID
                Log.d(TAG, "***************** Ecco l'UID per cancellare l'esercizio: ${UID}")
                var nomeEs = nome.text.toString()
                Log.d(TAG, "***************** Ecco il nome dell'esercizio: ${nomeEs} ")

                val db = FirebaseFirestore.getInstance()
                val dataB = db.collection("Esercizi")
                    .document("Atleti/${UID}")
//                    .id
//                    .toString()
                Log.d(TAG, "******************* DATABBB${dataB} ")

//                val updates = hashMapOf<String, Any>(
//                    "nome" to FieldValue.delete()
//                )
//                dataB.update(updates).addOnCompleteListener{}

            }

            .setNegativeButton("Indietro") {dialog, which ->
                Toast.makeText(context, "Ripristino", Toast.LENGTH_SHORT).show()
            }

                .show() //mostra alert dialog

        })

        //FUNZIONE ELIMINA ESERCIZIO DA IMPLEMENTARE
//        private fun eliminaEsercizio(view: View) {
//            var UID = facebookID.get(0)
//            Log.d("TAG", "******************** Ecco l'UID:  ${UID}: ")
//            lateinit var mAuth: FirebaseAuth
//
//            val db = FirebaseFirestore.getInstance()
//            val dataB = db.collection("Esercizi/Atleti/${UID}")
//                .whereEqualTo("nome", mAuth)
//
//
//            val updates = hashMapOf<String, Any>(
//                "nome" to FieldValue.delete()
//            )
//            dataB.update(updates).addOnCompleteListener{}
//        }
    }

}