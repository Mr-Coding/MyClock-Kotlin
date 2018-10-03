package com.frank.myclock.activity

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import com.frank.myclock.Data
import com.frank.myclock.extend.setActivityFullScreen
import com.frank.myclock.extend.timeRunning
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.frank.myclock.R
import com.frank.myclock.extend.showWelcome
import com.frank.myclock.service.BatteryService
import com.frank.myclock.util.APKVersionCodeUtils
import com.frank.myclock.view.ChangeLayoutSwitch
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_drawer2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.panel_settings.*

abstract class BaseActivity : Activity(){
    private val data:Data by lazy { Data(this) }
    private var batteryService:BatteryService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        batteryService = BatteryService{
            battery_progress.progress = it
        }
        registerReceiver(batteryService, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        if (isKeepScreenOn()){
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }

        if (setLayoutRes() == ChangeLayoutSwitch.STATE.A.toString()) {
            setContentView(R.layout.activity_drawer)
            if (canOpenMenu()){
                draw_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

                if(canOpenMenu()){
                    draw_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    main_layout.setOnLongClickListener {
                        draw_layout.openDrawer(Gravity.LEFT)
                        true
                    }
                    stylesetting_btn.setOnClickListener{
                        draw_layout.closeDrawer(Gravity.LEFT)
                        draw_layout.openDrawer(Gravity.RIGHT)
                    }
                }
            }
        }else if (setLayoutRes() == ChangeLayoutSwitch.STATE.B.toString()){
            setContentView(R.layout.activity_drawer2)

            if (canOpenMenu()){
                draw_layout2.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                if(canOpenMenu()){
                    draw_layout2.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    main_layout.setOnLongClickListener {
                        draw_layout2.openDrawer(Gravity.LEFT)
                        true
                    }
                    stylesetting_btn.setOnClickListener{
                        draw_layout2.closeDrawer(Gravity.LEFT)
                        draw_layout2.openDrawer(Gravity.RIGHT)
                    }
                }
            }
        }

        init()

        showWelcome(data)



        version_name.text = "版本  V "+APKVersionCodeUtils.getVersionName(this)
    }


    override fun onResume() {
        super.onResume()
        setActivityFullScreen(setFullScreen())
        timeRunning()
    }

    override fun onRestart() {
        super.onRestart()
        setActivityFullScreen(setFullScreen())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryService)
    }

    protected abstract fun isKeepScreenOn():Boolean

    protected abstract fun setLayoutRes():String

    protected abstract fun setFullScreen():Boolean

    protected abstract fun canOpenMenu():Boolean

    protected abstract fun init()

}
