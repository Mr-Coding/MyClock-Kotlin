package com.frank.myclock.activity

import android.app.Activity
import android.view.Gravity
import androidx.drawerlayout.widget.DrawerLayout
import com.frank.myclock.Data
import com.frank.myclock.R
import com.frank.myclock.extend.setListener
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.panel_settings.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_MENU
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.frank.myclock.util.UriToFile


class LaunchActivity: BaseActivity() {
    private val data: Data by lazy { Data(this) }

    override fun init() {
        setListener(data)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 1){
                Glide.with(this).load(data?.data).into(findViewById(R.id.bg))
                Glide.with(this).load(data?.data).into(findViewById(R.id.showbg_iv))

                val uri: Uri? = data?.data
                val filePath = UriToFile.getPath(this,uri!!)
                Log.i("BaseActivity","Uri: ${filePath}")
                this.data.setImg(filePath)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            draw_layout.openDrawer(Gravity.LEFT)
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {}

    override fun isKeepScreenOn(): Boolean {
        return data.isKeepScreenOn()
    }
    override fun setLayoutRes(): String {
        return data.getLayout()
    }
    override fun setFullScreen(): Boolean {
        return true
    }
    override fun canOpenMenu(): Boolean {
        return true
    }

}