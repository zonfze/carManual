package com.viglle.carmanual.action.model;

import com.viglle.carmanual.widget.model.VgImageViewModel;

/**
 * Created by Administrator on 2016/5/16.
 */
public class FType{
    /**
     * share features
     */
    public static final int LAYOUT_WIDTH=1001;//控件宽度
    public static final int LAYOUT_HEIGHT=1002;//控件高度
    public static final int BG_COLOR=1003;//背景颜色
    public static final int VISIBLE=1004;//可见性(1,可见;2,不可见但是占据屏幕空间;0不可见,不暂居空间)



    public static final int TEXT=10001;//文字
    public static final int TEXT_COLOR=10002;//颜色
    public static final int TEXT_SIZE=10003;//字号
    public static final int TEXT_TYPEFACE=10004;//字体
    public static final int TEXT_ALIGN=10005;//对齐方式

    /**
     * VgImageView的缩放方式
     * {@link VgImageViewModel#SCALE_TYPE}
     */
    public static final int SCALE_TYPE=10006;//缩放方式
    /**
     * VgImageView的缩放方式
     * {@link VgImageViewModel#SRC}
     */
    public static final int SRC=10007;//图片路径
}
