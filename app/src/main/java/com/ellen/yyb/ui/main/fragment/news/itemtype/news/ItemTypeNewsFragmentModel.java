package com.ellen.yyb.ui.main.fragment.news.itemtype.news;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ItemTypeNewsFragmentModel implements ItemTypeNewsFragmentAgree.ItemTypeNewsFragmentAgreeModel {


    @Override
    public void getNewsJson(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
