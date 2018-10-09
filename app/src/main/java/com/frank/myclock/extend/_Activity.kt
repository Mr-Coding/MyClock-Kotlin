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
import android.content.Context
import android.graphics.Color
import android.hardware.SensorManager
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.WindowManager
import com.frank.myclock.R
import com.frank.myclock.activity.LaunchActivity
import com.frank.myclock.device.ActivityBrightnessManager
import com.frank.myclock.device.MySensor
import com.frank.myclock.util.APKVersionCodeUtils
import com.frank.myclock.view.ChangeLayoutSwitch
import com.frank.myclock.view.MyDialog
import com.frank.myclock.view.TextSizeSwitch


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
                    h_tv.text    = if (!data.is12HourClock()){
                        Time.hour()
                    }else{
                        Time.hourOfDay()
                    }
                    m_tv.text    = Time.minute()
                    ampm_tv.text = Time.halfDay()
                    week_tv.text = Time.week()
                    s_tv.text    = Time.second()
                    ymd_tv.text  = if (!data.isShowLunar()){
                        Time.YMD()
                    }else{
                        Time.YMDAsLunar()
                    }
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

fun Activity.anyInit(data: Data){
    fun setGradualChange(isGradualChange:Boolean){
        h_tv.setGradualChange(isGradualChange)
        m_tv.setGradualChange(isGradualChange)
        s_tv.setGradualChange(isGradualChange)
        ymd_tv.setGradualChange(isGradualChange)
        ampm_tv.setGradualChange(isGradualChange)
        week_tv.setGradualChange(isGradualChange)
        colon_tv.setGradualChange(isGradualChange)
    }

    h_tv.setColor(data.getTextColor())
    m_tv.setColor(data.getTextColor())
    s_tv.setColor(data.getTextColor())
    ymd_tv.setColor(data.getTextColor())
    ampm_tv.setColor(data.getTextColor())
    week_tv.setColor(data.getTextColor())
    colon_tv.setColor(data.getTextColor())

    MySensor(getSystemService(Context.SENSOR_SERVICE) as SensorManager?).setOnLightChanged(object : MySensor.OnLightChanged{
        override fun onHeight() {
            if (data.isBrightness()) {
                ActivityBrightnessManager.changeAppBrightness(applicationContext, -1)
            }
        }
        override fun onLow() {
            if (data.isBrightness()){
                ActivityBrightnessManager.changeAppBrightness(applicationContext, 5);
            }
        }
    })

    h_tv.setVisibility(data.isShowHour())
    h_tv.text = if (!data.is12HourClock()){
        Time.hour()
    }else{
        Time.hourOfDay()
    }

    m_tv.setVisibility(data.isShowMinute())
    m_tv.text = Time.minute()

    colon_tv.setVisibility(data.isShowHour() and data.isShowMinute())

    s_tv.setVisibility(data.isShowSecond())
    s_tv.text = Time.second()

    ampm_tv.setVisibility(data.isShowAMPM())
    ampm_tv.text = Time.halfDay()

    week_tv.setVisibility(data.isShowWeek())
    week_tv.text = Time.week()

    ymd_tv.setVisibility(data.isShowYMD())
    ymd_tv.setVisibility(data.isShowYMD())

    setGradualChange(data.isGradualChange())
    setTextSize2(data)

}

/**
 * 开始各种初始化和设置
 */
fun Activity.setListener(data: Data){
//    val typeface = Typeface.createFromAsset(assets,"font/ds_digi.ttf")
    setHour(data)
    setMinute(data)
    setSecond(data)
    setAMPM(data)
    setWeek(data)
    setYMD(data)
    setTextStyle(data)
    setStartMove(data)
    setOrientation(data)
    setLauncher(data)
    setBackgroundImg(data)
    setFlashingColon(data)
//    setLock(data)
    setBrightness(data)
    pay()
    setTextColor(data)
    setTextSize(data)
    setNavigationBarColor()
    setKeepScreenOn(data)
    setBatteryProgress(data)
}

fun Activity.setTextSize2(data: Data){
    changeLayout(data)
}
// 更换布局和字体大小
fun Activity.changeLayout(data: Data){
    when(data.getLayout()){
        ChangeLayoutSwitch.STATE.A.toString() -> {
            when(data.getTextSize()){
                TextSizeSwitch.STATE.AUTO.toString() ->{
                    autoAllSize(R.dimen.large_1,R.dimen.small_1)
                }
                TextSizeSwitch.STATE.BIG.toString() -> {
                    bigAllSize(R.dimen.large_1,R.dimen.small_1)
                }
                TextSizeSwitch.STATE.SMALL.toString() -> {
                    smallAllSize(R.dimen.large_1,R.dimen.small_1)
                }
            }
        }
        ChangeLayoutSwitch.STATE.B.toString() -> {
            when(data.getTextSize()){
                TextSizeSwitch.STATE.AUTO.toString() ->{
                    autoAllSize(R.dimen.large_2,R.dimen.small_2)
                }
                TextSizeSwitch.STATE.BIG.toString() -> {
                    bigAllSize(R.dimen.large_2,R.dimen.small_2)
                }
                TextSizeSwitch.STATE.SMALL.toString() -> {
                    smallAllSize(R.dimen.large_2,R.dimen.small_2)
                }
            }
        }
    }
}

fun Activity.bigAllSize(largeResId:Int,smallResId:Int){
    h_tv.toBigSize(resources.getDimension(largeResId))
    m_tv.toBigSize(resources.getDimension(largeResId))
    colon_tv.toBigSize(resources.getDimension(largeResId))
    ymd_tv.toBigSize(resources.getDimension(smallResId))
    week_tv.toBigSize(resources.getDimension(smallResId))
    ampm_tv.toBigSize(resources.getDimension(smallResId))
    s_tv.toBigSize(resources.getDimension(smallResId))
}
fun Activity.smallAllSize(largeResId:Int,smallResId:Int){
    h_tv.toSmallSize(    resources.getDimension(largeResId))
    m_tv.toSmallSize(    resources.getDimension(largeResId))
    colon_tv.toSmallSize(resources.getDimension(largeResId))
    ymd_tv.toSmallSize(  resources.getDimension(smallResId))
    week_tv.toSmallSize( resources.getDimension(smallResId))
    ampm_tv.toSmallSize( resources.getDimension(smallResId))
    s_tv.toSmallSize(    resources.getDimension(smallResId))
}
fun Activity.autoAllSize(largeResId:Int,smallResId:Int){
    h_tv.toAutoSize(    resources.getDimension(largeResId))
    m_tv.toAutoSize(    resources.getDimension(largeResId))
    colon_tv.toAutoSize(resources.getDimension(largeResId))
    ymd_tv.toAutoSize(  resources.getDimension(smallResId))
    week_tv.toAutoSize( resources.getDimension(smallResId))
    ampm_tv.toAutoSize( resources.getDimension(smallResId))
    s_tv.toAutoSize(    resources.getDimension(smallResId))
}

fun Activity.showWelcome(data: Data){
    if (data.getVersionCode() != APKVersionCodeUtils.getVersionCode(this)) {
        data.setVersionCode(APKVersionCodeUtils.getVersionCode(this))
        MyDialog.showWelcome(this)
    }
}

fun Activity.setNavigationBarColor(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setNavigationBarColor(Color.BLACK);
    }
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
        view.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
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
