package com.ellen.yyb.ui.news_details;

import com.ellen.yyb.mvp.activity.BaseModel;
import com.ellen.yyb.mvp.activity.BasePresenter;
import com.ellen.yyb.mvp.activity.BaseView;
import com.ellen.yyb.mvp.fragment.BaseFragmentPresenter;

public interface NewsDetailsAgree {

    interface NewsDetailsAgreeModel extends BaseModel{

        void like(String url);
        void share(String shareTitle,String url);

    }

    interface NewsDetailsAgreeView extends BaseView{

        void loadUrl(String url);

    }

    abstract class NewsDetailsAgreePresenter extends BasePresenter<NewsDetailsAgreeModel,NewsDetailsAgreeView> {

        abstract void like(String url);
        abstract void share(String shareTitle,String url);

    }
}
