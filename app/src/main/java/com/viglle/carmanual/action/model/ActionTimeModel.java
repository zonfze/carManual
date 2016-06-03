package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ActionTimeModel extends BaseActionModel{
    private long time;//时间(倒计时的时间,毫秒)
    public static final String TIME="time";

//    private String pre;//前缀
//    public static final String PRE="pre";
    private int change_view_id;//被改变的控件的id; -1时表示无控件被改变
    public static final String CHANGEVIEW_ID="change_id";



    public long getTime() {
       return time;
    }

    public void setTime(String time) {
        if(time==null||time.equals("")||time.equals("null")){
            this.time= 0;
        }
        if(!AppUtil.isNumeric(time)){
            this.time= 0;
        }
        this.time= Long.parseLong(time);
    }

    public int getChange_view_id() {

        return change_view_id;
    }

    public void setChange_view_id(String change_view_id) {
        if(change_view_id==null||change_view_id.equals("")||change_view_id.equals("null")){
            this.change_view_id=-1;
        }
        if(!AppUtil.isNumeric(change_view_id)){
            this.change_view_id=-1;
        }
        this.change_view_id=Integer.parseInt(change_view_id);
    }
}
