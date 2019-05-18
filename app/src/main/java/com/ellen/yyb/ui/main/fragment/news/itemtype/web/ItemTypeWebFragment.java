package com.ellen.yyb.ui.main.fragment.news.itemtype.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class ItemTypeWebFragment extends BaseFragment implements BaseFragment.ButterKnifeInterface {

    private Unbinder unbinder;

    private String url;

    @BindView(R.id.webview_fragment_itme_type_web)
    WebView webView;

    public ItemTypeWebFragment(String url) {
        this.url = url;
    }


    @Override
    protected void initData() {
        laodWebViewUrl(url);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_item_type_web;
    }

    @Override
    public void initButterKnife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void unBindButterKnife() {
        unbinder.unbind();
    }

    private void laodWebViewUrl(String url) {

        WebSettings webSettings = webView.getSettings();

// 支持javascript
        webSettings.setJavaScriptEnabled(true);

// 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);

// 支持数据库
        webSettings.setDatabaseEnabled(true);

// 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = getActivity().getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

// 设置可以支持缩放
        webSettings.setUseWideViewPort(true);

// 扩大比例的缩放
        webSettings.setSupportZoom(true);

        webSettings.setBuiltInZoomControls(true);

// 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);

// 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

// 隐藏滚动条
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);


// 处理网页内的连接（自身打开）
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wvLoadTrueUrl(view, url);
                return true;
            }

            private void wvLoadTrueUrl(WebView webView, String url) {

                String[] strings = new String[]{
                        "StartPlayVideo",
                        "Share",
                        "StartDownloadImage",
                        "FindMoreComment",
                };

                String url1 = url;

                //视频：js2cmd://StartPlayVideo@一串不明所以的id
                //分享：js2cmd:doSinaShare,doWeixinShare,doWeixinCircleShare,doSystemShare
                //Gif:js2cmd:StartDownloadingImage@gif的地址(.gif)
                //查看更多评论：js2cmd.FindMoreComment@评论的id
                //图片：js2cmd:StartDownloadingImage@gif的地址(.jpg)

                if (url.contains(strings[0])) {
                    //ToastUtil.toast(context, "播放视频");
                } else if (url.contains(strings[1])) {

                    if (url.contains("SinaShare")) {

                        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
                        wechatIntent.setPackage("com.sina.weibo");
                        wechatIntent.setType("text/plain");
                        //wechatIntent.putExtra(Intent.EXTRA_TEXT, title+"："+LookNewsActivity.this.url);
                        startActivity(wechatIntent);

                    } else if (url.contains("WeixinShare")) {

                        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
                        wechatIntent.setPackage("com.tencent.mm");
                        wechatIntent.setType("text/plain");
                        // wechatIntent.putExtra(Intent.EXTRA_TEXT, title+"："+LookNewsActivity.this.url);
                        startActivity(wechatIntent);

                    } else if (url.contains("WeixinCircleShare")) {
                        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
                        wechatIntent.setPackage("com.tencent.mm");
                        wechatIntent.setType("text/plain");
                        //wechatIntent.putExtra(Intent.EXTRA_TEXT, title+"："+LookNewsActivity.this.url);
                        startActivity(wechatIntent);
                    } else if (url.contains("SystemShare")) {
                        //share();
                    }

                    return;

                } else if (url.contains(strings[2])) {

                    if (url.endsWith(".jpg")) {
                        // ToastUtil.toast(context, "显示jpg图片");
                        url1 = url.substring(url.indexOf("@") + 1);
                        ;
                    } else if (url.endsWith(".gif")) {
                        // ToastUtil.toast(context, "显示gif动态图");
                        url1 = url.substring(url.indexOf("@") + 1);
                        // ToastUtil.toast(context, url1);
                    } else {


                        url1 = url.substring(url.indexOf("@") + 1);


                    }


                } else if (url.contains(strings[3])) {

                    //ToastUtil.toast(context, "显示评论");

                } else {


                }


                webView.loadUrl(url1);


            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url1 = request.getUrl().toString();

                wvLoadTrueUrl(view, url1);
                return true;
            }
        });


        webView.loadUrl(url);
        // 使用返回键的方式防止网页重定向
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

    }

}
