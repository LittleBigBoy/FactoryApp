package com.emjiayuan.nll.viewholder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.hedgehog.ratingbar.RatingBar;

import androidx.annotation.IdRes;

public class RatingBarViewHolder extends BaseViewHolder {
    private RatingBar ratingBar;
    public RatingBarViewHolder(View view) {
        super(view);
    }
    public BaseViewHolder setStars(@IdRes int viewId, float value) {
        RatingBar view = getView(viewId);
        view.setStar(value);
        return this;
    }
}
