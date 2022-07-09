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

        im_exit.setOnClickListener{

            mAuth?.signOut()
            LoginManager.getInstance().logOut()
            Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        }




        im_personalTrainer.setOnClickListener(){
            val personal = Intent(this@DashboardActivity, LoginActivityPT::class.java)
            startActivity(personal)
        }


        im_user.setOnClickListener(){
            val atleti = Intent(this@DashboardActivity, LoginActivity::class.java)
            startActivity(atleti)
        }


        im_timer.setOnClickListener(){
            val timerHome = Intent(this@DashboardActivity, TimerHome::class.java)
            startActivity(timerHome)
        }

    }

    private fun updateUI() {
        Toast.makeText(this, " LogOut ", Toast.LENGTH_SHORT).show()

        //apre login
        val Login = Intent(this, LoginActivity::class.java)
        startActivity(Login)
    }

}