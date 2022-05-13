package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var btnLogout = findViewById<Button>(R.id.login_button)

        btnLogout.setOnClickListener(){

            mAuth.signOut()
            LoginManager.getInstance().logOut()

            updateUI()
        }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser

        if(currentUser == null){
            updateUI()
        }
    }

    private fun updateUI() {
        Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        //apre Dashboard
        val dashboard = Intent(this, LoginActivity::class.java)
        startActivity(dashboard)
        finish()
    }
}