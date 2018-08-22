package com.frank.myclock.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.frank.myclock.view.MySwitch.BTN.*

class MySwitch: LinearLayout, View.OnClickListener {
    private var autoBtnId:Int = 0
    private var LanBtnId:Int = 1
    private var PorBtnId:Int = 2
    private var bgColor:Int = Color.parseColor("#FF0D1A28")
    private var bgCheckedColor:Int = Color.WHITE
    private var textColor:Int = Color.WHITE
    private var textCheckedColor:Int = Color.BLACK
    private lateinit var autoTv: TextView
    private lateinit var lanTv: TextView
    private lateinit var porTv: TextView
    private var stateIs: ((String) -> Unit)? = null

    enum class BTN{
        AUTO_BTN,PORTRAIT_BTN,LANDSCAPE_BTN
    }
    enum class STATE{
        PORTRAIT, LANDSCAPE, AUTO
    }

    constructor(context: Context?) : super(context) {init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){init()}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){init()}

    fun init(){
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        params.weight = 1.0f//在此处设置weight

        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        setBackgroundColor(bgColor)

        autoTv = TextView(context)
        autoTv.setText("自动")
        autoTv.setTextColor(textColor)
        autoTv.layoutParams = params
        autoTv.gravity = Gravity.CENTER
        autoTv.setOnClickListener(this)
        autoTv.id = autoBtnId

        porTv = TextView(context)
        porTv.setText("竖屏")
        porTv.setTextColor(textColor)
        porTv.layoutParams = params
        porTv.gravity = Gravity.CENTER
        porTv.setOnClickListener(this)
        porTv.id = PorBtnId

        lanTv = TextView(context)
        lanTv.setText("横屏")
        lanTv.setTextColor(textColor)
        lanTv.layoutParams = params
        lanTv.gravity = Gravity.CENTER
        lanTv.setOnClickListener(this)
        lanTv.id = LanBtnId

        addView(autoTv)
        addView(porTv)
        addView(lanTv)
    }

    override fun onClick(view: View?) {
        restore1()
        restore2()
        restore3()
        var state = ""
        when (view?.id){
            autoBtnId-> {
                changeColors(autoTv)
                state = STATE.AUTO.toString()
            }
            LanBtnId-> {
                changeColors(lanTv)
                state = STATE.LANDSCAPE.toString()
            }
            PorBtnId-> {
                changeColors(porTv)
                state = STATE.PORTRAIT.toString()
            }
        }
        stateIs?.invoke(state)
    }

    fun onChosen(stateIs:(String)->Unit){
        this.stateIs = stateIs
    }

    fun setChosen(btn:BTN){
        when(btn){
            AUTO_BTN ->{ changeColors(autoTv) }
            LANDSCAPE_BTN ->{ changeColors(lanTv) }
            PORTRAIT_BTN ->{ changeColors(porTv) }
        }
    }

    private fun changeColors(textView: TextView?){
        textView?.setTextColor(textCheckedColor)
        textView?.setBackgroundColor(bgCheckedColor)
    }

    private fun restore1(){
        autoTv.setTextColor(textColor)
        autoTv.setBackgroundColor(bgColor)
    }
    private fun restore2(){
        lanTv.setTextColor(textColor)
        lanTv.setBackgroundColor(bgColor)
    }
    private fun restore3(){
        porTv.setTextColor(textColor)
        porTv.setBackgroundColor(bgColor)
    }

}