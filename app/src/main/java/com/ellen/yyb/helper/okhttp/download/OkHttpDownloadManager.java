package com.ellen.yyb.helper.okhttp.download;

import android.content.Context;

import com.ellen.yyb.helper.okhttp.okhttpclient.AutoOkHttpClient;
import com.ellen.yyb.helper.okhttp.request.AutoRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * OkHttp下载文件管理类
 * 1.支持多线程下载
 * 2.支持断点续传
 */
public class OkHttpDownloadManager {

    private Context context;

    public OkHttpDownloadManager(Context context){
        this.context = context;
    }

    /**
     *
     * @param url 下载文件的地址
     * @param fatherPath 存储的父目录
     * @param fileName 文件名
     * @param downloadCallback 下载回调
     */
    public void download(String url, final String fatherPath, final String fileName, final DownloadCallback downloadCallback){

        //1.创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new AutoOkHttpClient.Builder(context).build().getOkHttpClient();
        //2.创建一个Response对象
        Request request = new AutoRequest.Builder().url(url).build().createPostRequest();
        //3.进行异步请求
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                downloadCallback.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                File fatherFile = new File(fatherPath);
                if(!fatherFile.exists()){
                    fatherFile.mkdirs();
                }
                File tagetFile = new File(fatherFile,fileName);

                //进行文件的存储
                try {

                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(tagetFile);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        downloadCallback.onDownloading(progress);
                    }
                    fos.flush();
                    //下载完成
                    downloadCallback.onDownloadSuccess(tagetFile);
                } catch (Exception e) {
                    //下载出现异常
                    downloadCallback.onDownloadFailed(e);
                }finally {

                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {

                    }

                }

            }
        });

   }

}
