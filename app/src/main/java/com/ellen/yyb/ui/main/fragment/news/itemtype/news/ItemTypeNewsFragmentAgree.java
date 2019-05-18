package com.ellen.yyb.ui.main.fragment.news.itemtype.news;

import com.ellen.yyb.mvp.fragment.BaseFragmentModel;
import com.ellen.yyb.mvp.fragment.BaseFragmentPresenter;
import com.ellen.yyb.mvp.fragment.BaseFragmentView;

public interface ItemTypeNewsFragmentAgree {

    interface ItemTypeNewsFragmentAgreeModel extends BaseFragmentModel{

        //请求Json
        void getNewsJson(String url,okhttp3.Callback callback);

    }

    interface ItemTypeNewsFragmentAgreeView extends BaseFragmentView{

        //更新ui
        void updateUI(String json);
        //请求网络错误
        void reuqestNetError(String errMessage);
    }

    abstract class NewsDataFragmentAgreePresenter extends
            BaseFragmentPresenter<ItemTypeNewsFragmentAgreeModel,ItemTypeNewsFragmentAgreeView>{
        //请求网络Json数据
        abstract void requestNetJson(String url);
    }

}
