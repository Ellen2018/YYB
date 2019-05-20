package com.ellen.yyb.ui.main.fragment.video;

import com.ellen.yyb.bean.TrailersBean;
import com.ellen.yyb.bean.Video;
import com.ellen.yyb.mvp.fragment.BaseFragmentModel;
import com.ellen.yyb.mvp.fragment.BaseFragmentPresenter;
import com.ellen.yyb.mvp.fragment.BaseFragmentView;

import java.util.List;

public interface VideoFragmentAgree {

    interface VideoFragmentAgreeModel extends BaseFragmentModel {
        void getVideoJson(okhttp3.Callback callback);
        Video getSavedData();
        void saveData(Video video);
    }

    interface VideoFragmentAgreeView extends BaseFragmentView{

        void updateVideoData(String json);
        void requestDataError(String errMessage);
        void loadSavedData(Video video);

    }

    abstract class VideoFragmentAgreePresenter extends BaseFragmentPresenter<VideoFragmentAgreeModel,VideoFragmentAgreeView>{

        abstract void requestVideoJson();
        //加载已经保存的数据(调用发生在用户启动应用第一次加载)
        abstract void laodSavedData();
        //缓存数据
        abstract void saveData(Video video);

    }

}
