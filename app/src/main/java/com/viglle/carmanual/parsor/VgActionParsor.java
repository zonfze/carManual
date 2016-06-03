package com.viglle.carmanual.parsor;

import com.viglle.carmanual.action.ActionType;
import com.viglle.carmanual.action.model.ActionCloseNewModel;
import com.viglle.carmanual.action.model.ActionCloseUIModel;
import com.viglle.carmanual.action.model.ActionHttpModel;
import com.viglle.carmanual.action.model.ActionNewUIModel;
import com.viglle.carmanual.action.model.ActionTimeModel;
import com.viglle.carmanual.action.model.ActionToastModel;
import com.viglle.carmanual.action.model.ActionUIModel;
import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.action.model.BaseFModel;
import com.viglle.carmanual.action.model.FModel;
import com.viglle.carmanual.action.model.ResultModel;
import com.viglle.carmanual.event.BaseEventModel;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.net.TwoValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class VgActionParsor {


    public static List<BaseActionModel> parsorActionLink(JSONArray array)throws JSONException{

        List<BaseActionModel> list = new ArrayList<>();
        if (!checkJSONArray(array)) return null;
        for (int i=0;i<array.length();i++){
            JSONObject obj=array.getJSONObject(i);
            BaseActionModel actionModel=parsorActionModel(obj);
            if(actionModel!=null){
                list.add(actionModel);
            }
        }
        return list;
    }

    public static BaseActionModel parsorActionModel(JSONObject jsonObject) throws JSONException {
        if (!checkObj(jsonObject)) return null;
        String actionTypeStr=jsonObject.getString(BaseActionModel.ACTION_TYPE);
        int actionType=0;
        if(actionTypeStr==null){//判断获取到的字符串是否合法,避免空指针
            return null;
        }
        if(!AppUtil.isNumeric(actionTypeStr)){//校验字符串是否是由数字组成;避免字符串转数字的时异常
            return null;
        }
        try {
            actionType =Integer.parseInt(actionTypeStr);//字符串转数字
        }catch (Exception  e){//捕获转化异常;避免转换错误时程序崩溃
            e.printStackTrace();
            return null;
        }
        switch (actionType){
            case ActionType.ACTION_SHOW_TOAST:
                ActionToastModel  modelToast=new ActionToastModel();
                modelToast.setText(jsonObject.getString(ActionToastModel.TEXT));
                modelToast.setActionType(actionTypeStr);
                modelToast.setParams(parsorParams(jsonObject));
                modelToast.setModifidLink(parsorFModelLink(jsonObject));
                return modelToast;
            case ActionType.ACTION_SHOW_SNACKBAR://弹出Snackbar提示

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_SDCARD://从sdcard读取数据

                break;
            case ActionType.ACTION_SAVE_PARAM_TO_SDCARD://保存数据到sdcard上

                break;
            case ActionType.ACTION_OBTAIN_PARAM_FROM_VIEW://获取View控件上的值

                break;
            case ActionType.ACTION_UPDATE_VIEW:

                break;
            case ActionType.ACTION_NEW_PANEL://打开一个界面
                ActionNewUIModel uiModel=new ActionNewUIModel();
                uiModel.setUrl(jsonObject.getString(ActionNewUIModel.URL));
                uiModel.setShowType(jsonObject.getString(ActionUIModel.SHOW_TYPE));
                uiModel.setActionType(actionTypeStr);
                uiModel.setParams(parsorParams(jsonObject));
                uiModel.setModifidLink(parsorFModelLink(jsonObject));
                return uiModel;
            case ActionType.ACTION_CLOSE_PANEL://关闭一个界面
                ActionCloseUIModel closeUIModel=new ActionCloseUIModel();
                closeUIModel.setActionType(actionTypeStr);
                closeUIModel.setParams(parsorParams(jsonObject));
                closeUIModel.setModifidLink(parsorFModelLink(jsonObject));
                return closeUIModel;
            case ActionType.ACTION_CLOSE_AND_NEW_PANEL://打开一个界面并关闭上一个界面
                ActionCloseNewModel closeNewModel=new ActionCloseNewModel();
                closeNewModel.setUrl(jsonObject.getString(ActionCloseNewModel.URL));
                closeNewModel.setShowType(jsonObject.getString(ActionUIModel.SHOW_TYPE));
                closeNewModel.setActionType(actionTypeStr);
                closeNewModel.setParams(parsorParams(jsonObject));
                closeNewModel.setModifidLink(parsorFModelLink(jsonObject));
                return closeNewModel;
            case ActionType.ACTION_SEND_HTTP_REQUEST://http请求
                ActionHttpModel  modelHttp=new ActionHttpModel();
                modelHttp.setUrl(jsonObject.getString(ActionHttpModel.URL));
                modelHttp.setActionType(actionTypeStr);
                ResultModel resultModel=parsorResultModel(jsonObject);
                modelHttp.setResult(resultModel);
                modelHttp.setRef_ui(parsorRefUi(jsonObject));
                modelHttp.setParams(parsorParams(jsonObject));
                modelHttp.setModifidLink(parsorFModelLink(jsonObject));
                return modelHttp;
            case ActionType.ACTION_COUNT_TIMER://计时

                break;
            case ActionType.ACTION_COUNT_DOWN_TIMER://倒计时
                ActionTimeModel timeModel=new ActionTimeModel();
                timeModel.setActionType(actionTypeStr);
                timeModel.setParams(parsorParams(jsonObject));
                timeModel.setModifidLink(parsorFModelLink(jsonObject));
//                timeModel.setRef_ui(parsorRefUi(jsonObject));
                timeModel.setChange_view_id(jsonObject.getString(ActionTimeModel.CHANGEVIEW_ID));
                timeModel.setTime(jsonObject.getString(ActionTimeModel.TIME));
                return timeModel;
            case ActionType.ACTION_SHOW_DIALOG://弹出对话框

                break;
            case ActionType.ACTION_DIMISS_DIALOG://关闭对话框

                break;
        }

        return null;
    }


    private static ResultModel parsorResultModel(JSONObject resultObj) throws JSONException {
        if(!checkObj(resultObj)){
            return null;
        }
        ResultModel resultModel=new ResultModel();
        JSONObject onResultObj=resultObj.getJSONObject(ActionHttpModel.RESULT);
        if(!checkObj(onResultObj)){
            return null;
        }
        LogUtil.log_e("result="+onResultObj.toString());
        JSONArray arrayOk=onResultObj.getJSONArray(ResultModel.ON_OK);
        JSONArray arrayFail=onResultObj.getJSONArray(ResultModel.ON_FAIL);
        List<BaseEventModel> modelsOk=VgEventParsor.parsorEventLink(arrayOk);
        resultModel.setOnOkList(modelsOk);
        List<BaseEventModel> modelsFail=VgEventParsor.parsorEventLink(arrayFail);
        resultModel.setOnFailList(modelsFail);


        return resultModel;
    }

    private static List<String> parsorRefUi(JSONObject json) throws JSONException {
        List<String> list = new ArrayList<>();
        if (!checkObj(json)) return null;
        JSONArray array=json.getJSONArray(ActionHttpModel.REF_UI);
        if (!checkJSONArray(array)) return null;
        for(int i=0;i<array.length();i++){
            list.add((String)array.get(i));
        }
        return list;
    }

    private static List<TwoValues<String,String>> parsorParams(JSONObject json) throws JSONException {
        List<TwoValues<String,String>> list = new ArrayList<>();
        if (!checkObj(json)) return null;
        JSONArray array=json.getJSONArray(BaseActionModel.PARAMS);
        if (!checkJSONArray(array)) return null;
        for(int i=0;i<array.length();i++){
            String as=array.getString(i);
            if(as!=null&&!as.equals("")&&as.contains("=")){//避免空指针异常
                String [] ass=as.split("=");
                if(ass.length>=2){//避免数组越界异常
                    list.add(new TwoValues<String, String>(ass[0],ass[1]));
                }
            }
        }

        return list;
    }

    private static List<BaseFModel> parsorFModelLink(JSONObject Obj) throws JSONException {
        if(!checkObj(Obj)){
            return null;
        }
        List<BaseFModel> list = new ArrayList<>();
        JSONArray array=Obj.getJSONArray(BaseActionModel.MODIFY_LINK);
        if(!checkJSONArray(array)){
            return null;
        }
        for(int i=0;i<array.length();i++){
            JSONObject object=array.getJSONObject(i);
            BaseFModel model=parsorFModel(object);
            if(model!=null){
                list.add(model);
            }
        }
        return list;
    }

    private static BaseFModel parsorFModel(JSONObject fmodelObj) throws JSONException {
        if(!checkObj(fmodelObj)){
            return null;
        }
        FModel model=new FModel();
        String f_type_str=fmodelObj.getString(BaseFModel.F_TYPE);
        model.setF_type(f_type_str);
        if(model.getF_type()<0){//f_type小于0的话;就是找不到相应的f_type;因此返回null
            return null;
        }
        model.setF_value(fmodelObj.getString(BaseFModel.F_VALUE));
        model.setRef_id(fmodelObj.getString(BaseFModel.REF_ID));
        return model;
    }



    private static boolean checkJSONArray(JSONArray array) {
        if(array==null||array.length()<1){//避免空指针异常
            return false;
        }
        return true;
    }

    private static boolean checkObj(JSONObject json) {
        if(json==null||json.toString().equals("")||json.toString().equalsIgnoreCase("null")||json.toString().equals("{}")){//判断参数是否合法
            return false;
        }
        return true;
    }
}
