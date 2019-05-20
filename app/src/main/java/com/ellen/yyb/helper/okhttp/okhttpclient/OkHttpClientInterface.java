package com.ellen.yyb.helper.okhttp.okhttpclient;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public abstract class OkHttpClientInterface {

    protected OkHttpClient mOkhttpClient;
    protected OkHttpClient.Builder mBuilder;
    protected Context context;

    //连接超时
    protected int connectionTimeOut;
    //请求超时
    protected int callTimeOut;
    //读取超时
    protected int readTimeOut;

    //拦截器
    protected Interceptor interceptor;

    //Https证书目录
    protected String httpsPath;

    //超时默认时长
    protected static final int CONNECTION_TIME_OUT = 30;
    protected static final int CALL_TIME_OUT = 60;
    protected static final int READ_TIME_OUT = 60;


    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public int getCallTimeOut() {
        return callTimeOut;
    }

    public void setCallTimeOut(int callTimeOut) {
        this.callTimeOut = callTimeOut;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getHttpsPath() {
        return httpsPath;
    }

    public void setHttpsPath(String httpsPath) {
        this.httpsPath = httpsPath;
    }

    //获取普通的OkHttpClient对象
    protected abstract OkHttpClient getOkHttpClient();
    //获取对Https支持的OkHttpClient对象
    protected abstract OkHttpClient getHttpsOkHttpClient(HttpsErrorCallback httpsErrorCallback);

    public interface HttpsErrorCallback{
        void error(IOException e);
    }

}
