package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth


class DashboardActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    lateinit var im_personalTrainer: ImageView
    lateinit var im_user: ImageView
    lateinit var im_timer: ImageView
    lateinit var im_exit: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        im_personalTrainer = findViewById(R.id.im_personalTrainer)
        im_user = findViewById(R.id.im_user)
        im_timer = findViewById(R.id.im_timer)
        im_exit = findViewById(R.id.im_exit)

       // var btnLogout = findViewById<Button>(R.id.btn_logout)

        im_exit?.setOnClickListener{

            mAuth?.signOut()
            LoginManager.getInstance().logOut()

            updateUI()
        }




        im_personalTrainer.setOnClickListener(){
            val personal = Intent(this@DashboardActivity, TrainerActivity::class.java)
            startActivity(personal)
        }


        im_user.setOnClickListener(){
            val atleti = Intent(this@DashboardActivity, AthleteActivity::class.java)
            startActivity(atleti)
        }


        im_timer.setOnClickListener(){
            val timer = Intent(this@DashboardActivity, Timer::class.java)
            startActivity(timer)
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