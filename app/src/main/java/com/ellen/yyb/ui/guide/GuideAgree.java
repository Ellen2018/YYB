package com.ellen.yyb.ui.guide;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.ellen.yyb.mvp.BaseModel;
import com.ellen.yyb.mvp.BasePresenter;
import com.ellen.yyb.mvp.BaseView;

import me.relex.circleindicator.CircleIndicator;

public interface GuideAgree {

    interface GuideAgreeModel extends BaseModel{

        //是否首次启动应用
        boolean isFirstLanucher();
        //设置首次启动的标记
        void saveFirstLanucher(boolean isFirstLanucher);

        //获取向导页的图片集合
        int[] getGuideImageArray();

    }

    interface GuideAgreeView extends BaseView{

        void jumpToMain();
        void jumpToSplash();
        void showJumpTextView();
        void hideJumpTextView();
        void loadingGuideImage();

    }

    abstract class GuideAgreePresenter extends BasePresenter<GuideAgreeModel,GuideAgreeView>{

       abstract void loadingGuideImage(FragmentActivity activity, CircleIndicator circleIndicator,ViewPager viewPager);
       abstract void saveFirstLanucher();
       abstract void checkUserFirstLanucher();

    }



}
