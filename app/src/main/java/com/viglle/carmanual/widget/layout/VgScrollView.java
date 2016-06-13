package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.viglle.carmanual.utils.LogUtil;

/**
 * Created by Administrator on 2016/6/2.
 */
public class VgScrollView extends ScrollView{
    public VgScrollView(Context context) {
        super(context);
    }

    public VgScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VgScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
//                isTopOrBottom();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(ev);
    }

    private void isTopOrBottom() {
        float scrollY=getScrollY();
        if(scrollY==0){
            LogUtil.log_e("top");
        }
        float height=getHeight();//scrollView的高度加上scrollView 当前滚动的Y坐标值;如果刚好等于子View的高度则说明ScrollView中的子View已经滚动到底部了。
        float childHeight=getChildAt(0).getMeasuredHeight();
        if((height+scrollY)==childHeight){
            LogUtil.log_e("bottom");
        }
    }


}
