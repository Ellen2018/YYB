package com.ellen.yyb.helper.okhttp.okhttpclient;

import android.content.Context;

import com.ellen.yyb.helper.okhttp.https.HttpsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 *
 * OkHttpClient生产类
 * 1.普通的OkHttpClient
 * 2.支持Https的OkHttpClient
 *
 */
public class AutoOkHttpClient extends OkHttpClientInterface{

    public AutoOkHttpClient(Context context){
        this.context = context;
        mBuilder = new OkHttpClient.Builder();
        connectionTimeOut = CONNECTION_TIME_OUT;
        callTimeOut = CALL_TIME_OUT;
        readTimeOut = READ_TIME_OUT;
    }

    @Override
    public OkHttpClient getOkHttpClient(){
        //设置超时
        mBuilder.connectTimeout(connectionTimeOut,TimeUnit.SECONDS);
        //mBuilder.callTimeout(callTimeOut,TimeUnit.SECONDS);
        mBuilder.readTimeout(readTimeOut,TimeUnit.SECONDS);
        //设置拦截器
        if(interceptor != null){
            mBuilder.addInterceptor(interceptor);
        }
        mOkhttpClient = mBuilder.build();
        return mOkhttpClient;
    }

    @Override
    protected OkHttpClient getHttpsOkHttpClient(HttpsErrorCallback httpsErrorCallback) {
        //设置超时
        mBuilder.connectTimeout(connectionTimeOut,TimeUnit.SECONDS);
        //mBuilder.callTimeout(callTimeOut,TimeUnit.SECONDS);
        mBuilder.readTimeout(readTimeOut,TimeUnit.SECONDS);
        //设置拦截器
        if(interceptor != null){
            mBuilder.addInterceptor(interceptor);
        }
        //设置Https支持
        if(httpsPath != null && httpsPath.length() > 0){
            try {
                mBuilder.sslSocketFactory(HttpsUtils.getSSLSocketFactory(context.getAssets().open(httpsPath)));
            } catch (IOException e) {
                httpsErrorCallback.error(e);
            }
        }
        mOkhttpClient = mBuilder.build();
        return mOkhttpClient;
    }


    public static class Builder{

        private int connectionTimeOut;
        private int callTimeOut;
        private int readTimeOut;
        private Interceptor interceptor;
        private String assetsCaPath;
        private Context context;

        public Builder(Context context){
            //先设置超时的默认值
            connectionTimeOut = CONNECTION_TIME_OUT;
            callTimeOut = CALL_TIME_OUT;
            readTimeOut = READ_TIME_OUT;
            this.context = context;
        }

        public Builder connectionTimeOut(int connectionTimeOut){
            this.connectionTimeOut = connectionTimeOut;
            return this;
        }

        public Builder callTimeOut(int callTimeOut){
            this.callTimeOut = callTimeOut;
            return this;
        }

        public Builder readTimeOut(int readTimeOut){
           this.readTimeOut = readTimeOut;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor){
            this.interceptor = interceptor;
            return this;
        }

        public Builder setHttpsCaPath(String caPath){
            this.assetsCaPath = caPath;
            return this;
        }

        public AutoOkHttpClient build(){
            AutoOkHttpClient autoOkHttpClient = new AutoOkHttpClient(context);
            autoOkHttpClient.setConnectionTimeOut(connectionTimeOut);
            autoOkHttpClient.setCallTimeOut(callTimeOut);
            autoOkHttpClient.setReadTimeOut(readTimeOut);
            autoOkHttpClient.setInterceptor(interceptor);
            autoOkHttpClient.setHttpsPath(assetsCaPath);
            return autoOkHttpClient;
        }

    }

}
