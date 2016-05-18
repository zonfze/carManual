package com.viglle.carmanual.modules.fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.viglle.carmanual.factory.ViewFactory;
import com.viglle.carmanual.utils.AppUtil;
import com.viglle.carmanual.utils.LogUtil;
import com.viglle.carmanual.widget.VgWebView;

/**
 * Created by Administrator on 2016/5/18.
 */
public class WebViewFragment extends BaseFragment{
    VgWebView mVwebView;
    LinearLayout rootLayout;

    boolean isActive=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootLayout=new LinearLayout(getContext());
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootLayout.setLayoutParams(params);
        if(Build.VERSION.SDK_INT>=19) {
            View view = new View(getContext());
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    AppUtil.calHeight(getActivity(), ViewFactory.STATUS_BAR_HEIGHT)));
            rootLayout.addView(view);
//            rootLayout.setPadding(0, AppUtil.calHeight(getActivity(), ViewFactory.STATUS_BAR_HEIGHT),0,0);
        }
        mVwebView=new VgWebView(getContext());
        rootLayout.addView(mVwebView);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams paramsWeb=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mVwebView.setLayoutParams(paramsWeb);
        mVwebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mVwebView.getSettings().setJavaScriptEnabled(true);
        mVwebView.getSettings().setSupportZoom(true);
        mVwebView.getSettings().setBuiltInZoomControls(true);
        mVwebView.getSettings().setAllowFileAccess(true);//设置启用或禁止访问文件数据
        mVwebView.getSettings().setSupportMultipleWindows(true);
        mVwebView.getSettings().setUseWideViewPort(true);
//        mVwebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mVwebView.getSettings().setLoadWithOverviewMode(true);

        mVwebView.setWebViewClient(new MyWebViewClient());
        mVwebView.setWebChromeClient(new MyWebChromeClient());
//        setBackForward();//设置记录历史，按物理"返回"键回退到上一页
        mVwebView.loadUrl("http://www.baidu.com");
        return rootLayout;
    }

    private void setBackForward(){
        mVwebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction()==KeyEvent.ACTION_DOWN){
                    if(keyCode==KeyEvent.KEYCODE_BACK){
                        if(isActive&&mVwebView!=null&&mVwebView.canGoBack()){
                            mVwebView.goBack();
                            return true;
                        }else{
                            return getActivity().onKeyDown(keyCode,event);
                        }
                    }
                }
                return  false;
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LogUtil.log_e("visible",isVisibleToUser+"");
        if(isVisibleToUser){
            isActive=true;
        }else{
            isActive=false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mVwebView!=null){
            mVwebView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mVwebView!=null){
            mVwebView.destroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mVwebView!=null){
            mVwebView.onResume();
        }
    }

    class MyWebChromeClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        @Override
        public View getVideoLoadingProgressView() {
            return super.getVideoLoadingProgressView();
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
        }

        @Override
        public void onPermissionRequestCanceled(PermissionRequest request) {
            super.onPermissionRequestCanceled(request);
        }

        @Override
        public void getVisitedHistory(ValueCallback<String[]> callback) {
            super.getVisitedHistory(callback);
        }

        public void openFileChooser(ValueCallback<Uri> uploadMsg){
            openFileChooser(uploadMsg, "");
        }
        public void openFileChooser(ValueCallback<Uri> uploadMsg,String acceptType){

        }

        //For android 4.1+
        public void openFileChooser(ValueCallback<Uri> uploadMsg,String acceptType,String capture){

        }


        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

            if(Build.VERSION.SDK_INT>=21){
                LogUtil.log_e("getAcceptTypes" + fileChooserParams.getAcceptTypes());
                LogUtil.log_e("isCaptureEnabled" +  fileChooserParams.isCaptureEnabled());
                LogUtil.log_e("getFilenameHint" +  fileChooserParams.getFilenameHint());
                LogUtil.log_e("getTitle" +  fileChooserParams.getTitle());
            }else{

            }

            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }

        @Override
        public void onCloseWindow(WebView window) {
            super.onCloseWindow(window);
        }

        @Override
        public void onRequestFocus(WebView view) {
            super.onRequestFocus(view);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//            WebView childView = new WebView(getActivity());
//            final WebSettings settings = childView.getSettings();
//            settings.setJavaScriptEnabled(true);
//            childView.setWebChromeClient(this);
//            childView.getSettings().setJavaScriptEnabled(true);
//            childView.getSettings().setSupportZoom(true);
//             childView.getSettings().setAllowFileAccess(true);//设置启用或禁止访问文件数据
//            childView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
//            transport.setWebView(childView);
//            resultMsg.sendToTarget();
//            mHadOnCreateWindow = true;
//            return true;
            return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
        }
    }


}
