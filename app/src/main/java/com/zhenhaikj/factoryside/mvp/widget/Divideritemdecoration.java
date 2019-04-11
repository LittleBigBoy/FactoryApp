package com.zhenhaikj.factoryside.mvp.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;

import com.zhenhaikj.factoryside.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Divideritemdecoration extends RecyclerView.ItemDecoration {
        // 写右边字的画笔(具体信息)
        private Paint mpaint;

        // 写左边日期字的画笔( 时间 + 日期)
        private Paint mpaint1;
        private Paint mpaint2;
        private Paint mpaint3;


        // 左 上偏移长度
        private int itemview_leftinterval;
        private int itemview_topinterval;

        // 轴点半径
        private int circle_radius;

        // 图标
        private Bitmap micon;
        //月份合集(使用时需要设置)
        private List<String> monthlist= new ArrayList<>();
        //年份合集(使用时需要设置)
        private List<String> yearlist= new ArrayList<>();

        public void setmonthlist(List<String> monthlist) {
            this.monthlist = monthlist;
        }

        public void setyearlist(List<String> yearlist) {
            this.yearlist = yearlist;
        }

        // 在构造函数里进行绘制的初始化，如画笔属性设置等
        public Divideritemdecoration(Context context) {

            // 轴点画笔(红色)
            mpaint = new Paint();
            mpaint.setColor(Color.rgb(58, 154, 239));
            mpaint.setStyle(Paint.Style.STROKE);
            mpaint.setStrokeWidth(3);
            // 左边时间文本画笔(蓝色)
            // 此处设置了两只分别设置 时分 & 年月
            mpaint1 = new Paint();
            mpaint1.setColor(Color.BLACK);
            mpaint1.setTextSize(30);


            mpaint2 = new Paint();
            mpaint2.setColor(context.getResources().getColor(R.color.black));
            mpaint2.setTextSize(26);

            mpaint3 = new Paint();
            mpaint3.setColor(Color.rgb(58, 154, 239));
            mpaint3.setTextSize(20);

            // 赋值itemview的左偏移长
            itemview_leftinterval = 150;

            // 赋值itemview的上偏移长度
            itemview_topinterval = 30;

            // 赋值轴点圆的半径为10
            circle_radius = 8;



        }




        // 重写getitemoffsets（）方法
        // 作用：设置itemview 左 & 上偏移长度
        @Override
        public void getItemOffsets(Rect outrect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outrect, view, parent, state);

            // 设置itemview的左 & 上偏移长度分别为150 px & 30px,即此为ondraw()可绘制的区域
            outrect.set(itemview_leftinterval, itemview_topinterval, 0, 0);

        }

        // 重写ondraw（）
        // 作用:在间隔区域里绘制时光轴线 & 时间文本
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            // 获取recyclerview的child view的个数
            int childcount = parent.getChildCount();

            // 遍历每个item，分别获取它们的位置信息，然后再绘制对应的分割线
            for (int i = 0; i < childcount; i++) {

                // 获取每个item对象
                View child = parent.getChildAt(i);
                View lastchild = null;
                if (i > 0) {

                    lastchild = parent.getChildAt(i - 1);
                }

                /**
                 * 绘制轴点
                 */
                // 轴点 = 圆 = 圆心(x,y) 位置可以根据需求来调节
                float centerx = child.getLeft() - itemview_leftinterval / 4;
                float centery = child.getTop() + itemview_topinterval +10;
                // 绘制轴点圆
                c.drawCircle(centerx, centery, circle_radius, mpaint);


                /**
                 * 绘制上半轴线(x轴是保持不变的)
                 */
                // 上端点坐标(x,y)
                float upline_up_x = centerx;
                float upline_up_y = 0;
                if (i>0){
                    upline_up_y = lastchild.getBottom();
                }else {
                    upline_up_y = centery - itemview_topinterval;
                }



                // 下端点坐标(x,y)
                float upline_bottom_x = centerx;
                float upline_bottom_y = centery - circle_radius;

                //绘制上半部轴线
                c.drawLine(upline_up_x, upline_up_y, upline_bottom_x, upline_bottom_y, mpaint);

                /**
                 * 绘制下半轴线,最后一个不画下半轴
                 */
                if (i <childcount-1){
                    // 上端点坐标(x,y)
                    float bottomline_up_x = centerx;
                    float bottom_up_y = centery + circle_radius;

                    // 下端点坐标(x,y)
                    float bottomline_bottom_x = centerx;
                    float bottomline_bottom_y = child.getBottom();

                    //绘制下半部轴线
                    c.drawLine(bottomline_up_x, bottom_up_y, bottomline_bottom_x, bottomline_bottom_y, mpaint);
                }



                /**
                 * 绘制左边时间文本
                 */
                // 获取每个item的位置
                int index = parent.getChildAdapterPosition(child);
                // 设置文本起始坐标
                float text_x = child.getLeft() - itemview_leftinterval * 5 / 6;
                float text_y = upline_bottom_y;

                // 根据item位置设置时间文本
                switch (index) {
                    case (0):
                        // 设置日期绘制位置

                        c.drawText(monthlist.get(0), text_x, text_y, mpaint1);
                        c.drawText(yearlist.get(0), text_x + 8, text_y + 28, mpaint2);
                        break;
                    case (1):
                        // 设置日期绘制位置
                        c.drawText(monthlist.get(1), text_x, text_y, mpaint1);
                        c.drawText(yearlist.get(1), text_x + 8, text_y + 28, mpaint2);
                        break;
                    case (2):
                        // 设置日期绘制位置
                        if (TextUtils.isEmpty(yearlist.get(2))){
                            c.drawText(monthlist.get(2), text_x, text_y, mpaint3);
                        }else {
                            c.drawText(monthlist.get(2), text_x, text_y, mpaint1);
                            c.drawText(yearlist.get(2), text_x + 8, text_y + 28, mpaint2);
                        }
                        break;
                    case (3):
                        // 设置日期绘制位置
                        c.drawText(monthlist.get(3), text_x, text_y, mpaint1);
                        c.drawText(yearlist.get(3), text_x + 8, text_y + 28, mpaint2);
                        break;
                    case (4):
                        // 设置日期绘制位置
                        c.drawText(monthlist.get(4), text_x, text_y, mpaint1);
                        c.drawText(yearlist.get(4), text_x + 8, text_y + 28, mpaint2);
                        break;
                    default:c.drawText("结束", text_x, text_y, mpaint1);
                }
            }
        }


}
