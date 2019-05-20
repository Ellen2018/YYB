package com.ellen.yyb.ui.main.fragment.news;

import android.util.Log;

import com.ellen.yyb.helper.okhttp.okhttpclient.AutoOkHttpClient;
import com.ellen.yyb.helper.okhttp.request.AutoRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragmentModel implements NewsFragmentAgree.NewsFragmentAgreeModel {

    //新闻的标题
    private String newTitleUrl = "http://m.news.cntv.cn/special/json/fl626/index.json";

    @Override
    public void  getNewsTitle(Callback callback){
        OkHttpClient okHttpClient = new AutoOkHttpClient.Builder(null).build().getOkHttpClient();
        Request request = new AutoRequest.Builder().url(newTitleUrl).build().createGetRequest();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
