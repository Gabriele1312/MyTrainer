package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_athlete.*

class AthleteActivity : AppCompatActivity() {

    val newArray = arrayOf(
        R.id.chest_press,R.id.squat_jump, R.id.leg_extensions
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_athlete)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setActionBar(toolbar)

    }

    fun ImageButtonClicked(view: View) {

        for (item in newArray){
            if(view.id == newArray[item]){
                val value = item + 1
                Log.i("FIRST", item.toString())

                val intent = Intent(this@AthleteActivity, FocusEsercizio::class.java)
                intent.putExtra("value", item.toString())
                startActivity(intent)
            }
        }
    }
}