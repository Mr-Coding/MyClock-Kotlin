package com.frank.myclock.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryService(val callbacks:((level:Int)->Unit)?=null) : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_CHANGED){
            val level = intent.getIntExtra("level",0)
            callbacks?.invoke(level)
        }
    }
}