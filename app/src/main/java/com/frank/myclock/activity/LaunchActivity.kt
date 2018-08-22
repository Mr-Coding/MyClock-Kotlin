package com.frank.myclock.activity

import com.bumptech.glide.Glide
import com.frank.myclock.R

class LaunchActivity: BaseActivity() {

    override fun init() {

    }

    override fun onBackPressed() {}

    override fun setLayoutRes(): Int {
        return R.layout.activity_main
    }
    override fun setFullScreen(): Boolean {
        return true
    }
    override fun canOpenMenu(): Boolean {
        return true
    }

}