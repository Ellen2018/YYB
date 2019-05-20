package com.ellen.yyb.helper.okhttp.request;

import okhttp3.Request;

/**
 * Request生产类:
 * 1.Get请求的Response
 * 2.Post请求的Response
 * 3.上传文件的Response
 */
public class AutoRequest extends RequestInterface{

    @Override
    public Request createGetRequest() {
        Request.Builder mRequestBuilder = new Request.Builder();
        mRequestBuilder.url(getUrlBuilder(url,requestUrlParams).toString())
                       .headers(getHeaders(requestHeaderParams))
                       .get();
        return mRequestBuilder.build();
    }

    @Override
    public Request createPostRequest() {
        Request.Builder mRequestBuilder = new Request.Builder();
        mRequestBuilder.url(url)
                .headers(getHeaders(requestHeaderParams))
                .post(getFromBody(requestUrlParams));
        return  mRequestBuilder.build();
    }

    @Override
    public Request createMultiPostRequest() {
        Request.Builder mRequestBuilder = new Request.Builder();
        mRequestBuilder.url(url).post(getMultipatrBody(requestUrlParams));
        return mRequestBuilder.build();
    }

    public static class Builder{

        //请求地址
        private String url;
        //请求参数列表
        private RequestParams requestFormParams;
        //请求体头部参数列表
        private RequestParams requestHeaderParams;

        public Builder url(String url){
            this.url = url;
            return this;
        }

        public Builder setRequestFormParams(RequestParams requestFormParams){
            this.requestFormParams = requestFormParams;
            return this;
        }

        public Builder setRequestHeaderParams(RequestParams requestHeaderParams){
            this.requestHeaderParams = requestHeaderParams;
            return this;
        }

        public AutoRequest build(){
            AutoRequest autoRequest = new AutoRequest();
            autoRequest.setUrl(url);
            autoRequest.setRequestFromParams(requestFormParams);
            autoRequest.setRequestHeaderParams(requestHeaderParams);
            return autoRequest;
        }
    }

}
