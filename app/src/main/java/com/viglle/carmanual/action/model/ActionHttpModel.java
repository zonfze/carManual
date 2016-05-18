package com.viglle.carmanual.action.model;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ActionHttpModel extends BaseActionModel{
    private String url;
    public static final String URL="url";

//    private List<Integer>ref_ui=new ArrayList<>();
//    public static final String REF_UI="ref_ui";


    private ResultModel result;
    public static final String RESULT="result";


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ResultModel getResult() {
        return result;
    }

    public void setResult(ResultModel result) {
        this.result = result;
    }
}
