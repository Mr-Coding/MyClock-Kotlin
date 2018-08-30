package com.frank.myclock.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import com.frank.myclock.Data
import com.frank.myclock.extend.setActivityFullScreen
import com.frank.myclock.extend.timeRunning
import android.widget.Toast
import com.frank.myclock.extend.showWelcome
import com.frank.myclock.util.APKVersionCodeUtils
import kotlinx.android.synthetic.main.panel_settings.*

abstract class BaseActivity : Activity(){
    private val data:Data by lazy { Data(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isKeepScreenOn()){
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }

        setContentView(setLayoutRes())

        init()

        showWelcome(data)

        version_name.text = "版本  V "+APKVersionCodeUtils.getVersionName(this)
    }


    override fun onResume() {
        super.onResume()
        setActivityFullScreen(setFullScreen())
        timeRunning()
    }

    override fun onRestart() {
        super.onRestart()
        setActivityFullScreen(setFullScreen())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, 1)
        }
    }

    protected abstract fun isKeepScreenOn():Boolean

    protected abstract fun setLayoutRes():Int

    protected abstract fun setFullScreen():Boolean

    protected abstract fun canOpenMenu():Boolean

    protected abstract fun init()

}
