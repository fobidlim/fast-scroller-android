package com.fobid.fastscroller.ui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.fobid.fastscroller.R;

public class FastScrollerRecyclerView extends RecyclerView {

    private boolean mEnableFastScroller;

    public FastScrollerRecyclerView(Context context) {
        this(context, null);
    }

    public FastScrollerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastScrollerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FastScroller, defStyle, 0);

            mEnableFastScroller = a.getBoolean(R.styleable.FastScroller_fastScrollEnabled, false);
            if (mEnableFastScroller) {
                StateListDrawable verticalThumbDrawable = (StateListDrawable) a
                        .getDrawable(R.styleable.FastScroller_fastScrollVerticalThumbDrawable);
                Drawable verticalTrackDrawable = a
                        .getDrawable(R.styleable.FastScroller_fastScrollVerticalTrackDrawable);
                StateListDrawable horizontalThumbDrawable = (StateListDrawable) a
                        .getDrawable(R.styleable.FastScroller_fastScrollHorizontalThumbDrawable);
                Drawable horizontalTrackDrawable = a
                        .getDrawable(R.styleable.FastScroller_fastScrollHorizontalTrackDrawable);
                initFastScroller(verticalThumbDrawable, verticalTrackDrawable,
                        horizontalThumbDrawable, horizontalTrackDrawable);
            }
            a.recycle();
        }
    }

    @VisibleForTesting
    void initFastScroller(StateListDrawable verticalThumbDrawable,
                          Drawable verticalTrackDrawable, StateListDrawable horizontalThumbDrawable,
                          Drawable horizontalTrackDrawable) {
        if (verticalThumbDrawable == null || verticalTrackDrawable == null
                || horizontalThumbDrawable == null || horizontalTrackDrawable == null) {
            throw new IllegalArgumentException(
                    "Trying to set fast scroller without both required drawables.");
        }

        Resources resources = getContext().getResources();
        new FastScroller(this, verticalThumbDrawable, verticalTrackDrawable,
                horizontalThumbDrawable, horizontalTrackDrawable,
                resources.getDimensionPixelSize(R.dimen.fastscroll_default_thickness),
                resources.getDimensionPixelSize(R.dimen.fastscroll_minimum_range),
                resources.getDimensionPixelOffset(R.dimen.fastscroll_margin));
    }
}
