package com.viglle.carmanual.widget.entity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommonAdapter extends BaseAdapter {
    private Context mCtx;

    public CommonAdapter(Context ctx) {
        mCtx = ctx;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mCtx);
        textView.setText("ddddddddddddddddddddddddd");
        textView.setTextSize(24);
        return textView;
    }
}