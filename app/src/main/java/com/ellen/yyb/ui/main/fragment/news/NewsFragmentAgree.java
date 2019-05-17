package com.ellen.yyb.ui.main.fragment.news;

import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.mvp.fragment.BaseFragmentModel;
import com.ellen.yyb.mvp.fragment.BaseFragmentPresenter;
import com.ellen.yyb.mvp.fragment.BaseFragmentView;

import java.io.IOException;
import java.util.List;

public interface NewsFragmentAgree {

    interface NewsFragmentAgreeModel extends BaseFragmentModel{
        void getNewsTitle(okhttp3.Callback callback);
    }

    interface NewsFragmentAgreeView extends BaseFragmentView{
        void updateNewTitle(List<NewsTitle.DataBean> dataBeanList);
        void requestNewsTitleFailure();
    }

    abstract class NewsFragmentAgreePresenter extends BaseFragmentPresenter<NewsFragmentAgreeModel,NewsFragmentAgreeView>{
       //访问网络获取News Title的数据
       abstract void requestNewsTitleFromNet();

    }

}
