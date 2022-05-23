package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import com.google.firebase.internal.InternalTokenProvider

class FocusEsercizio : AppCompatActivity() {

    lateinit var CountTimer: CountDownTimer
    private var MtimeRunning: Boolean = false
    private var MtimeLeftinMillis: Long = 0
    lateinit var value1 : CharSequence
    lateinit var buttonvalue: String

    val startbtn = findViewById<Button>(R.id.start)
    val mtextView = findViewById<TextView>(R.id.time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_esercizio)

        val intent = Intent()
        buttonvalue = intent.getStringExtra("value").toString()

        var intvalue = buttonvalue?.toInt()

        when(intvalue){
            1 -> setContentView(R.layout.chest_press)
            2 -> setContentView(R.layout.squat_jump)
            3 -> setContentView(R.layout.leg_extensions)
        }


        startbtn.setOnClickListener(){
            if(MtimeRunning){
                stopTimer()
            }
            else {
                startTimer()
            }
        }

     }



    private fun stopTimer(){
        CountTimer.cancel()
        MtimeRunning = false
        startbtn.text = "START"
    }


    private fun startTimer(){

        value1 = mtextView.text
        var num1 = value1.toString()
        var num2 = num1.substring(0,2)
        var num3 = num1.substring(3,5)

        val number = num2.toInt() * 60 + num3.toInt()
        MtimeLeftinMillis = (number*1000).toLong()

        CountTimer =  object : CountDownTimer(MtimeLeftinMillis, 1000) {
            override fun onTick(p0: Long) {
                MtimeLeftinMillis = p0
                updateTimer()
            }

            override fun onFinish() {
                var newValue = buttonvalue.toInt()
                if(newValue<=7){
                    val i = Intent(this@FocusEsercizio, FocusEsercizio::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    i.putExtra("value", newValue.toString())
                    startActivity(i)
                }

                else{
                    newValue = 1
                    val intent = Intent(this@FocusEsercizio, FocusEsercizio::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.putExtra("value", newValue.toString())
                    startActivity(intent)
                }
            }

        }.start()
        startbtn.text = "PAUSA"
        MtimeRunning = true

    }

    private fun updateTimer(){
        var minutes = (MtimeLeftinMillis.toInt()) / 60000
        var seconds = (MtimeLeftinMillis.toInt()) % 60000 / 1000


        var timeLeftText = ""
        if(minutes < 10){
            timeLeftText = "0"
        }

        timeLeftText = "$timeLeftText$minutes:"

        if(seconds < 10){
            timeLeftText += "0"
        }

        timeLeftText += seconds
        mtextView.text = timeLeftText
    }

}
