package com.frank.myclock.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.frank.myclock.R

class SplashActivity : AppCompatActivity() {
    private val context = this
    private val thread  = object : Thread() {
        override fun run() {
            super.run()
            try {
                sleep(3000)
                val intent = Intent(applicationContext,MainActivity::class.java)
                context.startActivity(intent)
                context.finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        thread.start()
    }
}
