package com.example.mytrainer

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase

class AthleteActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var eserciziArrayList: ArrayList<Esercizi>
    private lateinit var myAdapter: MyAdapterEsercizi
    private lateinit var db: FirebaseFirestore
    private lateinit var buttonTimer: Button
//    val UID:String = intent.getStringExtra("uidAtleti").toString()
//    val UID:String = mAuth?.currentUser?.uid.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_athlete)

        var btnLogout = findViewById<Button>(R.id.btn_logout2)
        mAuth= Firebase.auth

        btnLogout?.setOnClickListener{

            mAuth.signOut()
            LoginManager.getInstance().logOut()

            updateUI()
        }

        recyclerView = findViewById(R.id.rwEsercizi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        eserciziArrayList = arrayListOf()
        myAdapter = MyAdapterEsercizi(eserciziArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListener()

    }

    private fun updateUI() {
        Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        //apre login
        val Login = Intent(this, DashboardActivity::class.java)
        startActivity(Login)
    }


    private fun EventChangeListener() {
        Log.d("TAG", "*******************: ${mAuth.currentUser?.uid.toString()}")
        db = FirebaseFirestore.getInstance()
        db.collection("Esercizi/Atleti/${mAuth.currentUser?.uid.toString()}").orderBy("nome", Query.Direction.ASCENDING)
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