package com.viglle.carmanual.factory;

/**
 * Created by Administrator on 2016/5/9.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viglle.carmanual.seletor.SelectorFactory;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.widget.entity.BottomNavPoupItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/5.
 */
public class VgBottomNavPupopLayout111 extends LinearLayout {
    public List<BottomNavPoupItemModel> mDatas=new ArrayList<>();
    Drawable drawable =null;

    public VgBottomNavPupopLayout111(Context context) {
        super(context);
        initView();
    }

    public VgBottomNavPupopLayout111(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public VgBottomNavPupopLayout111(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){
        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, AppUtil.dip2px(getContext(), 48)));
        setBackgroundColor(Color.parseColor("#ffe6e6e6"));
        drawable=new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                Paint pant=new Paint();
                pant.setStyle(Paint.Style.FILL);
                pant.setColor(Color.parseColor("#ffe6e6e6"));
                Path path=new Path();
                path.moveTo(AppUtil.dip2px(getContext(),15),0);
                path.lineTo(AppUtil.dip2px(getContext(),15), AppUtil.dip2px(getContext(),15));
                path.lineTo(0, AppUtil.dip2px(getContext(),15));
                path.close();
                canvas.drawPath(path,pant);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
        Button btn=new Button(getContext());
        LayoutParams btnParams=new LayoutParams(AppUtil.dip2px(getContext(),48),AppUtil.dip2px(getContext(),48));
        btnParams.setMargins(0, 1, 1, 1);
        btn.setText("qq");
        btn.setBackgroundColor(Color.parseColor("#ffffffff"));
        btn.setGravity(Gravity.CENTER);
        btn.setLayoutParams(btnParams);

    }


    public void setDatas(List<BottomNavPoupItemModel> list){
        if(list==null||list.isEmpty()){
            return;
        }
        if(!mDatas.isEmpty()){
            mDatas.clear();
        }
        mDatas.addAll(list);
    }

    private View createItem(final BottomNavPoupItemModel model,final int p) {
        final RelativeLayout itemLayout=new RelativeLayout(getContext());
        itemLayout.setClickable(true);

        LayoutParams itemLayoutParams=new LayoutParams(0, AppUtil.dip2px(getContext(),48));
        itemLayoutParams.weight=1;
        if(p==mDatas.size()-1){
            itemLayoutParams.setMargins(0, 1, 0, 0);
        }else{
            itemLayoutParams.setMargins(0, 1, 1, 0);
        }

        itemLayout.setLayoutParams(itemLayoutParams);
        itemLayout.setBackgroundColor(Color.parseColor("#ffffffff"));

        if(model.getSubMenu()!=null&&!model.getSubMenu().isEmpty()){
            ImageView mAngle=new ImageView(getContext());
            RelativeLayout.LayoutParams imageParams=new RelativeLayout.LayoutParams(AppUtil.dip2px(getContext(),15),
                    AppUtil.dip2px(getContext(),15));
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            imageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            imageParams.setMargins(0, 0, AppUtil.dip2px(getContext(),5), AppUtil.dip2px(getContext(),5));
            mAngle.setLayoutParams(imageParams);
            mAngle.setBackgroundDrawable(drawable);
            itemLayout.addView(mAngle);
        }

        TextView textView = new TextView(getContext());
        RelativeLayout.LayoutParams txtParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        setItemBackgroud(itemLayout);
        textView.setText(model.getTitle());
        textView.setTextSize(AppUtil.px2sp(getContext(),42));
        textView.setTextColor(Color.parseColor("#ff000000"));
        textView.setGravity(Gravity.CENTER);
        itemLayout.addView(textView);
        textView.setLayoutParams(txtParams);
        itemLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getSubMenu() != null && !model.getSubMenu().isEmpty()) {
                    showPoupWindow(itemLayout, model);
                } else {
                    if(mMenuItemClick==null){
                        return;
                    }
                    mMenuItemClick.onMenuItemClick(model,p);
                }
            }
        });


        return itemLayout;
    }

    private void showPoupWindow(View clickedView, final BottomNavPoupItemModel model){

        if(model.getSubMenu()==null||model.getSubMenu().isEmpty()){
            return;
        }
        int width=clickedView.getWidth();
        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        listView.setSelector(SelectorFactory.getInstance().newSeletor(getContext(),
                "#ffffffff",
                "#ffe6e6e6",
                "#ffe6e6e6",
                "#ffffffff"));
        MySubMenuAdapter adapter=new MySubMenuAdapter(getContext());
        adapter.setSubDatas(model.getSubMenu());
        listView.setAdapter(adapter);
        listView.setVerticalScrollBarEnabled(false);

        final PopupWindow popupWindow=new PopupWindow();
        popupWindow.setContentView(listView);
        popupWindow.setWidth(width);
        popupWindow.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                BottomNavPoupItemModel itemModel = (BottomNavPoupItemModel) parent.getItemAtPosition(position);
                if(mMenuItemClick==null){
                    return;
                }
                mMenuItemClick.onSubMenuItemClick(itemModel,position);
            }
        });
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int [] whArray=new int[2];
        clickedView.getLocationOnScreen(whArray);
        float dd=whArray[1]-model.getSubMenu().size()*(AppUtil.dip2px(getContext(),48)+AppUtil.getDensity(getContext()));
        popupWindow.showAtLocation(clickedView,0,whArray[0],(int)dd);
    }

    private void setItemBackgroud(View view){
        if(Build.VERSION.SDK_INT>=16){
            view.setBackground(SelectorFactory.getInstance().newSeletor(
                    getContext(),
                    "#ffffffff",
                    "#ffe6e6e6",
                    "#ffe6e6e6",
                    "#ffffffff"));
        }else{
            view.setBackgroundDrawable(SelectorFactory.getInstance().newSeletor(getContext(),
                    "#ffffffff",
                    "#ffe6e6e6",
                    "#ffe6e6e6",
                    "#ffffffff"));
        }
    }

    private class MySubMenuAdapter extends BaseAdapter {
        Context mCtx;

        private List<BottomNavPoupItemModel> mList = new ArrayList<>();
        public MySubMenuAdapter(Context ctx) {
            mCtx=ctx;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public BottomNavPoupItemModel getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(mCtx);

            textView.setText(getItem(position).getTitle());
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(14);
            textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,AppUtil.dip2px(mCtx,48)));
            textView.setTextColor(Color.parseColor("#ff000000"));
//            textView.setPadding(AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10),AppUtil.dip2px(mCtx,10));
            setItemBackgroud(textView);
            return textView;
        }

        public void setSubDatas(List<BottomNavPoupItemModel> list){
            if(list==null){
                return;
            }
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public interface OnMenuItemClick<T>{
    /**
     *
     * @param model 数据模型
     * @param position 标识的几个菜单(也就是菜单索引)
     */
    void onMenuItemClick(BottomNavPoupItemModel model,int position);

    /**
     * 子菜单被点击
     * @param model
     * @param position
     */
    void onSubMenuItemClick(BottomNavPoupItemModel model,int position);
}

    private OnMenuItemClick mMenuItemClick=null;

    public OnMenuItemClick getMenuItemClick() {
        return mMenuItemClick;
    }

    public void setMenuItemClick(OnMenuItemClick mMenuItemClick) {
        this.mMenuItemClick = mMenuItemClick;
    }
}
