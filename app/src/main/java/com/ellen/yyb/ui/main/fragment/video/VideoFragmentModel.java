package com.ellen.yyb.ui.main.fragment.video;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class VideoFragmentModel implements VideoFragmentAgree.VideoFragmentAgreeModel {

    /**
     * 网络视频的联网地址
     */
    public static  final  String VIDEO_URL = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    @Override
    public void getVideoJson(Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(VIDEO_URL).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
