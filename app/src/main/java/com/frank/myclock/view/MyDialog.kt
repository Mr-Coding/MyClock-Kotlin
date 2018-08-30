package com.frank.myclock.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.frank.myclock.R
import android.app.Activity
import android.widget.Button
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

    }

}