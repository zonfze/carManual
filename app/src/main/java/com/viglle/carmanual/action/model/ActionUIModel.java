package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/11.
 */
public class ActionUIModel extends BaseActionModel{
    private String url;
    public static final String URL="url";

    private int showType=0;//0 打开的是一个非网页界面;1代表打开一个网页界面
    public static final String SHOW_TYPE="show_type";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        if(isaNull(showType)){
            new Exception("showType 不能为空"+showType);
            this.showType = 9999;
        }
        if(!AppUtil.isNumeric(showType)){
            new Exception("showType 不能为非数字字符"+showType);
            this.showType = 9999;
        }
        this.showType = Integer.parseInt(showType);

    }
    public boolean isaNull(String str) {
        return str==null||str.equals("")||str.equalsIgnoreCase("null");
    }
}
