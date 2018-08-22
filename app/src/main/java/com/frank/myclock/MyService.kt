package com.frank.myclock

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.frank.myclock.activity.LockActivity

class MyService:Service(){
    private val myBroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate() {
        super.onCreate()
        Log.i("MyService","MyService创建");
        val intentFilter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(myBroadcastReceiver,intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notification = Notification.Builder(this,"LockScreen ID")
                    .setContentText(getString(R.string.app_name)+"正在运行.")
                    .setSmallIcon(R.drawable.ic_clock)
                    .build()
            startForeground(1,notification)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MyService","MyService销毁");
        unregisterReceiver(myBroadcastReceiver)
    }

    override fun onBind(p0: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class MyBroadcastReceiver:BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent?.action == Intent.ACTION_SCREEN_OFF){
            val lockScreenIntent = Intent(context, LockActivity::class.java)
            lockScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            val pendingIntent = PendingIntent.getActivity(context,0,lockScreenIntent,0)
            pendingIntent.send()
        }
        
    }

}