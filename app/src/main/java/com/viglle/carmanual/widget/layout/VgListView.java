package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/6/2.
 */
public class VgListView extends ListView{
    public VgListView(Context context) {
        super(context);
    }

    public VgListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VgListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
