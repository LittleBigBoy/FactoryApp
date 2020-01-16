package com.zhenhaikj.factoryside.mvp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class MyProgress extends ProgressBar {
    String text;
    Paint mPaint;

    public MyProgress(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        System.out.println("1");
        initText();
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        System.out.println("2");
        initText();
    }


    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        System.out.println("3");
        initText();
    }

    @Override
    public synchronized void setProgress(int progress) {
        // TODO Auto-generated method stub
        Double a=Double.valueOf(progress);
        setText(a);
        super.setProgress(progress);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
//        this.setText();
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(this.text, x, y, this.mPaint);

    }

    //初始化，画笔
    private void initText(){
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.WHITE);
        this.mPaint.setTextSize(40);

    }

    private void setText(){
//        setText(this.getProgress());
    }

    //设置文字内容
    private void setText(Double progress){
        Double i = (progress /this.getMax())*100;
//        Double a= Double.parseDouble(String.format("%.2f", i))*100;
        this.text = String.format("%.0f", i) + "%";
    }


}
