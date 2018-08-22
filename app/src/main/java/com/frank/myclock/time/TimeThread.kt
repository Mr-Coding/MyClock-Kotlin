package com.frank.myclock.time

object TimeThread: Thread() {
//    private val timeThread: TimeThread? = null
    private var timeChange: SecondChange? = null
    @Volatile
    var isRunning = true
    @Volatile
    private var count = 1
    var on1s:(() ->Unit)?=null
    var on30s:(() ->Unit)?=null

    override fun run() {
        super.run()
        while (isRunning){
            Thread.sleep(1000)
            if (isRunning){
                on1s?.invoke()
                if (count == 30){
                    count = 1
                    on30s?.invoke()
                }
            }

            count ++
        }
    }

    fun setTimeListener(on1s:() ->Unit,on30s:() ->Unit) {
        this.on1s = on1s
        this.on30s = on30s
    }

    interface SecondChange {
        fun on1s()
        fun on30s()
    }
}