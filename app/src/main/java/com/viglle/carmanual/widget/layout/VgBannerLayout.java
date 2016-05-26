package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * 轮播控件
 * 用于循环展示一组界面(轮播图等)
 */
public class VgBannerLayout extends ViewPager{
    private int mCurrentPage=0;
    private  boolean noScroll=false;

    public VgBannerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        addAnimation();
    }

    public VgBannerLayout(Context context) {
        super(context);
//        addAnimation();
    }

    private void addAnimation() {
        if(Build.VERSION.SDK_INT>=11){
            setPageTransformer(true,new DepthPageTransformer());//添加ViewPager切换动画
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {

        if (noScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    public void setDatas(List<View> list){////
        if(list==null||list.isEmpty()){
            return;
        }
        AdvAdapter adapter = new AdvAdapter(list);
        this.setAdapter(adapter);
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPage=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void changedCurrentItem(int index){
        if(getAdapter()!=null){
            int count= getAdapter().getCount();
            if(index>count||index<0){
                return;
            }
            this.setCurrentItem(index);
        }
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public int getmCurrentPage() {
        return mCurrentPage;
    }

    public void setmCurrentPage(int mCurrentPage) {
        this.mCurrentPage = mCurrentPage;
    }

    public static  class AdvAdapter extends PagerAdapter {
        private List<View> views = null;

        public AdvAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {

            if(arg1>=views.size()){
                arg1=arg1%views.size();
            }
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {

        }

        @Override
        public int getCount() {
            return views==null?0:views.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
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
}
