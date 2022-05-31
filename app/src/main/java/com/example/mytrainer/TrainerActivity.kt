package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth

class TrainerActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)

        var btnLogout = findViewById<Button>(R.id.btn_logout3)

        btnLogout?.setOnClickListener{

            mAuth?.signOut()
            LoginManager.getInstance().logOut()

            updateUI()
        }

        val btnUtenti = findViewById<Button>(R.id.btn_listaAtleti)
        btnUtenti.setOnClickListener(){
            val intent = Intent(this, ListaAtleti::class.java)
            startActivity(intent)
        }
    }

    private fun updateUI() {
        Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        //apre login
        val Login = Intent(this, DashboardActivity::class.java)
        startActivity(Login)
    }

}