package com.viglle.carmanual.modules.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.base.UIType;
import com.viglle.carmanual.event.BaseEventModel;
import com.viglle.carmanual.event.EventType;
import com.viglle.carmanual.factory.EventFactory;
import com.viglle.carmanual.factory.ViewFactory;
import com.viglle.carmanual.parsor.VgEventParsor;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.widget.entity.BottomNavPoupItemModel;
import com.viglle.carmanual.widget.model.BaseViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends BaseActivity {

    String url="";
    int showType=0;
//    private String cacheKey="";

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<TwoValues<String,String>> params ;
        mUiType= UIType.UI_LOGIN;
        Intent intent=getIntent();
        if(intent==null){
            params=new ArrayList<>();

        }else{
            url=intent.getStringExtra("url");
            showType=intent.getIntExtra("showType",0);
            params= (List<TwoValues<String, String>>) intent.getSerializableExtra("params");
        }
      //  cacheKey= AppUtil.buildCacheKey(url,params);
//        String jsonStr = SharedPrefUtil.getInstance(this).getProperty(cacheKey,"");
//        if(!jsonStr.equals("")){
//            LogUtil.log_e("local_json",jsonStr);
//            handlerResult(jsonStr);
//            return;
//        }
        if(showType== BottomNavPoupItemModel.SHOW_TYPE_WEB){

        }else{
            HttpUtil.httpPost(url,params, new HttpHandlerInterface() {
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
                JSONArray array=resultObj.getJSONArray(BaseActivity.EVENT_LINK);
                eventModelList=VgEventParsor.parsorEventLink(array);
                EventFactory.createEventLink(mCtx, mViewTreeBean, eventModelList);
                if(view!=null){
                    setContentView(view);
                }
            }else if(res_type.equals(RES_TYPE_1002)){
                JSONArray array=resultObj.getJSONArray(BaseActivity.EVENT_LINK);
                EventFactory.createEventLink(mCtx, mViewTreeBean, VgEventParsor.parsorEventLink(array));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(getBackKeyModel(eventModelList)!=null){//首先判断是否有返回按钮物理事件，如果有则先执行；否则执行系统默认事件
            showExitApp();
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }

    }

    /**
     * 用来获取BackKey事件实体
     *
     * @param list
     * @return
     */
    private BaseEventModel getBackKeyModel(List<BaseEventModel> list){
        if(list==null||list.isEmpty()){
            return null;
        }
        for(BaseEventModel model:list){
            if(model.getEventType()== EventType.TOUCH_BACKKEY_DRVIE){
                return model;
            }
        }
        return null;
    }

    long exitTime = 0;
    /**
     * 展示退出程序
     */
    private void showExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(this, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }
}
