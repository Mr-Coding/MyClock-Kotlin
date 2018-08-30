package com.frank.myclock.view;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.frank.myclock.Data;
import com.frank.myclock.R;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {
    private ValueAnimator colorAnim;
    private int color = Color.WHITE;
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int RED = 0xffFF8080;int BLUE = 0xff8080FF;int CYAN = 0xff80ffff;int GREEN = 0xff80ff80;

        colorAnim = ObjectAnimator.ofInt(this,"textColor",
                RED, BLUE,CYAN,GREEN, Color.YELLOW);
        colorAnim.setDuration(15000);
        colorAnim.setEvaluator(new ArgbEvaluator()); colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
    }

    public void toAutoSize(float size){
        setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
    }

    public void toBigSize(float size){
        setTextSize(TypedValue.COMPLEX_UNIT_PX,size*1.9f);
    }
    public void toSmallSize(float size){
        setTextSize(TypedValue.COMPLEX_UNIT_PX,size/1.6f);
    }


    public void setColor(String color){
        this.color = Color.parseColor(color);
        setTextColor(this.color);
    }

    public void setColor(int color){
        this.color = color;
        setTextColor(this.color);
    }

    public void setVisibility(boolean isGone){
        if (isGone) {
            setVisibility(View.VISIBLE);
        }else {
            setVisibility(View.GONE);
        }
    }

    public void setGradualChange(boolean GradualChange){
        if (GradualChange){
            colorAnim.start();
        }else {
            colorAnim.cancel();
            setTextColor(new Data(getContext()).getTextColor());
        }
        invalidate();
    }
}
