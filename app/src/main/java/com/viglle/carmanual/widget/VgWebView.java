package com.viglle.carmanual.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Administrator on 2016/5/18.
 */
public class VgWebView extends WebView{
    public VgWebView(Context context) {
        super(context);
    }

    public VgWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VgWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public VgWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
    }
}
