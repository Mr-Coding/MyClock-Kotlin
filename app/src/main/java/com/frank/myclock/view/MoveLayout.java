package com.frank.myclock.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MoveLayout extends RelativeLayout {
    private LayoutParams params;
    private int index = 1;

    public MoveLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startMove(boolean isStart){
        if (!isStart)
            return;
        if (index == 6){ index = 1; }
        loop(index);
        Log.i("MoveLayout",""+index);
        index++;
    }

    public void startMove(){
        if (index == 6){ index = 1; }
        loop(index);
        Log.i("MoveLayout",""+index);
        index++;
    }

    public void restore(){
        index = 1;
        center();
    }

    private void left_top(){
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        this.setLayoutParams(params);
    }

    private void left_bottom(){
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        this.setLayoutParams(params);
    }

    private void center(){
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.setLayoutParams(params);
    }

    private void right_top(){
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        this.setLayoutParams(params);
    }

    private void right_bottom(){
        params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        this.setLayoutParams(params);
    }

    private void loop(int index){
        switch (index){
            case 1:
                left_top();
                break;
            case 2:
                left_bottom();
                break;
            case 3:
                center();
                break;
            case 4:
                right_top();
                break;
            case 5:
                right_bottom();
                break;
        }
    }
}
