package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class VgGalleryView extends RelativeLayout{
    VgInerViewPager inerViewPager;
    boolean isStop=true;//用于中断轮播图线程
    int interval=0;

    public VgGalleryView(Context context) {
        super(context);
        initView();
    }

    public VgGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VgGalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setNoScroll(boolean noScroll) {
        inerViewPager.setNoScroll(noScroll);
    }

    private void initView(){
        inerViewPager=new VgInerViewPager(getContext());
        LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        inerViewPager.setLayoutParams(params);
        addView(inerViewPager);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setDatas(List<View> list){
        if(list==null||list.isEmpty()){
            return;
        }
        inerViewPager.setDatas(list);
     }

    public void setCurrentItem(int index){
        inerViewPager.changedCurrentItem(index);
    }
    class VgInerViewPager extends ViewPager {
        private int mCurrentPage=0;
        private  boolean noScroll=false;
        AdvAdapter adapter=null;
        public VgInerViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
//        addAnimation();
        }

        public VgInerViewPager(Context context) {
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
            adapter = new AdvAdapter(list);
            this.setAdapter(adapter);
            this.addOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mCurrentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            play();//循环播放

        }

        private void play(){
            if(adapter==null){
                return;
            }
            new Thread(){
                @Override
                public void run() {
                    isStop=false;
                    while(!isStop){
                        LogUtil.log_e("isStop="+isStop);
                        if(((BaseActivity)(VgGalleryView.this.getContext())).isFinishing()){//当Activity退出并被finish掉时；停止轮播图线程
                            isStop=true;
                            break;
                        }
                        if(adapter.getCount()<=1){//当只有一个item时,不运行循环
                            isStop=true;
                            break;
                        }

                        if(interval<1){//小于1毫秒,毫无意义,所以停止循环;避免这种死循环占用系统资源
                            isStop=true;
                            break;
                        }
                        refreshViewPager();
                        try {
                            sleep(interval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }.start();
        }
        private void refreshViewPager(){
            VgInerViewPager.this.post(new Runnable() {
                @Override
                public void run() {
                    mCurrentPage++;
                    if (mCurrentPage >= adapter.getCount()) {
                        mCurrentPage = 0;
                    }
                    changedCurrentItem(mCurrentPage % adapter.getCount());
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


    }
    public  class AdvAdapter extends PagerAdapter {
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
