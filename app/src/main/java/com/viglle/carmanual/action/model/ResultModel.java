package com.viglle.carmanual.action.model;

import com.viglle.carmanual.event.BaseEventModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ResultModel implements Serializable{
    private List<BaseEventModel> onOkList=null;
    public static final String ON_OK="onOk";

    private List<BaseEventModel> onFailList=null;
    public static final String ON_FAIL="onFail";


    public List<BaseEventModel> getOnOkList() {
        return onOkList;
    }

    public void setOnOkList(List<BaseEventModel> onOkList) {
        this.onOkList = onOkList;
    }

    public List<BaseEventModel> getOnFailList() {
        return onFailList;
    }

    public void setOnFailList(List<BaseEventModel> onFailList) {
        this.onFailList = onFailList;
    }
}
