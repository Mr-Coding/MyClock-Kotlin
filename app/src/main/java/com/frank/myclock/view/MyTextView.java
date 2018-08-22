package com.frank.myclock.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MyTextView extends TextView {
    private ValueAnimator colorAnim;
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int RED = 0xffFF8080;int BLUE = 0xff8080FF;int CYAN = 0xff80ffff;int GREEN = 0xff80ff80;

        colorAnim = ObjectAnimator.ofInt(this,"textColor",
                RED, BLUE,CYAN,GREEN, Color.YELLOW);
        colorAnim.setDuration(15000);
        colorAnim.setEvaluator(new ArgbEvaluator()); colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);

    }


    public void setVisibility(boolean isGone){
        if (isGone) {
            setVisibility(View.VISIBLE);
        }else {
            setVisibility(View.GONE);
        }
    }

    public void setGradualChange(boolean isCloseGradualChange){
        if (isCloseGradualChange){
            colorAnim.start();
        }else {
            colorAnim.cancel();
            setTextColor(Color.WHITE);
        }
        invalidate();
    }
}
