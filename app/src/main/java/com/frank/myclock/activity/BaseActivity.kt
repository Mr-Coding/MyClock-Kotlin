package com.frank.myclock.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.frank.myclock.Data
import com.frank.myclock.R
import com.frank.myclock.util.UriToFile
import com.frank.myclock.extend.setActivityFullScreen
import com.frank.myclock.extend.actionSettings
import com.frank.myclock.extend.timeRunning
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.panel_settings.*

abstract class BaseActivity : Activity(){
    private val data:Data by lazy { Data(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutRes())

        init()

        draw_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        if(canOpenMenu()){
            draw_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            main_layout.setOnLongClickListener {
                draw_layout.openDrawer(Gravity.LEFT)
                true
            }

            stylesetting_btn.setOnClickListener{
                draw_layout.closeDrawer(Gravity.LEFT)
                draw_layout.openDrawer(Gravity.RIGHT)
            }
        }

        actionSettings(data)

    }


    override fun onResume() {
        super.onResume()

        setActivityFullScreen(setFullScreen())

        timeRunning()

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

    protected abstract fun setLayoutRes():Int

    protected abstract fun setFullScreen():Boolean

    protected abstract fun canOpenMenu():Boolean

    protected abstract fun init()

}
