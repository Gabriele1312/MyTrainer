package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TrainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)

        val btnUtenti = findViewById<Button>(R.id.btn_listaAtleti)
        btnUtenti.setOnClickListener(){
            val intent = Intent(this, ListaAtleti::class.java)
            startActivity(intent)
        }
    }

}