package cn.blueballoon.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Created by twiceYuan on 2017/11/7.
 * <p>
 * 纵向广告轮播
 */
public class MarqueeView extends ViewFlipper {

    private int interval     = 3000;
    private int animDuration = 1000;

    private OnItemClickListener onItemClickListener;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MarqueeView);

        int temp1 = attributes.getInt(R.styleable.MarqueeView_marqueeView_animDuration, -1);
        if (temp1 != -1) {
            animDuration = temp1;
        }
        int temp2 = attributes.getInt(R.styleable.MarqueeView_marqueeView_intervalTime, -1);
        if (temp2 != -1) {
            interval = temp2;
        }

        attributes.recycle();
        refreshLayout();
    }

    @SuppressWarnings("unused")
    public void setInterval(int interval) {
        this.interval = interval;
        refreshLayout();
    }

    @SuppressWarnings("unused")
    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
        refreshLayout();
    }

    private void refreshLayout() {

        Context context = getContext();
        Animation animIn = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_in);
        Animation animOut = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_out);

        animIn.setDuration(animDuration);
        animOut.setDuration(animDuration);

        setInAnimation(animIn);
        setOutAnimation(animOut);

        setFlipInterval(interval + animDuration);
    }

    public void setViews(final List<View> views) {

        removeAllViews();

        if (views == null || views.size() == 0) return;

        for (int i = 0; i < views.size(); i++) {
            final int position = i;

            views.get(i).setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, views.get(position));
                }
            });

            addView(views.get(i));
        }
        startFlipping();
    }

    @SuppressWarnings("unused")
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }
}
