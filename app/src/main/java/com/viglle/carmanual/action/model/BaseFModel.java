package com.viglle.carmanual.action.model;

import com.viglle.carmanual.utils.AppUtil;

/**
 * Created by Administrator on 2016/5/16.
 */
public abstract class BaseFModel {
    /**
     * 关联的控件的ID {@link com.viglle.carmanual.widget.model.BaseViewModel#VIEW_ID}
     */
    private String ref_id;
    public static final String REF_ID="ref_id";

    /**{@link com.viglle.carmanual.action.model.FType}*/
    private String f_type;//对应的属性的编号

    private String f_value;//属性的值;

    public static final String F_TYPE="f_type";
    public static final String F_VALUE="f_value";

    public int getRef_id() {

        if(ref_id==null||ref_id.equals("")||ref_id.equals("null")){
            return -1;
        }
        if(!AppUtil.isNumeric(ref_id)){
            return -1;
        }
        return Integer.parseInt(ref_id);
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getF_value() {
        return f_value;
    }

    public void setF_value(String f_value) {
        this.f_value = f_value;
    }

    public int getF_type() {
        if(f_type==null||f_type.equals("")||f_type.equals("null")){
            return -1;
        }
        if(!AppUtil.isNumeric(f_type)){
            return -1;
        }
        return Integer.parseInt(f_type);
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }
}
