package com.frank.myclock.time

import android.os.Handler
import java.text.SimpleDateFormat
import java.util.*

class Time(){

    companion object{

        private var l:Long = 0;
        private var date:Date? = null;
        private var SDF:SimpleDateFormat = SimpleDateFormat.getDateInstance() as SimpleDateFormat;

        private fun init(){
            l = System.currentTimeMillis()
            date = Date(l)
        }

        //年月日
        fun YMD():String{
            init()
            SDF.applyPattern("yyyy年MM月dd日")
            return SDF.format(date);
        }

        //年月日农历
        fun YMDAsLunar():String{
            val today:Calendar = Calendar.getInstance()
            today.time = Lunar.chineseDateFormat.parse(YMD())
            return Lunar(today).toString()
        }

        //周
        fun week(): String {
            init()
            SDF.applyPattern("EEEE")
            return SDF.format(date)
        }

        //12小时制
        fun hourOfDay():String{
            init()
            SDF.applyPattern("HH")
            return SDF.format(date)
        }

        //24小时制
        fun hour():String{
            init()
            SDF.applyPattern("hh")
            return SDF.format(date)
        }

        //AM/PM
        fun halfDay():String{
            init()
            val h = Integer.parseInt(hourOfDay())
            if (h >= 13){
                return "PM"
            }
            return "AM"
        }

        //分钟
        fun minute():String{
            init()
            SDF.applyPattern("mm")
            return SDF.format(date)
        }

        //秒钟
        fun second():String{
            init()
            SDF.applyPattern("ss")
            return SDF.format(date)+"s"
        }

    }

}