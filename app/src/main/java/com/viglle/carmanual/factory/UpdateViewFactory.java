package com.viglle.carmanual.factory;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.viglle.carmanual.action.model.BaseActionModel;
import com.viglle.carmanual.action.model.BaseFModel;
import com.viglle.carmanual.action.model.FType;
import com.viglle.carmanual.seletor.SelectorFactory;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.widget.entity.ViewTreeBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 */
public class UpdateViewFactory {

    public static void modifyViews(Context ctx,ViewTreeBean viewTreeBean,BaseActionModel actionModel){
        List<BaseFModel> fModels = actionModel.getModifidLink();
        if(fModels==null||fModels.isEmpty()){
            return;
        }
        for (int i=0;i<fModels.size();i++)
        {
            BaseFModel model=fModels.get(i);
            int f_type=model.getF_type();
            if(f_type<0){
                continue;
            }
            modifyView(ctx,f_type,model,viewTreeBean);
        }
    }

    private static void modifyView(Context ctx,int f_type,BaseFModel model,ViewTreeBean viewTreeBean){
        int ref_id=model.getRef_id();
        if(ref_id<0){
            return;
        }
        switch (f_type){
            case FType.LAYOUT_WIDTH:
//                String widthStr=model.getF_value();
//                if(widthStr==null||widthStr.equals("")||widthStr.equalsIgnoreCase("null")){
//                    return;
//                }
//                if (!AppUtil.isNumeric(widthStr)){
//                    return;
//                }
//                viewTreeBean.getViewById(ref_id).getLayoutParams().width=Integer.parseInt(widthStr);
//                viewTreeBean.getViewById(ref_id).getLayoutParams()
                break;
            case FType.LAYOUT_HEIGHT:

                break;
            case FType.BG_COLOR:
                modifyBg(ctx, model, viewTreeBean, ref_id);
                break;
            case FType.VISIBLE:
                modifyVisible(ctx, model, viewTreeBean, ref_id);
                break;
            case FType.TEXT:
                modifyText(ctx,model,viewTreeBean,ref_id);
                break;
            case FType.TEXT_COLOR:
                modifyTextColor(ctx,model,viewTreeBean,ref_id);
                break;
            case FType.TEXT_SIZE:
                modifyTextSize(ctx,model,viewTreeBean,ref_id);
                break;
            case FType.TEXT_TYPEFACE:

                break;
            case FType.TEXT_ALIGN:

                break;
            case FType.SCALE_TYPE:

                break;
            case FType.SRC:

                break;
        }
    }

    private static void modifyVisible(Context ctx, BaseFModel model, ViewTreeBean viewTreeBean, int ref_id) {
        String visibleStr=model.getF_value();
        if(visibleStr==null||visibleStr.equals("")||visibleStr.equalsIgnoreCase("null")){
            return;
        }
        if(!AppUtil.isNumeric(visibleStr)){
            return;
        }
        int visible=Integer.parseInt(visibleStr);
        View view=viewTreeBean.getViewById(ref_id);
        if(view==null){
            return;
        }
        switch (visible){
            case 0:
                view.setVisibility(View.GONE);
                break;
            case 1:
                view.setVisibility(View.VISIBLE);
                break;
            case 2:
                view.setVisibility(View.INVISIBLE);
                break;
        }
    }
    private static void modifyText(Context ctx ,BaseFModel model, ViewTreeBean viewTreeBean, int ref_id) {

        TextView view = (TextView)viewTreeBean.getViewById(ref_id);
        if(view==null){
            return;
        }
        String textStr=model.getF_value();
        if(textStr==null||textStr.equals("")){
            view.setText("");
        }else{
            view.setText(textStr);
        }
    }

    private static void modifyTextColor(Context ctx ,BaseFModel model, ViewTreeBean viewTreeBean, int ref_id) {
        String colorStr=model.getF_value();
        if(colorStr==null||colorStr.equals("")||colorStr.equalsIgnoreCase("null")){
            colorStr="#ff000000";
        }
        if(!AppUtil.isColor(colorStr)){
            colorStr="#ff000000";
        }
        TextView view = (TextView)viewTreeBean.getViewById(ref_id);
        if(view==null){
            return;
        }
        view.setTextColor(Color.parseColor(colorStr));

    }
    private static void modifyTextSize(Context ctx ,BaseFModel model, ViewTreeBean viewTreeBean, int ref_id) {
        String sizeStr=model.getF_value();
        if(sizeStr==null||sizeStr.equals("")||sizeStr.equalsIgnoreCase("null")){
            sizeStr="12";
        }
        if(!AppUtil.isNumeric(sizeStr)){
            sizeStr="12";
        }
        int size=Integer.parseInt(sizeStr);
        TextView view = (TextView)viewTreeBean.getViewById(ref_id);
        if(view==null){
            return;
        }
        view.setTextSize(AppUtil.px2sp(ctx,size));
    }

    private static void modifyBg(Context ctx, BaseFModel model, ViewTreeBean viewTreeBean, int ref_id) {
        String colorStr=model.getF_value();
        if(colorStr==null||colorStr.equals("")||colorStr.equalsIgnoreCase("null")){
            return;
        }
        if(!AppUtil.isColor(colorStr)){
            return;
        }
        View view=viewTreeBean.getViewById(ref_id);
        if(view==null){
            return;
        }
        if(Build.VERSION.SDK_INT>=16){
            view.setBackground(SelectorFactory.getInstance().newSelector(ctx, colorStr));
        }else{
            view.setBackgroundDrawable(SelectorFactory.getInstance().newSelector(ctx, colorStr));
        }
    }
}
