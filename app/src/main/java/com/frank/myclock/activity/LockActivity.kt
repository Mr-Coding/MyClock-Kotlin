package com.frank.myclock.activity

import android.util.Log
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.frank.myclock.Data
import com.frank.myclock.R
import com.frank.myclock.extend.anyInit
import kotlinx.android.synthetic.main.activity_lcok.*

class LockActivity: BaseActivity() {
    private val data: Data by lazy { Data(this) }

    override fun init() {
        anyInit(data)
//        Glide.with(this).load(data.getImg()).into(findViewById(R.id.bg))
        swipe_layout.setOnUnlockListener {
            Log.i("LockActivity","Unlock");
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        anyInit(data)
        Glide.with(this).load(data.getImg()).into(findViewById(R.id.bg))
    }

    override fun isKeepScreenOn(): Boolean {
        return false
    }
    override fun setLayoutRes(): String {
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        return data.getLayout()
    }
    override fun setFullScreen(): Boolean {
        return true
    }
    override fun canOpenMenu(): Boolean {
        return false
    }

}