package com.ellen.yyb.helper.okhttp.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class RequestInterface {

    //请求地址
    protected String url;
    //请求参数列表
    protected RequestParams requestUrlParams;
    //请求体头部参数列表
    protected RequestParams requestHeaderParams;

    /**
     * 文件上传请求
     *
     * @return
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestParams getRequestFromParams() {
        return requestUrlParams;
    }

    public void setRequestFromParams(RequestParams requestFromParams) {
        this.requestUrlParams = requestFromParams;
    }

    public RequestParams getRequestHeaderParams() {
        return requestHeaderParams;
    }

    public void setRequestHeaderParams(RequestParams requestHeaderParams) {
        this.requestHeaderParams = requestHeaderParams;
    }

    //处理Get请求时url的拼接
    public StringBuilder getUrlBuilder(String url,RequestParams requestParams){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (requestParams != null) {
            for (Map.Entry<String, String> entry : requestParams.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        urlBuilder.delete(urlBuilder.length()-1,urlBuilder.length());
        return urlBuilder;
    }

    //处理请求头Head设置
    public Headers getHeaders(RequestParams requestParams){
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (requestParams != null) {
            for (Map.Entry<String, String> entry : requestParams.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        return mHeader;
    }

    //处理Post请求时请求参数
    public FormBody getFromBody(RequestParams requestParams){
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (requestParams != null) {
            for (Map.Entry<String, String> entry : requestParams.urlParams.entrySet()) {
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        return mFormBodyBuild.build();
    }

    //处理文件上传时的请求参数
    public MultipartBody getMultipatrBody(RequestParams requestParams){
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        if (requestParams != null) {

            for (Map.Entry<String, Object> entry : requestParams.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    multipartBodyBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {
                    multipartBodyBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return multipartBodyBuilder.build();
    }

    abstract Request createGetRequest();
    abstract Request createPostRequest();
    abstract Request createMultiPostRequest();

}
