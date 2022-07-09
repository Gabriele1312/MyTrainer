package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth

class TrainerActivity : AppCompatActivity() {

    lateinit var userList: ImageView
    lateinit var logout: ImageView

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)

       logout = findViewById(R.id.im_logout)

        logout.setOnClickListener{

            mAuth?.signOut()
            LoginManager.getInstance().logOut()

            updateUI()
        }

        userList = findViewById(R.id.im_user_list)
        userList.setOnClickListener(){
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