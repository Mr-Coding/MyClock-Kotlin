package com.frank.myclock.extend

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Context.SENSOR_SERVICE
import com.frank.myclock.Data
import com.frank.myclock.time.Time
import com.frank.myclock.view.MySwitch
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.panel_settings.*
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.frank.myclock.MyService
import com.frank.myclock.R
import com.frank.myclock.device.ActivityBrightnessManager.changeAppBrightness
import com.frank.myclock.device.MySensor
import com.frank.myclock.util.Pay
import com.frank.myclock.view.MyDialog
import kotlinx.android.synthetic.main.panel_style_settings.*
import com.divyanshu.colorseekbar.*
import com.frank.myclock.view.ChangeLayoutSwitch
import com.frank.myclock.view.TextSizeSwitch


fun Activity.setHour(data: Data) {
    h_tv.text = if (!data.is12HourClock()){
        Time.hour()
    }else{
        Time.hourOfDay()
    }
    h_tv.setVisibility(data.isShowHour())
    colon_tv.setVisibility(data.isShowHour() and data.isShowMinute())
    showhour_btn.isChecked = data.isShowHour()
    showhour_btn.setOnCheckedChangeListener{ view,isCecked ->
        h_tv.setVisibility(isCecked)
        data.setShowHour(isCecked)
        colon_tv.setVisibility(data.isShowHour() and data.isShowMinute())
    }
    hourofday_chk.isChecked = data.is12HourClock()
    hourofday_chk.setOnCheckedChangeListener { view, isChecked ->
        data.set12HourClock(isChecked)
        h_tv.text = if (!isChecked){
            Time.hour()
        }else{
            Time.hourOfDay()
        }
    }
}

//设置分钟
fun Activity.setMinute(data: Data) {
    m_tv.text = Time.minute()
    m_tv.setVisibility(data.isShowMinute())
    colon_tv.setVisibility(data.isShowHour() and data.isShowMinute())
    showminute_btn.isChecked = data.isShowMinute()
    showminute_btn.setOnCheckedChangeListener {
        view, isChecked ->
        data.setShowMinute(isChecked)
        m_tv.setVisibility(isChecked)
        colon_tv.setVisibility(data.isShowHour() and data.isShowMinute())
    }
}

//秒钟
fun Activity.setSecond(data: Data){
    s_tv.text = Time.second()
    s_tv.setVisibility(data.isShowSecond())
    showsecond_btn.isChecked = data.isShowSecond()
    showsecond_btn.setOnCheckedChangeListener {
        view, isChecked ->
        data.setShowSecond(isChecked)
        s_tv.setVisibility(isChecked)
    }
}
//AMPM
fun Activity.setAMPM(data: Data) {
    ampm_tv.text = Time.halfDay()
    ampm_tv.setVisibility(data.isShowAMPM())
    showampm_chk.isChecked = data.isShowAMPM()
    showampm_chk.setOnCheckedChangeListener {
        view, isChecked ->
        data.setShowAMPM(isChecked)
        ampm_tv.setVisibility(isChecked)
    }
}
//周
fun Activity.setWeek(data: Data) {
    week_tv.text = Time.week()
    week_tv.setVisibility(data.isShowWeek())
    showweek_chk.isChecked = data.isShowWeek()
    showweek_chk.setOnCheckedChangeListener {
        view, isChecked ->
        data.setShowWeek(isChecked)
        week_tv.setVisibility(isChecked)
    }
}
//年月日
fun Activity.setYMD(data: Data) {
    ymd_tv.setVisibility(data.isShowYMD())
    ymd_tv.text = if (!data.isShowLunar()){
        Time.YMD()
    }else{
        Time.YMDAsLunar()
    }

    ymd_tv.setOnClickListener {
        showlunar_chk.isChecked = !data.isShowLunar()
    }

    showymd_chk.isChecked = data.isShowYMD()
    showymd_chk.setOnCheckedChangeListener { view, isChecked ->
        data.setShowYMD(isChecked)
        ymd_tv.setVisibility(isChecked)
    }

    showlunar_chk.isChecked = data.isShowLunar()
    showlunar_chk.setOnCheckedChangeListener { view, isChecked ->
        data.setShowLunar(isChecked)
        ymd_tv.text = if (!isChecked){
            Time.YMD()
        }else{
            Time.YMDAsLunar()
        }
    }
}

//文字样式设置
fun Activity.setTextStyle(data: Data){
    fun setGradualChange(isGradualChange:Boolean){
        h_tv.setGradualChange(isGradualChange)
        m_tv.setGradualChange(isGradualChange)
        s_tv.setGradualChange(isGradualChange)
        ymd_tv.setGradualChange(isGradualChange)
        ampm_tv.setGradualChange(isGradualChange)
        week_tv.setGradualChange(isGradualChange)
        colon_tv.setGradualChange(isGradualChange)
    }

    setGradualChange(data.isGradualChange())
    gradualchange_btn.isChecked = data.isGradualChange()
    gradualchange_btn.setOnCheckedChangeListener{
        view, isChecked ->
        data.setGradualChange(isChecked)
        setGradualChange(isChecked)
    }
}

//文字移动设置
fun Activity.setStartMove(data: Data){
    startmove_btn.isChecked = data.isStartMove()
    startmove_btn.setOnCheckedChangeListener{
        _,isChecked ->
        data.setStartMove(isChecked)
        if(!isChecked){
            move_layout.restore()
        }
    }
}

/**
 * 设置屏幕方向
 */
fun Activity.setOrientation(data: Data){
    setOrientation(data.getOrientation())

    when(data.getOrientation()){
        MySwitch.STATE.AUTO.toString() -> {
            my_switch.setChosen(MySwitch.BTN.AUTO_BTN)
        }
        MySwitch.STATE.PORTRAIT.toString() -> {
            my_switch.setChosen(MySwitch.BTN.PORTRAIT_BTN)
        }
        MySwitch.STATE.LANDSCAPE.toString() -> {
            my_switch.setChosen(MySwitch.BTN.LANDSCAPE_BTN)
        }
    }

    my_switch.onChosen {
        data.setOrientation(it)
        setOrientation(it)
    }
}

fun Activity.setLauncher(data: Data){
    setLauncher(data.isLaunch())
    setlaunch_chk.isChecked = data.isLaunch()
    setlaunch_chk.setOnCheckedChangeListener { view, isChecked ->
        data.setLaunch(isChecked)
        setLauncher(isChecked)
        if (isChecked) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
        }
    }
}

fun Activity.setBackgroundImg(data: Data){
    Glide.with(this).load(data.getImg()).into(findViewById(R.id.bg))
    Glide.with(this).load(data.getImg()).into(findViewById(R.id.showbg_iv))

    addbg_btn.setOnClickListener { it ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                MyDialog.showPermission(this) {
                    ActivityCompat.requestPermissions(
                            this, Array<String>(1) { READ_EXTERNAL_STORAGE }, 1
                    )
                }
            } else {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                startActivityForResult(intent, 1)
            }
        }else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 1)
        }
    }

    clearbg_btn.setOnClickListener{
        data.setImg("")
        Glide.with(this).clear(findViewById<ImageView>(R.id.bg))
        Glide.with(this).clear(findViewById<ImageView>(R.id.showbg_iv))
    }
}
// 冒号闪动
fun Activity.setFlashingColon(data: Data){
    flashingcolon_chk.isChecked = data.isFlashingColon()
    flashingcolon_chk.setOnCheckedChangeListener{ view,isChecked ->
        data.setFlashingColon(isChecked)
        if (!isChecked){
            colon_tv.alpha = 1f
        }
    }
}

fun Activity.setLock(data: Data) {

    fun isLock(isLock: Boolean) {
        Log.i("Activity","Lock: $isLock")
        val lockIntent = Intent(this, MyService::class.java)
        if (isLock) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(lockIntent)
            } else {
                startService(lockIntent)
            }
        }else{
            stopService(lockIntent)
        }
    }

    isLock(data.isLock())
    lock_chk.isChecked = data.isLock()
    lock_chk.setOnCheckedChangeListener { view, isChecked ->
        data.setLock(isChecked)
        isLock(isChecked)
    }

}

fun Activity.pay(){
    ali_pay_iv.setOnClickListener {
        Pay.aLi(this,"FKX07485N6VROUOMUYEI40")
    }
    wx_pay_iv.setOnClickListener {
        MyDialog.showWXPay(this)
    }
}

fun Activity.setTextColor(data: Data){

    fun setColor(color:Int){
        h_tv.setColor(color)
        m_tv.setColor(color)
        s_tv.setColor(color)
        ymd_tv.setColor(color)
        ampm_tv.setColor(color)
        week_tv.setColor(color)
        colon_tv.setColor(color)
    }

    setColor(data.getTextColor())

    color_seek_bar.setOnColorChangeListener(object :ColorSeekBar.OnColorChangeListener{
        override fun onColorChangeListener(color: Int) {
            setColor(color)
            data.setTextColor(color)
        }
    })
}
// 字体大小
fun Activity.setTextSize(data: Data){
    changeLayout(data)

    when(data.getTextSize()){
        TextSizeSwitch.STATE.AUTO.toString() -> {
            textsize_switch.setChosen(TextSizeSwitch.BTN.AUTO_BTN)
        }
        TextSizeSwitch.STATE.BIG.toString() -> {
            textsize_switch.setChosen(TextSizeSwitch.BTN.BIG_BTN)
        }
        TextSizeSwitch.STATE.SMALL.toString() -> {
            textsize_switch.setChosen(TextSizeSwitch.BTN.SMALL_BTN)
        }
    }

    when(data.getLayout()){
        ChangeLayoutSwitch.STATE.A.toString() -> {
            changelayout_switch.setChosen(ChangeLayoutSwitch.BTN.A_BTN)
        }
        ChangeLayoutSwitch.STATE.B.toString() -> {
            changelayout_switch.setChosen(ChangeLayoutSwitch.BTN.B_BTN)
        }
    }

    textsize_switch.onChosen{
        data.setTextSize(it)
        changeLayout(data)
    }
    changelayout_switch.onChosen {
        data.setLayout(it)
        recreate()
    }
}

// 亮度
fun Activity.setBrightness(data: Data){
    val activity = this

    fun setBrightness() {
        MySensor(getSystemService(SENSOR_SERVICE) as SensorManager?).setOnLightChanged(object : MySensor.OnLightChanged {
            override fun onHeight() {
                if (data.isBrightness()) {
//                    h_tv.toW(); m_tv.toW(); s_tv.toW(); ymd_tv.toW(); colon_tv.toW(); ampm_tv.toW(); week_tv.toW()
                    changeAppBrightness(activity, -1)
                }
            }

            override fun onLow() {
                if (data.isBrightness()) {
//                    h_tv.toG(); m_tv.toG(); s_tv.toG(); ymd_tv.toG(); colon_tv.toG(); ampm_tv.toG(); week_tv.toG()
                    changeAppBrightness(activity, 5);
                }else{
//                    h_tv.toW(); m_tv.toW(); s_tv.toW(); ymd_tv.toW(); colon_tv.toW(); ampm_tv.toW(); week_tv.toW()
                    changeAppBrightness(activity, -1);
                }
            }
        })
    }

    setBrightness()
    brightness_chk.isChecked = data.isBrightness()
    brightness_chk.setOnCheckedChangeListener { view, isChecked ->
        setBrightness()
        data.setBrightness(isChecked)
    }
}

fun Activity.setKeepScreenOn(data: Data){
    keepscreenon_chk.isChecked = data.isKeepScreenOn()
    keepscreenon_chk.setOnCheckedChangeListener { view, isChecked ->
        data.setKeepScreenO(isChecked)
        recreate()
    }
}

fun Activity.setBatteryProgress(data: Data){
    if (data.showBatteryProgress()){
        battery_progress.visibility = View.VISIBLE
        showbattery_progress_chk.isChecked = true
    }else{
        battery_progress.visibility = View.GONE
        showbattery_progress_chk.isChecked = false
    }

    showbattery_progress_chk.setOnCheckedChangeListener { view, isChecked ->
        data.setShowBatteryProgress(isChecked)
        if (isChecked){
            battery_progress.visibility = View.VISIBLE
        }else{
            battery_progress.visibility = View.GONE
        }
    }

}