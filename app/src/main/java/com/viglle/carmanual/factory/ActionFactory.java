package com.viglle.carmanual.factory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.viglle.carmanual.action.ActionType;
import com.viglle.carmanual.action.model.ActionCloseNewModel;
import com.viglle.carmanual.action.model.ActionHttpModel;
import com.viglle.carmanual.action.model.ActionNewUIModel;
import com.viglle.carmanual.action.model.ActionToastModel;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.base.BaseActivity;
import com.viglle.carmanual.modules.user.LoginActivity;
import com.viglle.carmanual.parsor.VgUIParsor;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.SharedPrefUtil;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.utils.net.HttpHandlerInterface;
import com.viglle.carmanual.utils.net.HttpUtil;
import com.viglle.carmanual.utils.net.TwoValues;
import com.viglle.carmanual.widget.entity.ViewTreeBean;
import com.viglle.carmanual.widget.model.BaseTextViewModel;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.VgTextFieldModel;
import com.viglle.carmanual.widget.model.VgTextViewModel;
import com.viglle.carmanual.widget.model.VgViewType;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class ActionFactory{
    public static void createActionLink(Context ctx,ViewTreeBean viewTreeBean,List<BaseActionModel> links){
        if(links==null||links.isEmpty()){//校验参数是否异常,异常则返回
            return;
        }
        for(int i=0;i<links.size();i++){
            BaseActionModel model=links.get(i);
            createAction(ctx,viewTreeBean,model);
        }
    }

    public static void createAction(final Context ctx,final ViewTreeBean viewTreeBean,BaseActionModel model){
        if(model==null){//检验参数是否异常
            return;
        }
        int actionType=model.getActionType();
        switch (actionType){
            case ActionType.ACTION_SHOW_TOAST://显示Toast
                ActionToastModel modelToast= (ActionToastModel) model;
                UpdateViewFactory.modifyViews(ctx, viewTreeBean, model);
                ToastUtil.showToast(ctx,modelToast.getText());
                break;
            case ActionType.ACTION_SHOW_SNACKBAR://显示Snackbar

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_SDCARD://从sdcard读取数据

                break;
            case ActionType.ACTION_SAVE_PARAM_TO_SDCARD://将数据保存到sdcard

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_VIEW://从获取控件的值;比如EditText中的文字getText()

                break;
            case ActionType.ACTION_UPDATE_VIEW://刷新UI

                break;
            case ActionType.ACTION_NEW_PANEL://打开一个新界面
                ActionNewUIModel uiModel= (ActionNewUIModel) model;
                UpdateViewFactory.modifyViews(ctx,viewTreeBean,model);
                Intent intentuiModel=new Intent(ctx,LoginActivity.class);
                intentuiModel.putExtra("params", (Serializable) model.getParams());
                intentuiModel.putExtra("url", uiModel.getUrl());
                ctx.startActivity(intentuiModel);
                break;
            case ActionType.ACTION_CLOSE_PANEL://关闭一个界面
//                ActionCloseUIModel closeUIModel=(ActionCloseUIModel)model;
                ((Activity)ctx).finish();
                break;
            case ActionType.ACTION_CLOSE_AND_NEW_PANEL://打开一个新界面并关闭上一个界面
                ActionCloseNewModel closeNewModel= (ActionCloseNewModel) model;
                Intent intent=new Intent(ctx,LoginActivity.class);
                UpdateViewFactory.modifyViews(ctx,viewTreeBean,model);
                List<TwoValues<String,String>> refUIList = configRefUIParams(ctx,model,viewTreeBean);
                List<TwoValues<String,String>> staticList= configStaticParams(ctx,model,viewTreeBean);

                refUIList.addAll(staticList);
                intent.putExtra("params", (Serializable) refUIList);
                intent.putExtra("url", closeNewModel.getUrl());
                ctx.startActivity(intent);
                ((Activity)ctx).finish();
                break;
            case ActionType.ACTION_SEND_HTTP_REQUEST://发送httpq请求
                createHttpRequestAction(ctx, viewTreeBean, (ActionHttpModel) model);
                break;
            case ActionType.ACTION_COUNT_TIMER://计时

                break;
            case ActionType.ACTION_COUNT_DOWN_TIMER://倒计时

                break;
            case ActionType.ACTION_SHOW_DIALOG://显示对话框

                break;
            case ActionType.ACTION_DIMISS_DIALOG://隐藏对话框

                break;
            case ActionType.ACTION_MODIFY_VIEW_VALUE://修改View的某个属性

                break;

        }
    }

    private static void createHttpRequestAction(final Context ctx,final ViewTreeBean viewTreeBean, final ActionHttpModel model){
        if(model==null){
            return;
        }
        if(!ValidFactory.validRefUIParams(ctx, model, viewTreeBean)){
            return;
        }
        List<TwoValues<String,String>> list = configRefUIParams(ctx, model, viewTreeBean);//获取控件上的值组装成请求参数的格式
        List<TwoValues<String,String>> staticListParams=configStaticParams(ctx, model, viewTreeBean);
        list.addAll(staticListParams);//合并参数
        UpdateViewFactory.modifyViews(ctx, viewTreeBean, model);
        HttpUtil.httpPost(model.getUrl(),list , new HttpHandlerInterface() {
            @Override
            public void onSuccess(String data) {
                JSONObject rootObj = null;
                try {
                    rootObj = new JSONObject(data);
                    if (rootObj.getInt("retCode") != 101) {
                        ToastUtil.showToast(ctx,rootObj.getString(BaseActivity.MSG));
                        return;
                    }
                    JSONObject resultObj = rootObj.getJSONObject(BaseActivity.DATA);
                    String res_type=resultObj.getString(BaseActivity.RES_TYPE);
                    if(res_type.equals(BaseActivity.RES_TYPE_1001)){
                        BaseViewModel treeModel = VgUIParsor.parserUIModelTree(ctx, resultObj);
                        ((BaseActivity)ctx).setContentView(ViewFactory.createViewTree(ctx,treeModel,viewTreeBean));
                    }else if(res_type.equals(BaseActivity.RES_TYPE_1002)){
//                        JSONArray array=resultObj.getJSONArray(BaseActivity.EVENT_LINK);
                        EventFactory.createEventLink(ctx, viewTreeBean, model.getResult().getOnOkList());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, IOException e) {
                e.printStackTrace();
                ToastUtil.showToast(ctx, "网络不给力啊");
            }
        });
    }


    private static List<TwoValues<String,String>> configRefUIParams(Context ctx, BaseActionModel modelHttp, ViewTreeBean allViewMap) {
        List<Integer> refUI=modelHttp.getRef_ui();
        List<TwoValues<String,String>> list=new ArrayList<>();
        for(int i=0;i<refUI.size();i++){
            int view_id=refUI.get(i);
            BaseViewModel baseViewModel = allViewMap.getViewModelById(view_id);
            if(baseViewModel!=null){
                int view_type=baseViewModel.getView_type();
                switch (view_type){
                    case VgViewType.VgTextField:
                        list.add(getTextFieldKeyValue((BaseTextViewModel)baseViewModel));
                        break;
                    case VgViewType.VgRadioButton:

                        break;
                    case VgViewType.VgCheckBox:

                        break;
                    case VgViewType.VgTextView:
                        list.add(getTextViewKeyValue(baseViewModel));
                        break;
                }
            }
        }
        return list;
    }

    private static List<TwoValues<String,String>> configStaticParams(Context ctx, BaseActionModel modelHttp, ViewTreeBean allViewMap){
        List<TwoValues<String,String>> list=new ArrayList<>();
        if(modelHttp==null){
            return list;
        }

        List<TwoValues<String,String>> params=modelHttp.getParams();
        if(params==null||params.isEmpty()){
            return list;
        }
        for(TwoValues<String,String> model:params){
            if(model.key.equals("deviceid")){
                model.value= AppUtil.IMEI(ctx);
            }else if(model.key.equals("openid")){
                model.value= SharedPrefUtil.getInstance(ctx).getProperty(model.key,"");
            }else if(model.key.equals("userid")){
                model.value= SharedPrefUtil.getInstance(ctx).getProperty(model.key,"");
            }
        }
        return params;
    }

    private static TwoValues<String,String> getTextFieldKeyValue(BaseTextViewModel model){
        VgTextFieldModel textFieldModel= (VgTextFieldModel)model;
        String  key=textFieldModel.getKey();
        EditText editText=textFieldModel.getVgView();
        String value=editText.getText().toString();
       return new TwoValues<>(key,value);
    }
    private static TwoValues<String,String> getTextViewKeyValue(BaseViewModel model){
        VgTextViewModel textViewModel= (VgTextViewModel)model;
        String key=textViewModel.getKey();
        TextView textView=textViewModel.getVgView();
        String value=textView.getText().toString();
        return new TwoValues<>(key,value);
    }


}
