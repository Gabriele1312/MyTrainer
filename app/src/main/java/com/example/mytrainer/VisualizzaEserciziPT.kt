package com.example.mytrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.lista_atleti_item.*

class VisualizzaEserciziPT : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eserciziArrayList: ArrayList<Esercizi>
    private lateinit var myAdapter: MyAdapterSchedaPT
    private lateinit var db: FirebaseFirestore

    companion object{
        lateinit var facebookID: String
        lateinit var idDocument: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizza_esercizi_pt)


        recyclerView = findViewById(R.id.rwVisualizzaEsercizi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        eserciziArrayList = arrayListOf()
        myAdapter = MyAdapterSchedaPT(eserciziArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListener()
    }

    //visualizza esercizi
    private fun EventChangeListener() {
        val UID:String = intent.getStringExtra("uidAtleti").toString()
        db = FirebaseFirestore.getInstance()
        db.collection("Esercizi/Atleti/${UID}")

            .orderBy("nome", Query.Direction.ASCENDING)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("FIRE STORE ERROR", error.message.toString())
                }

                for(dc : DocumentChange in value?.documentChanges!!){

                    if(dc.type == DocumentChange.Type.ADDED){
                        eserciziArrayList.add(dc.document.toObject(Esercizi::class.java))

                    }
                }

                myAdapter.notifyDataSetChanged()
            }
    }
}