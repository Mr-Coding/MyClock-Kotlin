package com.frank.myclock.extend

import android.app.Activity
import com.frank.myclock.Data
import com.frank.myclock.time.Time
import com.frank.myclock.view.MySwitch
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.panel_settings.*
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.frank.myclock.MyService
import com.frank.myclock.R
import com.frank.myclock.activity.LockActivity
import kotlinx.android.synthetic.main.activity_lcok.*
import kotlinx.android.synthetic.main.panel_style_settings.*


fun Activity.setHour(data: Data) {
    h_tv.text = if (!data.is12HourClock()){
        Time.hour()
    }else{
        Time.hourOfDay()
    }
    hourofday_chk.isChecked = data.is12HourClock()
    hourofday_chk.setOnCheckedChangeListener {
        view, isChecked ->
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
    colon_tv.setVisibility(data.isShowMinute())
    showminute_btn.isChecked = data.isShowMinute()
    showminute_btn.setOnCheckedChangeListener {
        view, isChecked ->
        data.setShowMinute(isChecked)
        m_tv.setVisibility(isChecked)
        colon_tv.setVisibility(data.isShowMinute())
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
    showymd_chk.setOnCheckedChangeListener {
        view, isChecked ->
        data.setShowYMD(isChecked)
        ymd_tv.setVisibility(isChecked)
    }

    showlunar_chk.isChecked = data.isShowLunar()
    showlunar_chk.setOnCheckedChangeListener {
        view, isChecked ->
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
fun Activity.setGradualChange(data: Data){
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
    addbg_btn.setOnClickListener {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent,1)
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