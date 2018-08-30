package com.frank.myclock.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class TextSizeSwitch: LinearLayout, View.OnClickListener {
    private var autoBtnId:Int = 0
    private var bigBtnId:Int = 1
    private var smallBtnId:Int = 2
    private var bgColor:Int = Color.parseColor("#FF0D1A28")
    private var bgCheckedColor:Int = Color.WHITE
    private var textColor:Int = Color.WHITE
    private var textCheckedColor:Int = Color.BLACK
    private lateinit var autoTv: TextView
    private lateinit var smallTv: TextView
    private lateinit var bigTv: TextView
    private var stateIs: ((String) -> Unit)? = null

    enum class BTN{
        AUTO_BTN,BIG_BTN,SMALL_BTN
    }
    enum class STATE{
        SMALL, BIG, AUTO
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
        autoTv.text = "自动"
        autoTv.setTextColor(textColor)
        autoTv.layoutParams = params
        autoTv.gravity = Gravity.CENTER
        autoTv.setOnClickListener(this)
        autoTv.id = autoBtnId

        bigTv = TextView(context)
        bigTv.text = "大"
        bigTv.setTextColor(textColor)
        bigTv.layoutParams = params
        bigTv.gravity = Gravity.CENTER
        bigTv.setOnClickListener(this)
        bigTv.id = bigBtnId

        smallTv = TextView(context)
        smallTv.text = "小"
        smallTv.setTextColor(textColor)
        smallTv.layoutParams = params
        smallTv.gravity = Gravity.CENTER
        smallTv.setOnClickListener(this)
        smallTv.id = smallBtnId

        addView(autoTv)
        addView(bigTv)
        addView(smallTv)
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
            bigBtnId-> {
                changeColors(bigTv)
                state = STATE.BIG.toString()
            }
            smallBtnId-> {
                changeColors(smallTv)
                state = STATE.SMALL.toString()
            }
        }
        stateIs?.invoke(state)
    }

    fun onChosen(stateIs:(String)->Unit){
        this.stateIs = stateIs
    }

    fun setChosen(btn:BTN){
        when(btn){
            BTN.AUTO_BTN ->{ changeColors(autoTv) }
            BTN.SMALL_BTN ->{ changeColors(smallTv) }
            BTN.BIG_BTN->{ changeColors(bigTv) }
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
        smallTv.setTextColor(textColor)
        smallTv.setBackgroundColor(bgColor)
    }
    private fun restore3(){
        bigTv.setTextColor(textColor)
        bigTv.setBackgroundColor(bgColor)
    }

}