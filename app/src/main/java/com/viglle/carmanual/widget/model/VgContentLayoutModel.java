package com.viglle.carmanual.widget.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/4/15.
 */
public class VgContentLayoutModel extends BaseViewModel {

//    public static final String ORIENTATION="orientation";//Android线性布局中的布局方向标志(纵向/横向)
//    private String orientation;
//
//    public static final String GRAVITY="gravity";
//    private String gravity;
    private String clickable;//是否可以获取焦点和点击事件;0为不可点击;1位可点击
    public static final String CLICKABLE="clickable";

    public boolean getClickable() {
        if(isaNull(clickable)){
            return false;
        }
        if(!AppUtil.isNumeric(clickable)){
            return false;
        }
        if(clickable.equals("0")){
            return false;
        }
        return true;
    }

    public void setClickable(String clickable) {
        this.clickable = clickable;
    }
}
