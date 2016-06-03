package com.viglle.carmanual.action.model;



import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.net.TwoValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public abstract class BaseActionModel {
    private int actionType;//action的类型
    public static final String ACTION_TYPE="actionType";

    private List<TwoValues<String,String>> params=new ArrayList<>();
    public static final String PARAMS="params";

    /**
     * ref_ui 主要是说明该动作需要获取哪些控件上的值
     */
    private List<Integer>ref_ui=new ArrayList<>();
    public static final String REF_UI="ref_ui";

    /**
     * 该action需要修改的view的队列
     */
    private List<BaseFModel> modifidLink=new ArrayList<>();
    public static final String MODIFY_LINK="modify";



    public int getActionType() {

        return actionType;
    }

    public void setActionType(String actionType) {
        if(actionType==null||actionType.equals("")||actionType.equals("null")){
            this.actionType = 0;
        }
        if(!AppUtil.isNumeric(actionType)){
            this.actionType = 0;
        }
        this.actionType = Integer.parseInt(actionType);
    }

    public List<TwoValues<String,String>> getParams() {
        if(params==null){
            return new ArrayList<>();
        }
        return params;
    }

    public void setParams(List<TwoValues<String,String>> params) {
        if(params==null||params.isEmpty()){
            return;
        }
        if(this.params==null){
            this.params=new ArrayList<>();
        }
        if(!this.params.isEmpty()){
            this.params.clear();
        }
        this.params.addAll(params);

    }

    public List<Integer> getRef_ui() {
        return ref_ui;
    }

    public void setRef_ui(List<String> refui) {
        if(refui==null||refui.isEmpty()){
            return;
        }
        if(!ref_ui.isEmpty()){
            ref_ui.clear();
        }
        for(int i = 0;i<refui.size();i++) {
            String item=refui.get(i);
            if(item==null ||item.equals("")||item.equals("null")){
                new Exception("refui 不能为无效"+item);
                return;
            }
            if(!AppUtil.checkNumber(item, "")){
                return;
            }
            ref_ui.add(Integer.parseInt(item));
        }
    }
    public List<BaseFModel> getModifidLink() {
        return modifidLink;
    }

    public void setModifidLink(List<BaseFModel> link) {
        if(link==null||link.isEmpty()){
            return;
        }
        if(modifidLink==null){
            modifidLink=new ArrayList<>();
        }
        if(!modifidLink.isEmpty()){
            modifidLink.clear();
        }
        this.modifidLink.addAll(link);
    }
}
