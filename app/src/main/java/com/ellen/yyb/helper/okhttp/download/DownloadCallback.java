package com.ellen.yyb.helper.okhttp.download;

import java.io.File;

public interface DownloadCallback {

    /**
     * 下载成功之后的文件
     */
    void onDownloadSuccess(File file);

    /**
     * 下载进度
     */
    void onDownloading(int progress);

    /**
     * 下载失败回调
     */
    void onDownloadFailed(Exception e);
}
