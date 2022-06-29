package com.example.mytrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.ImageView

class Timer : AppCompatActivity() {

    private lateinit var playBtn: ImageView
    private lateinit var pauseBtn: ImageView
    private lateinit var cronometro: Chronometer
    private lateinit var exit: ImageView
    var isplay = false
    var pauseOffSet = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        playBtn = findViewById(R.id.btnplay)
        pauseBtn = findViewById(R.id.btnpausa)
        cronometro = findViewById(R.id.cronometro)
        exit = findViewById(R.id.exit)

        playBtn.setOnClickListener(){
            if(!isplay){
                cronometro.base = SystemClock.elapsedRealtime() - pauseOffSet
                cronometro.start()
                pauseBtn.visibility = View.VISIBLE
                playBtn.setImageResource(R.drawable.ic_baseline_stop_24)
                isplay = true
            }

            else{
                cronometro.base = SystemClock.elapsedRealtime()
                pauseOffSet = 0
                cronometro.stop()
                playBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                pauseBtn.visibility = View.GONE
                isplay = false
            }
        }

        pauseBtn.setOnClickListener(){
            if(isplay){
                cronometro.stop()
                pauseOffSet = (SystemClock.elapsedRealtime() - cronometro.base).toInt()
                isplay = false
                pauseBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }

            else{
                cronometro.base = SystemClock.elapsedRealtime() - pauseOffSet
                cronometro.start()
                pauseBtn.setImageResource(R.drawable.ic_baseline_pause_24)
                isplay = true
            }
        }

        exit.setOnClickListener(){

            val exit = Intent(this@Timer, AthleteActivity::class.java)
            startActivity(exit)
        }

    }



}
