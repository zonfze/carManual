package com.viglle.carmanual.modules;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.viglle.carmanual.event.BaseEventModel;
import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.base.UIType;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.factory.EventFactory;
import com.viglle.carmanual.factory.ViewFactory;

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

                List<BaseEventModel> eventModelList=VgEventParsor.parsorEventLink(resultObj);
                EventFactory.createEventLink(mCtx, mViewTreeBean, eventModelList);
                if(view!=null){
                    setContentView(view);
                }

            }else if(res_type.equals(RES_TYPE_1002)){
                EventFactory.createEventLink(mCtx, mViewTreeBean, VgEventParsor.parsorEventLink(resultObj));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
