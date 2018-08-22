package com.frank.myclock.activity

import com.bumptech.glide.Glide
import com.frank.myclock.R

class MainActivity : BaseActivity() {

    override fun init() {

    }

    override fun setFullScreen(): Boolean {
        return true
    }
    override fun setLayoutRes(): Int {
        return R.layout.activity_main
    }
    override fun canOpenMenu(): Boolean {
        return true
    }

}
