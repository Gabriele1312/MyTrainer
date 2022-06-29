package com.example.mytrainer

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class AthleteActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var eserciziArrayList: ArrayList<Esercizi>
    private lateinit var myAdapter: MyAdapterEsercizi
    private lateinit var db: FirebaseFirestore
    private lateinit var buttonTimer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_athlete)

        var btnLogout = findViewById<Button>(R.id.btn_logout2)

        btnLogout?.setOnClickListener{

            mAuth?.signOut()
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

        //il bottone non è sul layout di questa pagina, quindi devo richiamarlo.
        //setContentView(R.layout.lista_esercizi_item)
        //buttonTimer = findViewById(R.id.btn_timer_go)
        //buttonTimer.setOnClickListener(){

        //val i = Intent(this, Timer::class.java)
            //startActivity(i)
        //}
    }

    private fun updateUI() {
        Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        //apre login
        val Login = Intent(this, DashboardActivity::class.java)
        startActivity(Login)
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