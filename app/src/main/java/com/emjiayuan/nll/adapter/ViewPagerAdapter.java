package com.emjiayuan.nll.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.activity.LoginActivity;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends PagerAdapter {


    private List<View> views;
    private Activity activity;


    private static final String SHAREDPREFERENCES_NAME = "first_pref";


    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }


    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }


    @Override
    public void finishUpdate(View arg0) {
    }


    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }


    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        if (arg1 == views.size() - 1) {
            Button mStartWeiboImageButton = (Button) arg0
                    .findViewById(R.id.start_btn);
            mStartWeiboImageButton.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    setGuided();
                    goHome();
                }


            });
        }
        return views.get(arg1);
    }


    private void goHome() {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }


    private void setGuided() {
        SharedPreferences preferences = activity.getSharedPreferences(
                SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }


    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }


    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public void startUpdate(View arg0) {
    }
}