package com.frank.myclock.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.frank.myclock.R
import android.app.Activity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.frank.myclock.extend.setActivityFullScreen

class MyDialog{

    companion object {
        private var dialog:AlertDialog? = null

        fun dialogBuilder(context: Context,resource:Int,cancelable:Boolean):View{
            val dialogBuilder = AlertDialog.Builder(context)
            val parent = LayoutInflater.from(context).inflate(resource,null,false)
            dialogBuilder.setView(parent)
            dialogBuilder.setCancelable(cancelable)
            dialogBuilder.create()
            dialog = dialogBuilder.show()
            return parent
        }

        fun showWelcome(context: Activity){
            val parent = dialogBuilder(context, R.layout.dialog_info, true)
            parent.findViewById<TextView>(R.id.dialog_ok_btn).setOnClickListener {
                dialog?.dismiss()
            }
        }


        fun showPermission(context: Activity,onOKListener:() -> Unit) {
            val parent = dialogBuilder(context, R.layout.dialog_permission, true)
            parent.findViewById<TextView>(R.id.dialog_ok_btn).setOnClickListener {
                dialog?.dismiss()
                onOKListener()
            }
            parent.findViewById<TextView>(R.id.dialog_cansel_btn).setOnClickListener { dialog?.dismiss() }
        }

        fun showWXPay(context: Activity) {
            dialogBuilder(context, R.layout.dialog_wxpay, true)
            dialog?.setOnDismissListener {
                context.setActivityFullScreen(true)
            }
        }

        fun showTimePicker(context: Activity,callback:((number:String)->Unit)? = null) {
            val parent = dialogBuilder(context, R.layout.dialog_numberpicker, true)
            val inputTime = parent.findViewById<EditText>(R.id.dialog_input_time)
            parent.findViewById<Button>(R.id.dialog_ok_btn).setOnClickListener {
                var number = inputTime.text.toString()
                if (number == "" || number == "0"){
                    number = "1"
                }
                callback?.invoke(number)
                dialog?.dismiss()
            }
            dialog?.setOnDismissListener {
                context.setActivityFullScreen(true)
            }
        }

    }

}