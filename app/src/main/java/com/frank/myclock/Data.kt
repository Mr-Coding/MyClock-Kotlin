package com.frank.myclock

import android.content.Context
import android.content.SharedPreferences
import com.frank.myclock.view.MySwitch

class Data(context: Context){
    private val data:SharedPreferences by lazy { context.getSharedPreferences("settings",Context.MODE_PRIVATE) }
    private val editor = data.edit()

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
    //是否显示分钟
    fun isShowMinute():Boolean{
        return data.getBoolean("ShowMinute",true)
    }
    fun setShowMinute(value:Boolean){
        editor.putBoolean("ShowMinute",value).commit()
    }
    //是否显示农历
    fun isShowLunar():Boolean{
        return data.getBoolean("ShowLunar",false)
    }
    fun setShowLunar(value:Boolean){
        editor.putBoolean("ShowLunar",value).commit()
    }
    //是否让文字移动
    fun isTextMove():Boolean{
        return data.getBoolean("TextMove",false)
    }
    fun setTextMove(value:Boolean){
        editor.putBoolean("TextMove",value).commit()
    }
    //是否开启文字颜色变换
    fun isGradualChange():Boolean{
        return data.getBoolean("GradualChange",false)
    }
    fun setGradualChange(value:Boolean){
        editor.putBoolean("GradualChange",value).commit()
    }
    //是否开启12小时制
    fun is12HourClock():Boolean{
        return data.getBoolean("12HourClock",false)
    }
    fun set12HourClock(value:Boolean){
        editor.putBoolean("12HourClock",value).commit()
    }
    //是否显示AM/PM
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
        return data.getBoolean("Brightness",false)
    }
    fun setBrightness(value:Boolean){
        editor.putBoolean("Brightness",value).commit()
    }
    //是否让冒号闪动
    fun isFlashingColon():Boolean{
        return data.getBoolean("FlashingColon",false)
    }
    fun setFlashingColon(value:Boolean){
        editor.putBoolean("FlashingColon",value).commit()
    }
    //设置背景
    fun getImg():String{
        return data.getString("Background","")
    }
    fun setImg(value:String){
        editor.putString("Background",value).commit()
    }
    //设置屏幕方向
    fun getOrientation():String{
        return data.getString("Orientation",MySwitch.STATE.AUTO.toString())
    }
    fun setOrientation(value:String){
        editor.putString("Orientation",value).commit()
    }
    //是否设置为桌面启动器
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