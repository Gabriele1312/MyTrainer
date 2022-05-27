package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth


class DashboardActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var btnLogout = findViewById<Button>(R.id.btn_logout)

        btnLogout?.setOnClickListener{

            mAuth?.signOut()
            LoginManager.getInstance().logOut()

            updateUI()
        }


        var btnPersonal = findViewById<Button>(R.id.btn_pt)

        btnPersonal.setOnClickListener(){
            val personal = Intent(this@DashboardActivity, TrainerActivity::class.java)
            startActivity(personal)
        }

        var btnAtleti = findViewById<Button>(R.id.btn_atleta)
        btnAtleti.setOnClickListener(){
            val atleti = Intent(this@DashboardActivity, AthleteActivity::class.java)
            startActivity(atleti)
        }

    }


//    override fun onStart() {
//        super.onStart()
//        val currentUser = mAuth?.currentUser
//
//        if(currentUser != null){
//            updateUI()
//        }
//    }

    private fun updateUI() {
        Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        //apre login
        val Login = Intent(this, LoginActivity::class.java)
        startActivity(Login)
    }

}