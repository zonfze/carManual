package com.viglle.carmanual.widget.model;

/**
 * Created by Administrator on 2016/6/12.
 */
public class VgWebViewModel extends BaseViewModel{
    private String url;
    public static final String URL="url";

    private String isback;//后退  0代表不可以后退;1代表可以后退
    public static final String BACK="isback";
//    private String forward="0";//前进 0代表不可以前进;1代表可以前进
//    public static final String FORWARD="forward";

    public boolean getBack() {
        if(isaNull(isback)){
            return false;
        }
        if(isback.equals("1")){
            return true;
        }else{
            return false;
        }
    }

    public void setBack(String back) {
        this.isback = back;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

//    public String getForward() {
//        return forward;
//    }
//
//    public void setForward(String forward) {
//        this.forward = forward;
//    }
}
