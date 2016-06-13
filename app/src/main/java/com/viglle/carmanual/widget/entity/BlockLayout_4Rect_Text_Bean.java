package com.viglle.carmanual.widget.entity;

import com.viglle.carmanual.utils.AppUtil;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/12.
 */
public class BlockLayout_4Rect_Text_Bean implements Serializable{
    private String labelId;//菜单的唯一标识
    public static final String LABEL_ID="labelid";

    private String title;//导航标签的文字
    public static final String TAB_TEXT="title";

    private String url;
    public static final String URL="url";

    private String showType;//1表示在WebView中打开;0 表示请求一个UI界面；默认是0
    public static final String SHOW_TYPE="showType";
    public static final int SHOW_TYPE_WEB=1;
    public static final int SHOW_TYPE_UI=0;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BottomNavPoupItemModel{" +
                "labelId='" + labelId + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public int getShowType() {
        if(showType==null||showType.equals("")||showType.equalsIgnoreCase("null")){
            return 0;
        }
        if(!AppUtil.isNumeric(showType)){
            return 0;
        }
        return Integer.parseInt(showType);
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }
}
