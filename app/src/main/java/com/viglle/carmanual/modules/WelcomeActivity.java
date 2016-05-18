package com.viglle.carmanual.modules;

import android.os.Bundle;
import android.view.View;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.base.UIType;
import com.viglle.carmanual.event.BaseEventModel;
import com.viglle.carmanual.factory.EventFactory;
import com.viglle.carmanual.factory.ViewFactory;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.widget.model.BaseViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class WelcomeActivity extends BaseActivity {
//    private Map<Integer,BaseViewModel> mViewMap=new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mUiType= UIType.UI_WELCOME;
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
//        }
        HttpUtil.httpGet(AppUtil.FIRST_URL, new HttpHandlerInterface() {
            @Override
            public void onSuccess(String data) {
                handlerResult(data);
            }
            @Override
            public void onFailure(int statusCode, IOException e) {
                LogUtil.log_e(e.toString());
            }
        });
    }

    private void handlerResult(String jsonStr){
        JSONObject rootObj = null;
        try {
            rootObj = new JSONObject(jsonStr);
            if (rootObj.getInt("retCode") != 101) {
                return;
            }
            JSONObject resultObj = rootObj.getJSONObject(DATA);
            String res_type=resultObj.getString(RES_TYPE);
            if(res_type.equals(RES_TYPE_1001)){
                BaseViewModel treeModel = VgUIParsor.parserUIModelTree(mCtx, resultObj);
                View view=ViewFactory.createViewTree(mCtx, treeModel, mViewTreeBean);
                JSONArray array=resultObj.getJSONArray(EVENT_LINK);
                List<BaseEventModel> eventModelList=VgEventParsor.parsorEventLink(array);
                EventFactory.createEventLink(mCtx, mViewTreeBean, eventModelList);
                if(view!=null){
                    setContentView(view);
                }

            }else if(res_type.equals(RES_TYPE_1002)){
                JSONArray array=resultObj.getJSONArray(EVENT_LINK);
                EventFactory.createEventLink(mCtx, mViewTreeBean, VgEventParsor.parsorEventLink(array));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
