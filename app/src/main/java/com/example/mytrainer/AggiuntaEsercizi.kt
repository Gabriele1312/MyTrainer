package com.example.mytrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AggiuntaEsercizi : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aggiunta_esercizi)

        val btnAdd = findViewById<Button>(R.id.btnAggiungiEsercizio)
        val nomeEsercizio = findViewById<EditText>(R.id.nomeEsercizio)
        val reps = findViewById<EditText>(R.id.repsEsercizio)
        val serie = findViewById<EditText>(R.id.serieEsercizio)


        btnAdd.setOnClickListener(){

            val nomeEsercizio = nomeEsercizio.text.toString()
            val reps = reps.text.toString().toInt()
            val serie = serie.text.toString().toInt()

            val esercizio = hashMapOf(
                "nome" to nomeEsercizio ,
                "reps" to reps,
                "serie" to serie
            )

            db.collection("Esercizi")
                .add(esercizio)
                .addOnSuccessListener {
                    Log.d("ADD DATA", "DocumentSnapshot successfully written!")
                    Toast.makeText(this, " Esercizio Aggiunto ", Toast.LENGTH_SHORT).show()
                }

                .addOnFailureListener { e -> Log.w("ADD DATA", "Error writing document", e) }
        }
    }
}