package com.viglle.carmanual.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.viglle.carmanual.event.BaseEventModel;
import com.viglle.carmanual.modules.UICustomActivity;
import com.viglle.carmanual.modules.WebCustomActivity;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.widget.entity.BottomNavPoupItemModel;
import com.viglle.carmanual.widget.entity.ViewTreeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public int mUiType=100;//记录当前界面所属的类型(@link{UIType.java}),默认是普通类型的界面
    protected ViewTreeBean mViewTreeBean=new ViewTreeBean();
    public void clearMap(){
        mViewTreeBean.clear();
    }
    public List<BaseEventModel> eventModelList = null;

    public ViewTreeBean getmViewTree(){
        return mViewTreeBean;
    }
    public static final String EVENT_LINK="eventLink";
    public static final String DATA="data";
    public static final String RES_TYPE="res_type";


//    public static final String ACTION_LINK="actionLink";
    public static final String MSG="msg";
    public static final String RES_TYPE_1001="1001";//ui界面数据
    public static final String RES_TYPE_1002="1002";//action数据

    protected Context mCtx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatusBar();
        mCtx=this;
    }

    /**
     * extends AppCompatActivity
     * 实现沉浸式效果
     */
    private void setTranslucentStatusBar() {
        if(Build.VERSION.SDK_INT >= 19){//4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if(Build.VERSION.SDK_INT >= 21) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
        }
    }

    /**
     * 根据showType类型判断是否打开具有WebView的Activity
     * @param ctx
     * @param showType
     * @return
     */
    public static Intent createIntent(Context ctx,int showType){
        Intent intent;
        if(showType== BottomNavPoupItemModel.SHOW_TYPE_WEB){
            intent=new Intent(ctx, WebCustomActivity.class);
        }else{
            intent=new Intent(ctx, UICustomActivity.class);
        }
        return intent;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(mUiType!=UIType.UI_MAIN){
            return super.onKeyDown(keyCode, event);
        }else{
            showExitApp();
            return true;
        }
    }
    long exitTime = 0;

    /**
     * 展示退出程序
     */
    private void showExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(this,"再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}
