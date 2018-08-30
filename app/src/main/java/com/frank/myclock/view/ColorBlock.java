package com.frank.myclock.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.frank.myclock.R;

public class ColorBlock extends View {
    private int bgColor = Color.GRAY;
    Paint paint;
    RectF r1; //RectF对象  

    public ColorBlock(Context context) {    super(context); init(); }

    public ColorBlock(Context context, AttributeSet attrs) {  super(context, attrs); init(); initAttrs(attrs); }

    public void initAttrs(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.ColorBlock);
        bgColor = typedArray.getColor(R.styleable.ColorBlock_bgColor,bgColor);
        typedArray.recycle();
    }

    private void init(){
        paint = new Paint();
        r1    = new RectF(); //RectF对象  
    }

    public void setBgColor(String bgColor){
        this.bgColor = Color.parseColor(bgColor);
//        requestLayout();
        invalidate();
    }

    public void setBgColor(int bgColor){
        this.bgColor = bgColor;
//        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(bgColor);

        r1.left=8;   //左边  
        r1.top=8;   //上边  
        r1.right=getWidth()-8; //右边
        r1.bottom=getHeight()-8;//下边
        canvas.drawRoundRect(r1,2,2,paint);

    }
}
