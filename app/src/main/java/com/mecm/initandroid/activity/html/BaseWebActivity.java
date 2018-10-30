package com.mecm.initandroid.activity.html;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.mecm.initandroid.R;
import com.mecm.initandroid.utils.SwmLogUtils;
import com.mecm.initandroid.utils.SwmToastUtils;
import com.mecm.moneybag.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseWebActivity extends BaseActivity {

    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    private String mUrl;
    private String mParams;

    @SuppressLint({"WrongConstant", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mLinearLayout = (LinearLayout) this.findViewById(R.id.mweb_layout);


        SwmLogUtils.INSTANCE.e("base web view");
        mUrl = getIntent().getStringExtra("url");
        mParams = getIntent().getStringExtra("params");
        if (mParams.contains("\\")) {
            mParams = mParams.replace("\\", "");
        }
//        mParams = UrlUtil.getURLEncoderString(mParams);
        mUrl = mUrl + "?data=" + mParams;

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mWebChromeClient)
                .createAgentWeb()
                .ready()
                .go(mUrl);

        WebSettings webSettings = mAgentWeb.getAgentWebSettings().getWebSettings();
        saveData(webSettings);
//        webSettings.setSupportZoom(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
////                mWebSettings.defaultTextEncodingName = "utf-8"
////                mWebSettings.loadsImagesAutomatically = true
//        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setAppCacheEnabled(false);
//        webSettings.setCacheMode(2);

    }

    UrlChangeListener urlChangeListener;

    interface UrlChangeListener {
        void onLoad(WebView view, String url);
    }

    /**
     * HTML5数据存储
     */
    private void saveData(WebSettings mWebSettings) {
        mWebSettings.setDomStorageEnabled(true);  // 开启 DOM storage 功能
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();

        mWebSettings.setAppCachePath(appCachePath);
        mWebSettings.setAllowFileAccess(true);    // 可以读取文件缓存
        mWebSettings.setAllowFileAccess(true);   // 可以读取文件缓存

    }

    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            SwmLogUtils.INSTANCE.e(url);
            if ("http://www.baidu.com/".equals(url)) {
                finish();
                return;
            } else {
                String[] split = url.split("=");
                if (split.length > 0) {
                    if ("http://47.105.120.229/art/insertCurr?data".equals(split[0])) {
//                    if ("http://192.168.1.158:801/html/insertCurr?data".equals(split[0])) {
                        try {
                            String msg = split[1];
                            String replaceAll = msg.replaceAll("%22", "\"");
                            JSONObject jsonObject = new JSONObject(replaceAll);
                            String companyId = jsonObject.getString("companyId");
                            mAgentWeb.getAgentWebSettings().getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                            if (!mAgentWeb.back()) {
                                finish();
                            }

                        } catch (JSONException e) {
                            SwmToastUtils.INSTANCE.showToast(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (urlChangeListener != null) {
                urlChangeListener.onLoad(view, url);
            }

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted");
        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {

    };

    public String getUrl() {
        return "";
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Info", "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }
}
