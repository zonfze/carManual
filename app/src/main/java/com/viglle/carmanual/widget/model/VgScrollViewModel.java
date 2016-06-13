package com.viglle.carmanual.widget.model;

/**
 * Created by Administrator on 2016/6/2.
 */
public class VgScrollViewModel extends BaseViewModel{
    private String isShowBar="1";//是否显示滚动条.0表示不显示,1表示显示,默认是 1 显示
    public static final String SHOWBAR="isShowBar";

    public boolean getIsShowBar() {
       if(isaNull(isShowBar)||isShowBar.equals("0")){// 该数据为空,则默认显示滚动条
           return false;
       }else{
           return true;
       }
    }

    public void setIsShowBar(String isShowBar) {
        this.isShowBar = isShowBar;
    }
}
