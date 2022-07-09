package com.example.mytrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class AggiuntaEsercizi : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aggiunta_esercizi)

        val btnAdd = findViewById<Button>(R.id.btnAggiungiEsercizio)
        val nomeEsercizioet = findViewById<EditText>(R.id.nomeEsercizio)
        val repset = findViewById<EditText>(R.id.repsEsercizio)
        val serieet = findViewById<EditText>(R.id.serieEsercizio)
        val UID:String = intent.getStringExtra("uidAtleti").toString()
        val nomeAtleta:String = intent.getStringExtra("nomeAtleta").toString()



        btnAdd.setOnClickListener(){

            val nomeEsercizio = nomeEsercizioet.text.toString()
            val reps = repset.text.toString().toInt()
            val serie = serieet.text.toString().toInt()

            val esercizio = hashMapOf(
                "nome" to nomeEsercizio ,
                "reps" to reps,
                "serie" to serie
            )


            db.collection("Esercizi/Atleti/${UID}")
                .add(esercizio)
                .addOnSuccessListener {
                    Log.d("ADD DATA", "DocumentSnapshot successfully written! *********** ${nomeAtleta}")
                    Toast.makeText(this, " Esercizio Aggiunto ", Toast.LENGTH_SHORT).show()
                    nomeEsercizioet.text.clear()
                    repset.text.clear()
                    serieet.text.clear()
                }

                .addOnFailureListener { e -> Log.w("ADD DATA", "Error writing document", e) }
        }
    }
}