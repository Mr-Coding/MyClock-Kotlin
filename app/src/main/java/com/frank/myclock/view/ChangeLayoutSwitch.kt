package com.frank.myclock.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class ChangeLayoutSwitch: LinearLayout, View.OnClickListener {
    private var btnIdA:Int = 0
    private var btnIdB:Int = 1

    private var bgColor:Int = Color.parseColor("#FF0D1A28")
    private var bgCheckedColor:Int = Color.WHITE
    private var textColor:Int = Color.WHITE
    private var textCheckedColor:Int = Color.BLACK

    private lateinit var ATv: TextView
    private lateinit var BTv: TextView

    private var stateIs: ((String) -> Unit)? = null

    enum class BTN{
        A_BTN,B_BTN
    }
    enum class STATE{
        A, B
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

        ATv = TextView(context)
        ATv.text = "布局 1"
        ATv.setTextColor(textColor)
        ATv.layoutParams = params
        ATv.gravity = Gravity.CENTER
        ATv.setOnClickListener(this)
        ATv.id = btnIdA

        BTv = TextView(context)
        BTv.text = "布局 2"
        BTv.setTextColor(textColor)
        BTv.layoutParams = params
        BTv.gravity = Gravity.CENTER
        BTv.setOnClickListener(this)
        BTv.id = btnIdB

        addView(ATv)
        addView(BTv)
    }

    override fun onClick(view: View?) {
        restore1()
        restore2()
        var state = ""
        when (view?.id){
            btnIdA-> {
                changeColors(ATv)
                state = STATE.A.toString()
            }
            btnIdB-> {
                changeColors(BTv)
                state = STATE.B.toString()
            }
        }
        stateIs?.invoke(state)
    }

    fun onChosen(stateIs:(String)->Unit){
        this.stateIs = stateIs
    }

    fun setChosen(btn:BTN){
        when(btn){
            BTN.A_BTN ->{ changeColors(ATv) }
            BTN.B_BTN ->{ changeColors(BTv) }
        }
    }

    private fun changeColors(textView: TextView?){
        textView?.setTextColor(textCheckedColor)
        textView?.setBackgroundColor(bgCheckedColor)
    }

    private fun restore1(){
        ATv.setTextColor(textColor)
        ATv.setBackgroundColor(bgColor)
    }
    private fun restore2(){
        BTv.setTextColor(textColor)
        BTv.setBackgroundColor(bgColor)
    }

}