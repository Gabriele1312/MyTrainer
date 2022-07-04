//package com.example.mytrainer
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import com.google.firebase.firestore.DocumentChange
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import kotlinx.android.synthetic.main.lista_atleti_item.*
//
//class AggiuntaEsercizi : AppCompatActivity() {
//
//    val db = Firebase.firestore
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_aggiunta_esercizi)
//
//        val btnAdd = findViewById<Button>(R.id.btnAggiungiEsercizio)
//        val nomeEsercizio = findViewById<EditText>(R.id.nomeEsercizio)
//        val reps = findViewById<EditText>(R.id.repsEsercizio)
//        val serie = findViewById<EditText>(R.id.serieEsercizio)
//        val UID = findViewById<TextView>(R.id.tvUID)
//        var note: String = ""
//
//        btnAdd.setOnClickListener(){
//
//            val nomeEsercizio = nomeEsercizio.text.toString()
//            val reps = reps.text.toString().toInt()
//            val serie = serie.text.toString().toInt()
//
//
//            val esercizio = hashMapOf(
//                "nome" to nomeEsercizio ,
//                "reps" to reps,
//                "serie" to serie
//            )
//
////            val db = FirebaseFirestore.getInstance()
////            val dataB = db.collection("Atleti")
////
////            val query = dataB
////                .whereEqualTo("UIDatleti", mAuth.currentUser?.uid)
////
////            query.get().addOnSuccessListener { querySnapshot ->
////
////                for (dc : DocumentChange in querySnapshot.documentChanges){
////                    note = dc.document.getString("UIDatleti").toString1()
////                    Log.d(TAG, "*************PROVAAAAAA ${note}")
////                }
////                if (mAuth.currentUser?.uid != note) {
////
////                    val utenti = hashMapOf(
////                        "nome" to mAuth.currentUser?.displayName,
////                        "UIDatleti" to (mAuth.uid.toString1())
////                    )
////
////                    db.collection("Atleti")
////                        .add(utenti)
////                        .addOnSuccessListener {
////                            Log.d(
////                                TAG,
////                                "DocumentSnapshot successfully written!"
////                            )
////                        }
////                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
////
////                    Log.d(TAG, "Dentro con registrazione utente")
////                    Toast.makeText(this, " Login effettuato ", Toast.LENGTH_SHORT).show()
////
////                    //apre AthleteActivity
////                    Intent(this, AthleteActivity::class.java).also {
////                        startActivity(it)
////                    }
////                }else{
////                    Log.d(TAG, "Dentro senza registrazione utente")
////                    Toast.makeText(this, " Login effettuato ", Toast.LENGTH_SHORT).show()
////                    //apre AthleteActivity
////                    Intent(this, AthleteActivity::class.java).also {
////                        startActivity(it)
////
////                    }
////                }
////            }
////
////            Log.d("TAG", "*********** TVUID   " + UID.text.toString())
////            val db = FirebaseFirestore.getInstance()
////            val dataB = db.collection("Atleti")
////            val query = dataB
////                .whereEqualTo("UIDatleti", UID.text.toString())
////
////            query.get().addOnSuccessListener { querySnapshot ->
////
////                for (dc: DocumentChange in querySnapshot.documentChanges) {
////                    var note = dc.document.getString("UIDatleti").toString()
////                    Log.d("TAG", "*************PROVAAAAAA ${note}")
////                }
////                if(tvUID.text == note){
////                    db.collection("Atleti").document("")
////                        .set(esercizio)
////                        .addOnSuccessListener {
////                            Log.d("ADD DATA", "DocumentSnapshot successfully written!")
////                            Toast.makeText(this, " Esercizio Aggiunto ", Toast.LENGTH_SHORT).show()
////                        }
////
////                        .addOnFailureListener { e -> Log.w("ADD DATA", "Error writing document", e) }
////                }
////            }
//
//            db.collection("Atleti").document(UID.toString())
//                .set(esercizio)
//                .addOnSuccessListener {
//                    Log.d("ADD DATA", "DocumentSnapshot successfully written!")
//                    Toast.makeText(this, " Esercizio Aggiunto ", Toast.LENGTH_SHORT).show()
//                }
//
//                .addOnFailureListener { e -> Log.w("ADD DATA", "Error writing document", e) }
//
//
//        }
//    }
//}

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
        val nomeEsercizio = findViewById<EditText>(R.id.nomeEsercizio)
        val reps = findViewById<EditText>(R.id.repsEsercizio)
        val serie = findViewById<EditText>(R.id.serieEsercizio)
        val UID:String = intent.getStringExtra("uidAtleti").toString()
        val nomeAtleta:String = intent.getStringExtra("nomeAtleta").toString()



        btnAdd.setOnClickListener(){

            val nomeEsercizio = nomeEsercizio.text.toString()
            val reps = reps.text.toString().toInt()
            val serie = serie.text.toString().toInt()

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
                }

                .addOnFailureListener { e -> Log.w("ADD DATA", "Error writing document", e) }
        }
    }
}