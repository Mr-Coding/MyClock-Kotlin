package com.frank.myclock.daydream

import android.annotation.TargetApi
import android.content.res.Configuration
import android.os.Build
import android.service.dreams.DreamService
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.frank.myclock.R
import com.frank.myclock.time.Time
import com.frank.myclock.view.MoveLayout
import com.frank.myclock.view.MyTextView

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
class DayDream:DreamService(){
    private val h_tv by lazy { findViewById<MyTextView>(R.id.h_tv) }
    private val m_tv by lazy { findViewById<MyTextView>(R.id.m_tv) }
    private val halfday_tv by lazy { findViewById<MyTextView>(R.id.ampm_tv) }
    private val week_tv by lazy { findViewById<MyTextView>(R.id.week_tv) }
    private val s_tv by lazy { findViewById<MyTextView>(R.id.s_tv) }
    private val bg by lazy { findViewById<ImageView>(R.id.bg) }
    private val moveLayout by lazy { findViewById<MoveLayout>(R.id.move_layout) }
    private val time by lazy { Time() }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isFullscreen = true
        isInteractive = false;
        setContentView(R.layout.activity_main)

        h_tv.setText(Time.hour())
        m_tv.setText(Time.minute())
        halfday_tv.setText(Time.halfDay())
        week_tv.setText(Time.week())
        s_tv.setText(Time.second())

        Glide.with(applicationContext).load(R.drawable.ic_clock).into(bg!!)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Toast.makeText(applicationContext,"New Config.",Toast.LENGTH_SHORT).show()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onDreamingStarted() {
        super.onDreamingStarted()
//        time.running(
//                on1s = {
//                    h_tv.setText(Time.hour())
//                    m_tv.setText(Time.minute())
//                    halfday_tv.setText(Time.halfDay())
//                    week_tv.setText(Time.week())
//                    s_tv.setText(Time.second())
//                },
//                on30s = {
//                    moveLayout.startMove()
//                }
//        )
    }

    override fun onDreamingStopped() {
        super.onDreamingStopped()
    }

}