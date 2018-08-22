package com.frank.myclock.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.RelativeLayout

class SwipeLayout: RelativeLayout{
    constructor(context: Context?):super(context){}
    constructor(context: Context?,attrs: AttributeSet?):super(context, attrs){}

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("SwipeLayout", "onTouchEvent")

        return true
    }

}