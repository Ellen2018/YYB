package com.ellen.yyb.ui.main.fragment.news.itemtype.news;

import android.util.Log;

import com.ellen.yyb.helper.okhttp.okhttpclient.AutoOkHttpClient;
import com.ellen.yyb.helper.okhttp.request.AutoRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ItemTypeNewsFragmentModel implements ItemTypeNewsFragmentAgree.ItemTypeNewsFragmentAgreeModel {


    @Override
    public void getNewsJson(String url, Callback callback) {
        Request request = new AutoRequest.Builder().url(url).build().createGetRequest();
        OkHttpClient okHttpClient = new AutoOkHttpClient.Builder(null).build().getOkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
