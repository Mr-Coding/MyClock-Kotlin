package com.frank.myclock.activity

import android.util.Log
import android.view.WindowManager
import com.frank.myclock.R
import kotlinx.android.synthetic.main.activity_lcok.*

class LockActivity: BaseActivity() {
    override fun init() {
//        swipe_layout.setOnUnlockListener {
//            Log.i("LockActivity","setOnUnlockListener");
//            finish()
//        }
    }

    override fun setLayoutRes(): Int {
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
        return R.layout.activity_lcok
    }
    override fun setFullScreen(): Boolean {
        return true
    }
    override fun canOpenMenu(): Boolean {
        return false
    }

}