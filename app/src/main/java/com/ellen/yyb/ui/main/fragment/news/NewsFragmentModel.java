package com.ellen.yyb.ui.main.fragment.news;

import android.os.Message;

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
    public String getNewsTitle() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(newTitleUrl).get().build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

}