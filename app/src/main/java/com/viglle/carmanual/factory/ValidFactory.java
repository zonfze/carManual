package com.viglle.carmanual.factory;

import android.content.Context;
import android.widget.TextView;

import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.utils.ToastUtil;
import com.viglle.carmanual.validation.BaseValidModel;
import com.viglle.carmanual.validation.ValidLengthModel;
import com.viglle.carmanual.validation.ValidMatcherModel;
import com.viglle.carmanual.validation.ValidType;
import com.viglle.carmanual.widget.VgCheckBox;
import com.viglle.carmanual.widget.entity.ViewTreeBean;
import com.viglle.carmanual.widget.model.BaseTextViewModel;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.VgViewType;

import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 */
public class ValidFactory {
    public static boolean validRefUIParams(Context ctx, BaseActionModel modelHttp, ViewTreeBean allViewMap) {

        List<Integer> refUI=modelHttp.getRef_ui();
//        List<TwoValues<String,String>> list=new ArrayList<>();
        for(int i=0;i<refUI.size();i++){
            int view_id=refUI.get(i);
            BaseViewModel baseViewModel = allViewMap.getViewModelById(view_id);
            if(baseViewModel!=null){
                int view_type=baseViewModel.getView_type();
                switch (view_type){
                    case VgViewType.VgTextField:
                        if(!checkTextParam(ctx,baseViewModel,getTextViewString(baseViewModel))){
                            return false;
                        }
                        break;
                    case VgViewType.VgRadioButton:

                        break;
                    case VgViewType.VgCheckBox:
                        if(!checkCheckBoxParam(ctx,baseViewModel)){
                            return false;
                        }
                        break;
                    case VgViewType.VgTextView:
                        if(!checkTextParam(ctx,baseViewModel,getTextViewString(baseViewModel))){
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }
    private static boolean checkTextParam(Context ctx,BaseViewModel baseViewModel,String valueStr){

        List<BaseValidModel> validLink=baseViewModel.getValidLink();
        if(validLink==null||validLink.isEmpty()){
            return true;
        }
//        boolean isOk=false;
        for(BaseValidModel model:validLink){
            int validId= model.getValid_id();
            switch (validId){
                case ValidType.REQUIRE://校验是否必填
                    if(valueStr==null||valueStr.equals("")){
                        ToastUtil.showToast(ctx,model.getValid_msg());
                       return false;
                    }
                    break;
                case ValidType.LENGT://校验输入字符个数范围
                    if(!checkTextLength(ctx, model, valueStr)){
                        return false;
                    }
                    break;
                case ValidType.MATCHER://正则校验
                    ValidMatcherModel matcherModel=(ValidMatcherModel)model;
                    if(!AppUtil.isMatcherStr(matcherModel.getRule(),valueStr)){
                        ToastUtil.showToast(ctx,model.getValid_msg());
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private static boolean checkTextLength(Context ctx, BaseValidModel model, String valueStr) {
        int min=((ValidLengthModel)model).getMin();
        int max=((ValidLengthModel)model).getMax();
        if(min>max){
            LogUtil.log_e("最小值不能大于最大值");
            ToastUtil.showToast(ctx, model.getValid_msg());
            return false;
        }else{

            if(min>0){//如果min>0&&min<max;则说明需要校验不为空的情况
                if(valueStr==null||valueStr.equals("")){//校验是否为空;
                    ToastUtil.showToast(ctx, model.getValid_msg());
                    return false;
                }else{
                    if(valueStr.length()>=min&&valueStr.length()<=max){//校验是否在指定的范围内
                        return true;
                    }else {
                        ToastUtil.showToast(ctx,model.getValid_msg());
                        return false;
                    }
                }
            }else {
                return true;
            }
        }
    }

//    private static String getTextFieldString(BaseViewModel model){
//        VgTextFieldModel textFieldModel= (VgTextFieldModel)model;
//        EditText editText=model.getVgView();
//        String value=editText.getText().toString();
//        return value;
//    }
    private static String getTextViewString(BaseViewModel model){
        BaseTextViewModel textViewModel= (BaseTextViewModel)model;
        TextView textView=textViewModel.getVgView();
        String value=textView.getText().toString();
        return value;
    }

    private  static boolean checkCheckBoxParam(Context ctx,BaseViewModel baseViewModel){
        VgCheckBox checkBox=baseViewModel.getVgView();
        if(checkBox==null){
            return false;
        }
        return checkBox.isChecked();
    }
}
