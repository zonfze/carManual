package com.viglle.carmanual.widget.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import com.viglle.carmanual.widget.entity.BlockLayout_4Rect_Text_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class BlockLayout_4Rect_Text extends GridView{
    public BlockLayout_4Rect_Text(Context context) {
        super(context);
    }

    public BlockLayout_4Rect_Text(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BlockLayout_4Rect_Text(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDatas(List<BlockLayout_4Rect_Text_Bean> list){

    }


    private class MyAdapter{
        Context mCtx;
        private List<BlockLayout_4Rect_Text_Bean> mDatas=new ArrayList<>();
        public MyAdapter(Context ctx){
            mCtx=ctx;
        }

        public void setDatas(List<BlockLayout_4Rect_Text_Bean> list){
            if(list==null||list.isEmpty()){
                return;
            }
            if(!mDatas.isEmpty()){
                mDatas.clear();
            }
            mDatas.addAll(list);
        }
        public void addDatas(List<BlockLayout_4Rect_Text_Bean> list){
            if(list==null||list.isEmpty()){
                return;
            }
            mDatas.addAll(list);
        }
    }

}
