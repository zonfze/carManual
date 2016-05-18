package com.viglle.carmanual.factory;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viglle.carmanual.event.BaseEventModel;
import com.viglle.carmanual.seletor.SelectorFactory;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.ImageHelper;
import com.viglle.carmanual.widget.VgButton;
import com.viglle.carmanual.widget.VgCheckBox;
import com.viglle.carmanual.widget.VgImageView;
import com.viglle.carmanual.widget.VgRadioButton;
import com.viglle.carmanual.widget.VgSwitchView;
import com.viglle.carmanual.widget.VgTextField;
import com.viglle.carmanual.widget.VgTextView;
import com.viglle.carmanual.widget.entity.ViewTreeBean;
import com.viglle.carmanual.widget.layout.VgBottomNavLayout;
import com.viglle.carmanual.widget.layout.VgBottomNavPupopLayout;
import com.viglle.carmanual.widget.layout.VgContentLayout;
import com.viglle.carmanual.widget.layout.VgTopActionBar;
import com.viglle.carmanual.widget.layout.VgViewPager;
import com.viglle.carmanual.widget.model.BaseViewModel;
import com.viglle.carmanual.widget.model.VgBottomNavModel;
import com.viglle.carmanual.widget.model.VgBottomNavPupopLayoutModel;
import com.viglle.carmanual.widget.model.VgButtonModel;
import com.viglle.carmanual.widget.model.VgCheckBoxModel;
import com.viglle.carmanual.widget.model.VgContentLayoutModel;
import com.viglle.carmanual.widget.model.VgImageViewModel;
import com.viglle.carmanual.widget.model.VgRadioButtonModel;
import com.viglle.carmanual.widget.model.VgSwitchViewModel;
import com.viglle.carmanual.widget.model.VgTextFieldModel;
import com.viglle.carmanual.widget.model.VgTextViewModel;
import com.viglle.carmanual.widget.model.VgTopActionBarModel;
import com.viglle.carmanual.widget.model.VgViewPagerModel;
import com.viglle.carmanual.widget.model.VgViewType;

/**
 * Created by Administrator on 2016/4/15.
 */
public class ViewFactory {
    public static final int DefaultWidth=1080;//约定宽按三倍屏尺寸
    public static final int DefaultHeight=1920;//约定高按三倍屏尺寸
//    private static ViewFactory instance=null;
//    public static ViewFactory getInstance(){
//        if(instance==null){
//            instance=new ViewFactory();
//        }
//        return instance;
//    }

    public static View createViewTree(Context ctx,BaseViewModel modelTree,ViewTreeBean viewTreeBean){
        if(modelTree==null){
            VgContentLayout vgContentLayout= new VgContentLayout(ctx);
            vgContentLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return vgContentLayout;
        }
        int view_type=modelTree.getView_type();
        switch (view_type){
            case VgViewType.VgContentLayout:
                VgContentLayout vgContentLayout=createVgContentLayout(ctx,(VgContentLayoutModel)modelTree,viewTreeBean);
                for(int i=0;i<modelTree.getChilds().size();i++){
                    vgContentLayout.addView(createViewTree(ctx,modelTree.getChilds().get(i),viewTreeBean));
                }
                return vgContentLayout;
            case VgViewType.VgTextField:
                return createVgTextField(ctx,(VgTextFieldModel)modelTree,viewTreeBean);
            case VgViewType.VgTextView:
                return createVgTextView(ctx, (VgTextViewModel) modelTree, viewTreeBean);
            case VgViewType.VgButton:
                return createVgButton(ctx, (VgButtonModel) modelTree, viewTreeBean);
            case VgViewType.VgImageView:
                return createVgImageView(ctx, (VgImageViewModel) modelTree, viewTreeBean);
            case VgViewType.VgCheckBox:
                return createVgCheckBox(ctx,(VgCheckBoxModel)modelTree,viewTreeBean);
            case VgViewType.VgSwitchView:
                return createVgSwitchView(ctx,(VgSwitchViewModel)modelTree,viewTreeBean);
            case VgViewType.VgRadioButton:
                return createVgRadioButton(ctx,(VgRadioButtonModel)modelTree,viewTreeBean);
            case VgViewType.VgListView:

                return null;
            case VgViewType.VgScrollView:

                return null;
            case VgViewType.VgHorizentalScrollView:

                return null;
            case VgViewType.VgLeftNavLayout:

                return null;
            case VgViewType.VgTopNavLayout:

                return null;
            case VgViewType.VgRightNavLayout:

                return null;
            case VgViewType.VgBottomNavLayout:
                return createBottomNavLayout(ctx,(VgBottomNavModel)modelTree,viewTreeBean);
            case VgViewType.VgBottomNavPupopLayout:
                return createBottomNavPupLayout(ctx,(VgBottomNavPupopLayoutModel)modelTree,viewTreeBean);
            case VgViewType.VgTopActionBar:
                VgTopActionBar vgTopActionBar=createVgTopActionBar(ctx, (VgTopActionBarModel) modelTree, viewTreeBean);
                for(int i=0;i<modelTree.getChilds().size();i++){
                    vgTopActionBar.addView(createViewTree(ctx,modelTree.getChilds().get(i),viewTreeBean));
                }
                return vgTopActionBar;
            case VgViewType.VgViewPager:
                return createVgViewPager(ctx, (VgViewPagerModel) modelTree, viewTreeBean);
        }




        return null;
    }



    public static VgBottomNavLayout createBottomNavLayout(Context ctx,VgBottomNavModel model,ViewTreeBean modelMap){
        VgBottomNavLayout bottomNavLayout=new VgBottomNavLayout(ctx);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        model.setVgView(bottomNavLayout);

        configCommonParams(ctx,model,bottomNavLayout,params);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomNavLayout.setDatas(model.getDatas());
        bottomNavLayout.setLayoutParams(params);
        bottomNavLayout.setId(model.getView_id());
        modelMap.put(model);
        return bottomNavLayout;
    }

    public static VgBottomNavPupopLayout createBottomNavPupLayout(Context ctx,VgBottomNavPupopLayoutModel model,ViewTreeBean modelMap){
        VgBottomNavPupopLayout bottomNavPupLayout=new VgBottomNavPupopLayout(ctx);
        bottomNavPupLayout.setId(model.getView_id());
        model.setVgView(bottomNavPupLayout);
        modelMap.put(model);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        configCommonParams(ctx,model,bottomNavPupLayout,params);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomNavPupLayout.setDatas(model.getDatas());
        bottomNavPupLayout.setLayoutParams(params);


//        createEvent(ctx,bottomNavLayout,model);
        return bottomNavPupLayout;
    }

    public static VgViewPager createVgViewPager(Context ctx,VgViewPagerModel model,ViewTreeBean modelMap){
        VgViewPager vgViewPager=new VgViewPager(ctx);
        RelativeLayout.LayoutParams params= setWidthAndHeight(ctx,model.getView_width(), model.getView_height());

        model.setVgView(vgViewPager);
        vgViewPager.setId(model.getView_id());
        vgViewPager.setNoScroll(model.noNoScroll());
//        setVgViewOf(model, params);
//        setVgViewMargins(ctx, params, model);//设置外边距
//        setVgViewPaddings(ctx, vgViewPager, model);//设置内边距
//        setVgViewBackground(ctx, model, vgViewPager);//设置背景样式
        configCommonParams(ctx,model,vgViewPager,params);
        vgViewPager.setLayoutParams(params);

        modelMap.put(model);
        return vgViewPager;
    }

    private static void createEvent(Context ctx,ViewTreeBean viewTreeBean,BaseEventModel model){
        EventFactory.createEvent(ctx,viewTreeBean,model);
    }

    public static VgContentLayout createVgContentLayout(Context ctx,VgContentLayoutModel model,ViewTreeBean modelMap){
        VgContentLayout rootLayout=new VgContentLayout(ctx);

        RelativeLayout.LayoutParams params= setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
//        setVgViewOf(model, params);
//        setVgViewMargins(ctx, params, model);//设置外边距
//        setVgViewPaddings(ctx, rootLayout, model);//设置内边距
//        setVgViewBackground(ctx, model, rootLayout);//设置背景样式
        configCommonParams(ctx,model,rootLayout,params);
        rootLayout.setLayoutParams(params);
        rootLayout.setId(model.getView_id());
        model.setVgView(rootLayout);
        modelMap.put(model);
//        createEvent(ctx, rootLayout, model);
        return rootLayout;
    }

    public static final int STATUS_BAR_HEIGHT=25*3;

    public static VgTopActionBar createVgTopActionBar(Context ctx,VgTopActionBarModel model,ViewTreeBean modelMap){

        VgTopActionBar topActionBar=new VgTopActionBar(ctx);
        RelativeLayout.LayoutParams params= null;//setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
        int padd[];
        if(Build.VERSION.SDK_INT>=19){
            params= setWidthAndHeight(ctx, model.getView_width(), model.getView_height() + STATUS_BAR_HEIGHT);
            padd = model.getView_paddings();
            if(padd!=null&&padd.length==4){
                padd[1]=padd[1]+STATUS_BAR_HEIGHT;
            }
        }else{
            params= setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
        }
//        setVgViewPaddings(ctx, rootLayout, model);//设置内边距
//        setVgViewOf(model, params);
//        setVgViewMargins(ctx, params, model);//设置外边距
//
//        setVgViewBackground(ctx, model, rootLayout);//设置背景样式
        configCommonParams(ctx,model,topActionBar,params);
        topActionBar.setLayoutParams(params);
        topActionBar.setId(model.getView_id());
        model.setVgView(topActionBar);
        modelMap.put(model);
        return topActionBar;
    }

    public static VgButton createVgButton(Context ctx,VgButtonModel model,ViewTreeBean modelMap){
        VgButton btn=new VgButton(ctx);
        model.setVgView(btn);
//        int width= AppUtil.dip2px(ctx,model.getView_width());
//        int height= AppUtil.dip2px(ctx,model.getView_height());
        RelativeLayout.LayoutParams params= setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
//        setVgViewOf(model, params);
//        setVgViewMargins(ctx, params, model);//设置外边距
//        setVgViewPaddings(ctx, btn, model);//设置内边距
//        setVgViewBackground(ctx, model, btn);//设置背景样式
        configCommonParams(ctx,model,btn,params);
        btn.setTextSize(getTextSize(ctx, model.getText_size()));
        setTextAlign(btn, model.getText_align());
        btn.setTextColor(Color.parseColor(model.getText_color()));
        btn.setText(model.getText());
//        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        btn.setLayoutParams(params);
//        btn.setGravity(Gravity.CENTER);
        btn.setId(model.getView_id());

        modelMap.put(model);
//        createEvent(ctx, btn, model);
//        setVgButtonClick(ctx, model, modelMap);
        return btn;
    }

    public static VgCheckBox createVgCheckBox(Context ctx,VgCheckBoxModel model,ViewTreeBean modelMap){
        VgCheckBox checkBox=new VgCheckBox(ctx);
        model.setVgView(checkBox);

        RelativeLayout.LayoutParams params = setWidthAndHeight(ctx,model.getView_width(),model.getView_height());
//        setVgViewOf(model, params);
//        setVgViewBackground(ctx, model, checkBox);
//        setVgViewMargins(ctx, params, model);
//        setVgViewPaddings(ctx, checkBox, model);
        configCommonParams(ctx,model,checkBox,params);
        setTextAlign(checkBox, model.getText_align());
        checkBox.setTextSize(getTextSize(ctx, model.getText_size()));
        checkBox.setTextColor(Color.parseColor(model.getText_color()));
        checkBox.setText(model.getText());
        checkBox.setId(model.getView_id());
        checkBox.setChecked(model.isChecked());
        checkBox.setLayoutParams(params);
        modelMap.put(model);
        return checkBox;
    }

    public static VgRadioButton createVgRadioButton(Context ctx,VgRadioButtonModel model,ViewTreeBean modelMap){
        VgRadioButton vgRadioButton=new VgRadioButton(ctx);
        model.setVgView(vgRadioButton);

        RelativeLayout.LayoutParams params = setWidthAndHeight(ctx,model.getView_width(),model.getView_height());
//        setVgViewOf(model, params);
//        setVgViewBackground(ctx, model, vgRadioButton);
//        setVgViewMargins(ctx, params, model);
//        setVgViewPaddings(ctx, vgRadioButton, model);
        configCommonParams(ctx,model,vgRadioButton,params);
        setTextAlign(vgRadioButton, model.getText_align());
        vgRadioButton.setTextSize(getTextSize(ctx, model.getText_size()));
        vgRadioButton.setTextColor(Color.parseColor(model.getText_color()));
        vgRadioButton.setText(model.getText());
        vgRadioButton.setChecked(model.isChecked());
        vgRadioButton.setId(model.getView_id());
        vgRadioButton.setLayoutParams(params);
        modelMap.put(model);
        return vgRadioButton;
    }

    public static VgSwitchView createVgSwitchView(Context ctx,VgSwitchViewModel model,ViewTreeBean modelMap){
        VgSwitchView switchView=new VgSwitchView(ctx);
        model.setVgView(switchView);

        RelativeLayout.LayoutParams params = setWidthAndHeight(ctx,model.getView_width(),model.getView_height());
//        setVgViewOf(model, params);
//        setVgViewBackground(ctx, model, switchView);
//        setVgViewMargins(ctx, params, model);
//        setVgViewPaddings(ctx, switchView, model);
        configCommonParams(ctx,model,switchView,params);
        switchView.setId(model.getView_id());
        switchView.setState(model.isChecked());
        switchView.setLayoutParams(params);
        modelMap.put(model);
        return switchView;
    }

    private static int getTextSize(Context ctx, int text_size) {
        return AppUtil.px2sp(ctx, text_size);
    }

    public static VgTextField createVgTextField(Context ctx,VgTextFieldModel model,ViewTreeBean modelMap){
        VgTextField btn=new VgTextField(ctx);
//        int width= AppUtil.dip2px(ctx,model.getView_width());
//        int height= AppUtil.dip2px(ctx,model.getView_height());
        model.setVgView(btn);
        RelativeLayout.LayoutParams params= setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
        configCommonParams(ctx, model, btn, params);

        btn.setText(model.getText());
        btn.setTextSize(getTextSize(ctx, model.getText_size()));
        if(model.getPassword()==0){//以密文的形式显示
            btn.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{//明文的形式显示
            btn.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }

        setTextAlign(btn,model.getText_align());

        btn.setTextColor(Color.parseColor(model.getText_color()));
        btn.setHint(model.getHint());
        btn.setLayoutParams(params);
        btn.setId(model.getView_id());

        modelMap.put( model);
//        createEvent(ctx, btn, model);
        return btn;
    }


    private static void setVgViewVisible(View view, int visible){
        if(view==null){
            return;
        }
        switch (visible){
            case 0://不可见且不占用屏幕空间
                view.setVisibility(View.GONE);
                break;
            case 1://可见
                view.setVisibility(View.VISIBLE);
                break;
            case 2://不可见但占用屏幕空间
                view.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private static void setTextAlign(TextView btn,int align){
       if(align==1){//居左对齐
            btn.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        }else if(align==2){//居右对齐
           btn.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
        }else{//居中对齐
           btn.setGravity(Gravity.CENTER);
       }
    }


    public static VgTextView createVgTextView(Context ctx,VgTextViewModel model,ViewTreeBean modelMap){
        VgTextView btn=new VgTextView(ctx);
        model.setVgView(btn);
//        int width= AppUtil.dip2px(ctx,model.getView_width());
//        int height= AppUtil.dip2px(ctx,model.getView_height());
        RelativeLayout.LayoutParams params= setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
//        setVgViewOf(model, params);
//        setVgViewMargins(ctx, params, model);//设置外边距
//        setVgViewPaddings(ctx, btn, model);//设置内边距
//        setVgViewBackground(ctx, model, btn);//设置背景样式
        configCommonParams(ctx,model,btn,params);
        btn.setText(model.getText());
        btn.setTextSize(getTextSize(ctx, model.getText_size()));
        btn.setTextColor(Color.parseColor(model.getText_color()));
        setTextAlign(btn, model.getText_align());
        btn.setLayoutParams(params);
        btn.setId(model.getView_id());
        modelMap.put(model);
//        createEvent(ctx, btn, model);
        return btn;
    }

    public static VgImageView createVgImageView(Context ctx,VgImageViewModel model,ViewTreeBean modelMap){
        VgImageView imageView = new VgImageView(ctx);
        model.setVgView(imageView);
        RelativeLayout.LayoutParams params = setWidthAndHeight(ctx,model.getView_width(), model.getView_height());
        //setVgViewOf(model, params);
        //setVgViewMargins(ctx, params, model);
       // setVgViewPaddings(ctx, imageView, model);
       // setVgViewBackground(ctx, model, imageView);
        configCommonParams(ctx,model,imageView,params);
        imageView.setLayoutParams(params);

        int scaleType=model.getScaleType();
        switch (scaleType){
            case 0://按图片大小显示,图片太大将截取
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                break;
            case 1://按控件大小进行平铺
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case 2://不超出控件,如果突破太大,则压缩,有可能会导致图片失真
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
            case 3:

                break;
        }
        ImageHelper.getInstance().displayImage(ctx, model.getSrc(), imageView);
        modelMap.put(model);
//        createEvent(ctx,imageView,model);//设置事件
        return imageView;
    }

    @NonNull
    private static RelativeLayout.LayoutParams setWidthAndHeight(Context ctx,int view_width, int view_height) {
        if(view_width==DefaultWidth&&view_height==DefaultHeight){//当宽=1080;高=1920;代表全屏;所以应该获取手机的屏幕高宽作为实际的高宽
            view_width=AppUtil.getScreenWidth(ctx);
            view_height=AppUtil.getScreenHeight(ctx);
            return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        }else{
            if(view_width==DefaultWidth){//当只有宽=1080时;代表横向填充满布局
                view_width=AppUtil.getScreenWidth(ctx);
                return new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, AppUtil.calHeight(ctx,view_height));
            }
            if(view_height==DefaultHeight){//当只有高=1920时;代表横向填充满布局
                view_height=AppUtil.getScreenHeight(ctx);
                return new RelativeLayout.LayoutParams(AppUtil.calWidth(ctx, view_width), RelativeLayout.LayoutParams.MATCH_PARENT);
            }
            int width=AppUtil.calWidth(ctx, view_width);
            int height=AppUtil.calHeight(ctx,view_height);
            return new RelativeLayout.LayoutParams(width, height);
        }


    }


    private static void setVgViewOf(BaseViewModel model, RelativeLayout.LayoutParams params) {
        if(model.getView_of()==null||model.getView_of().length<1){
            return;
        }
        if(!(model.getView_of()[0]<0)){
            params.addRule(RelativeLayout.RIGHT_OF, model.getView_of()[0]);
        }
        if(!(model.getView_of()[1]<0)){
            params.addRule(RelativeLayout.BELOW, model.getView_of()[1]);
        }

        if(!(model.getView_of()[2]<0)){
            params.addRule(RelativeLayout.LEFT_OF, model.getView_of()[2]);
        }
        if(!(model.getView_of()[3]<0)){
            params.addRule(RelativeLayout.ABOVE, model.getView_of()[3]);
        }
    }

    /**
     * 设置控件背景颜色和点击样式
     * @param ctx
     * @param model
     * @param view
     */
    private  static void setVgViewBackground(Context ctx,BaseViewModel model,View view){
        if(Build.VERSION.SDK_INT>=16){
            view.setBackground(SelectorFactory.getInstance().newSeletor(
                    ctx,
                    model.getBg_normal_color(),
                    model.getBg_focus_color(),
                    model.getBg_focus_color(),
                    model.getBg_normal_color()));
        }else{
            view.setBackgroundDrawable(SelectorFactory.getInstance().newSeletor(ctx,
                    model.getBg_normal_color(),
                    model.getBg_focus_color(),
                    model.getBg_focus_color(),
                    model.getBg_normal_color()));
        }
    }


    private static void setVgViewMargins(Context context, RelativeLayout.LayoutParams params, BaseViewModel model) {
        int view_margins[]=model.getView_margins();
        if(view_margins==null||view_margins.length<1){
            return;
        }
        try{
            params.setMargins(AppUtil.calWidth(context, view_margins[0]),
                    AppUtil.calWidth(context, view_margins[1]),
                    AppUtil.calWidth(context, view_margins[2]),
                    AppUtil.calWidth(context, view_margins[3]));
//            params.setMargins(view_margins[0],view_margins[1],view_margins[2],view_margins[3]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void setVgViewPaddings(Context context, View view, BaseViewModel model) {
        int view_paddings[]=model.getView_paddings();
        if(view_paddings==null||view_paddings.length<1){
            return;
        }
        if(view==null){
            return;
        }
        try{
            view.setPadding(AppUtil.calWidth(context, view_paddings[0]),
                    AppUtil.calWidth(context, view_paddings[1]),
                    AppUtil.calWidth(context, view_paddings[2]),
                    AppUtil.calWidth(context, view_paddings[3]));
//            view.setPadding(view_paddings[0],view_paddings[1],view_paddings[2],view_paddings[3]);
//                    view.setPadding(AppUtil.dip2px(context, view_paddings[0]), AppUtil.dip2px(context, view_paddings[1]), AppUtil.dip2px(context, view_paddings[2]), AppUtil.dip2px(context, view_paddings[3]));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void setVgViewPaddings(Context context, View view, int left,int top,int right,int bottom) {
        if(view==null){
            return;
        }
        try{
            view.setPadding(AppUtil.calWidth(context, left),
                    AppUtil.calWidth(context, top),
                    AppUtil.calWidth(context, right),
                    AppUtil.calWidth(context, bottom));
//            view.setPadding(view_paddings[0],view_paddings[1],view_paddings[2],view_paddings[3]);
//                    view.setPadding(AppUtil.dip2px(context, view_paddings[0]), AppUtil.dip2px(context, view_paddings[1]), AppUtil.dip2px(context, view_paddings[2]), AppUtil.dip2px(context, view_paddings[3]));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private static void configCommonParams(Context ctx, BaseViewModel model, View view, RelativeLayout.LayoutParams params) {
        setVgViewOf(model, params);
        setVgViewMargins(ctx, params, model);//设置外边距
        setVgViewPaddings(ctx, view, model);//设置内边距
        setVgViewBackground(ctx, model, view);//设置背景样式
        setVgViewVisible(view,model.getVisible());
    }
}
