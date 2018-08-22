package com.frank.myclock.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {
    private OnUnlockListener listener;
    private float yStart,yEnd;
    public MyRelativeLayout(Context context) {
        super(context);
    }
    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("LockActivity","onTouchEvent");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                yStart = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                yEnd = event.getRawY();
                if (yStart > yEnd+400){
                    listener.OnUnlock();
                }
                break;
        }
        return true;
    }

    public void setOnUnlockListener(OnUnlockListener listener){
        this.listener = listener;
    }

    public interface OnUnlockListener{
        public void OnUnlock();
    }
}
