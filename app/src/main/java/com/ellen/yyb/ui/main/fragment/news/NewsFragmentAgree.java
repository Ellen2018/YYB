package com.ellen.yyb.ui.main.fragment.news;

import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.mvp.fragment.BaseFragmentModel;
import com.ellen.yyb.mvp.fragment.BaseFragmentPresenter;
import com.ellen.yyb.mvp.fragment.BaseFragmentView;

import java.io.IOException;
import java.util.List;

public interface NewsFragmentAgree {

    interface NewsFragmentAgreeModel extends BaseFragmentModel{
        String getNewsTitle() throws IOException;
    }

    interface NewsFragmentAgreeView extends BaseFragmentView{
        void updateNewTitle(List<NewsTitle.DataBean> dataBeanList);
    }

    abstract class NewsFragmentAgreePresenter extends BaseFragmentPresenter<NewsFragmentAgreeModel,NewsFragmentAgreeView>{

       abstract void requestNewsTitleFromNet();

    }

}
