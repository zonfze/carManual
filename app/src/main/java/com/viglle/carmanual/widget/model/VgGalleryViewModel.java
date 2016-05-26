package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/25.
 */
public class VgGalleryViewModel extends BaseViewModel{
    private String noScroll="0";//是否允许手动左右滑动切换 0为默认允许滑动切换;1为不允许滑动切换
    public static final String NOSCROLL="noScroll";



    private String interval;//轮播间隔时间；单位是毫秒
    public static final String INTERVAL="interval";

    public boolean noNoScroll() {
        if(noScroll==null||noScroll.equals("")||noScroll.equalsIgnoreCase("null")||noScroll.equals("0")){
            return false;
        }else{
            return true;
        }
    }

    public void setNoScroll(String noScroll) {
        this.noScroll = noScroll;
    }

    public int getInterval() {
        if(interval==null||interval.equals("")||interval.equals("null")){
            new Exception("interval 不能为空"+interval);
            return 5000;
        }
        if(!AppUtil.isNumeric(interval)){
            new Exception("interval 不能为非数字字符"+interval);
            return 5000;
        }
        return Integer.parseInt(interval);
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
