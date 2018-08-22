package com.frank.myclock.extend

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.View
import com.frank.myclock.Data
import com.frank.myclock.time.Time
import com.frank.myclock.time.TimeThread
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.content.ComponentName
import android.graphics.Typeface
import com.frank.myclock.activity.LaunchActivity


/**
 * Activity的扩展方法
 */

/**
 * 各种设置
 */
fun Activity.timeRunning(){
    val data: Data by lazy { Data(this) }
    val time = TimeThread
    var isHide = false

    if (!time.isAlive){
        time.isRunning = true
        time.start()
    }

    time.setTimeListener(
            on1s = {
                runOnUiThread { kotlin.run {
                    h_tv.text = if (!data.is12HourClock()){
                        Time.hour()
                    }else{
                        Time.hourOfDay()
                    }
                    m_tv.text = Time.minute()
                    ampm_tv.text = Time.halfDay()
                    week_tv.text = Time.week()
                    s_tv.text = Time.second()
                    if (data.isFlashingColon()) {
                        if (isHide) {
                            colon_tv.alpha = 0f
                        } else {
                            colon_tv.alpha = 1f
                        }
                        isHide = !isHide
                    }
                } }
            },
            on30s = {
                runOnUiThread { kotlin.run {
                        if (data.isStartMove()) {
                            move_layout.startMove(data.isStartMove())
                        }
                } }
            }
    )
}

/**
 * 开始各种初始化和设置
 */
fun Activity.actionSettings(data: Data){
//    val typeface = Typeface.createFromAsset(assets,"font/ds_digi.ttf")
    setHour(data)
    setMinute(data)
    setSecond(data)
    setAMPM(data)
    setWeek(data)
    setYMD(data)
    setTextStyle(data)
    setGradualChange(data)
    setOrientation(data)
    setLauncher(data)
    setBackgroundImg(data)
    setFlashingColon(data)
    setLock(data)
}

/**
 * 设制为桌面
 */
fun Activity.setLauncher(isLauncher:Boolean){
    val packageManager = packageManager
    val componentName = ComponentName(this, LaunchActivity::class.java)
    var flag = 0
    if (isLauncher) {
        flag = PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    } else {
        flag = PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    }
    packageManager.setComponentEnabledSetting(componentName, flag, PackageManager.DONT_KILL_APP)
}

/**
 * 设置屏幕方向
 */
fun Activity.setOrientation(state: String){
    when(state){
        "AUTO" -> {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
        "PORTRAIT" -> {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        "LANDSCAPE" -> {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
}

/**
 * 隐藏虚拟按键，并且全屏
 * @param isFullScreeen 是否全屏
 */
fun Activity.setActivityFullScreen(isFullScreeen:Boolean){
    if (!isFullScreeen) {
        return
    }
    if (Build.VERSION.SDK_INT < 19){
        val view = this.window.decorView
        view.systemUiVisibility = View.GONE
    }else if (Build.VERSION.SDK_INT >= 19){
        val view = this.window.decorView
        val uiOption = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN);
        view.systemUiVisibility = uiOption
    }
}

/**
 * 设置Activity透明度
 * @param bgAlpha 透明度
 */
fun Activity.setActivityBgAlpha(bgAlpha: Float){
    val lp = this.window.attributes
    lp.alpha = bgAlpha
    this.window.attributes = lp
}