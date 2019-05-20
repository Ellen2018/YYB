package com.ellen.yyb.ui.main.fragment.video;

import android.content.Context;
import android.util.Log;

import com.ellen.yyb.bean.TrailersBean;
import com.ellen.yyb.bean.Video;
import com.ellen.yyb.helper.GsonHelper;
import com.ellen.yyb.helper.key_value.MMKVHelper;

import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class VideoFragmentModel implements VideoFragmentAgree.VideoFragmentAgreeModel {


    private MMKVHelper videoMMkvHelper;

    public VideoFragmentModel(Context context){
      videoMMkvHelper = new MMKVHelper(this.getClass().getName());
    }

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

    @Override
    public Video getSavedData() {
        String json = (String) videoMMkvHelper.getValue(this.getClass().getName(),null);
        if(json != null){
            Log.e("取出的数据",json);
            Video video = GsonHelper.getGsonInstance().fromJson(json,Video.class);
            return video;
        }
        return null;
    }

    @Override
    public void saveData(Video video) {
          String json = GsonHelper.getGsonInstance().toJson(video);
          videoMMkvHelper.save(this.getClass().getName(),json);
        Log.e("存储的数据",json);
    }

}
