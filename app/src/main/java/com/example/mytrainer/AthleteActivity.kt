package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AthleteActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eserciziArrayList: ArrayList<Esercizi>
    private lateinit var myAdapter: MyAdapterEsercizi
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_athlete)

        recyclerView = findViewById(R.id.rwEsercizi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        eserciziArrayList = arrayListOf()
        myAdapter = MyAdapterEsercizi(eserciziArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListener()
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Esercizi").orderBy("nome", Query.Direction.ASCENDING)
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