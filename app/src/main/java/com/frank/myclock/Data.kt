package com.frank.myclock

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import com.frank.myclock.view.ChangeLayoutSwitch
import com.frank.myclock.view.MySwitch
import com.frank.myclock.view.TextSizeSwitch

class Data(context: Context){
    private val data:SharedPreferences by lazy { context.getSharedPreferences("settings",Context.MODE_PRIVATE) }
    private val editor = data.edit()

    //是否让文字移动
    fun isStartMove():Boolean{
        return data.getBoolean("StartMove",true)
    }
    fun setStartMove(value:Boolean){
        editor.putBoolean("StartMove",value).commit()
    }
    //是否开启锁屏
    fun isLock():Boolean{
        return data.getBoolean("Lock",false)
    }
    fun setLock(value:Boolean){
        editor.putBoolean("Lock",value).commit()
    }
    //是否显示秒钟
    fun isShowSecond():Boolean{
        return data.getBoolean("ShowSecond",true)
    }
    fun setShowSecond(value:Boolean){
        editor.putBoolean("ShowSecond",value).commit()
    }
    //是否显示小时
    fun isShowHour():Boolean{
        return data.getBoolean("ShowHour",true)
    }
    fun setShowHour(value:Boolean){
        editor.putBoolean("ShowHour",value).commit()
    }
    //是否显示分钟
    fun isShowMinute():Boolean{
        return data.getBoolean("ShowMinute",true)
    }
    fun setShowMinute(value:Boolean){
        editor.putBoolean("ShowMinute",value).commit()
    }
    // 是否显示农历
    fun isShowLunar():Boolean{
        return data.getBoolean("ShowLunar",false)
    }
    fun setShowLunar(value:Boolean){
        editor.putBoolean("ShowLunar",value).commit()
    }
    // 是否开启文字颜色变换
    fun isGradualChange():Boolean{
        return data.getBoolean("GradualChange",false)
    }
    fun setGradualChange(value:Boolean){
        editor.putBoolean("GradualChange",value).commit()
    }
    // 文字颜色
    fun getTextColor():Int{
        return data.getInt("TextColor", Color.WHITE)
    }
    fun setTextColor(value:Int){
        editor.putInt("TextColor",value).commit()
    }
    // 是否开启12小时制
    fun is12HourClock():Boolean{
        return data.getBoolean("12HourClock",false)
    }
    fun set12HourClock(value:Boolean){
        editor.putBoolean("12HourClock",value).commit()
    }
    // 是否显示AM/PM
    fun isShowAMPM():Boolean{
        return data.getBoolean("ShowAMPM",true)
    }
    fun setShowAMPM(value:Boolean){
        editor.putBoolean("ShowAMPM",value).commit()
    }
    //是否显示周
    fun isShowWeek():Boolean{
        return data.getBoolean("ShowWeek",true)
    }
    fun setShowWeek(value:Boolean){
        editor.putBoolean("ShowWeek",value).commit()
    }
    //是否显示年月日
    fun isShowYMD():Boolean{
        return data.getBoolean("ShowYMD",true)
    }
    fun setShowYMD(value:Boolean){
        editor.putBoolean("ShowYMD",value).commit()
    }
    //是否开启自动调节亮度
    fun isBrightness():Boolean{
        return data.getBoolean("setBrightness",false)
    }
    fun setBrightness(value:Boolean){
        editor.putBoolean("setBrightness",value).commit()
    }
    //是否让冒号闪动
    fun isFlashingColon():Boolean{
        return data.getBoolean("FlashingColon",false)
    }
    fun setFlashingColon(value:Boolean){
        editor.putBoolean("FlashingColon",value).commit()
    }
    // 设置背景
    fun getImg():String{
        return data.getString("Background","")
    }
    fun setImg(value:String){
        editor.putString("Background",value).commit()
    }
    // 设置布局
    fun getLayout():String{
        return data.getString("Layout",ChangeLayoutSwitch.STATE.A.toString())
    }
    fun setLayout(value:String){
        editor.putString("Layout",value).commit()
    }
    // 设置屏幕方向
    fun getOrientation():String{
        return data.getString("Orientation",MySwitch.STATE.AUTO.toString())
    }
    fun setOrientation(value:String){
        editor.putString("Orientation",value).commit()
    }
    // 字体大小
    fun getTextSize():String{
        return data.getString("TextSize",TextSizeSwitch.STATE.AUTO.toString())
    }
    fun setTextSize(value:String){
        editor.putString("TextSize",value).commit()
    }
    // 是否不让屏幕息屏
    fun isKeepScreenOn():Boolean{
        return data.getBoolean("KeepScreenOn",false)
    }
    fun setKeepScreenO(value:Boolean){
        editor.putBoolean("KeepScreenOn",value).commit()
    }
    // 是否设置为桌面启动器
    fun isLaunch():Boolean{
        return data.getBoolean("Launch",false)
    }
    fun setLaunch(value:Boolean){
        editor.putBoolean("Launch",value).commit()
    }
    //设置App版本号
    fun getVersionCode():Int{
        return data.getInt("VersionCode",0)
    }
    fun setVersionCode(value:Int){
        editor.putInt("VersionCode",value).commit()
    }
}