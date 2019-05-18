package com.ellen.yyb.ui.main.fragment.video;

import com.ellen.yyb.mvp.fragment.BaseFragmentModel;
import com.ellen.yyb.mvp.fragment.BaseFragmentPresenter;
import com.ellen.yyb.mvp.fragment.BaseFragmentView;

public interface VideoFragmentAgree {

    interface VideoFragmentAgreeModel extends BaseFragmentModel {
        void getVideoJson(okhttp3.Callback callback);
    }

    interface VideoFragmentAgreeView extends BaseFragmentView{

        void updateVideoData(String json);
        void requestDataError(String errMessage);

    }

    abstract class VideoFragmentAgreePresenter extends BaseFragmentPresenter<VideoFragmentAgreeModel,VideoFragmentAgreeView>{

        abstract void requestVideoJson();

    }

}
