package com.frank.myclock.time

import android.util.Log
import com.frank.myclock.Data
import com.frank.myclock.Data.Companion.count

object TimeThread : Thread() {
    //    private val timeThread: TimeThread? = null
    private var timeChange: SecondChange? = null
    @Volatile
    var isRunning = true
    @Volatile
//    private var count = 0
    var on1s:(() ->Unit)?=null
    var onMoveTime:(() ->Unit)?=null

    override fun run() {
        super.run()
        while (isRunning){
            Thread.sleep(1000)
            if (isRunning){
                on1s?.invoke()
                if (count == Data.moveTime.toInt()){
                    count = 0
                    onMoveTime?.invoke()
                }
            }

            count ++
        }
    }

    fun setTimeListener(on1s:() ->Unit, onMoveTime:() ->Unit) {
        this.on1s = on1s
        this.onMoveTime = onMoveTime
    }

    interface SecondChange {
        fun on1s()
        fun onMoveTime()
    }
}