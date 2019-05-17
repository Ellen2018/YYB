package com.ellen.yyb.ui.main;

import com.ellen.yyb.mvp.BaseModel;
import com.ellen.yyb.mvp.BasePresenter;
import com.ellen.yyb.mvp.BaseView;

public interface MainAgree {

    //协议化MainModel
    interface MainAgreeModel extends BaseModel{

        //获取底部导航栏的title数组
        String[] getBottomBarTitleArray();
        //获取底部导航栏的图片数组
        int[] getBottomBarIconArray();

    }

    interface MainAgreeView extends BaseView{

        void switchFragment(int position);

    }


    abstract class MainAgreePresenter extends BasePresenter<MainAgreeModel,MainAgreeView>{

        //获取底部导航栏的title数组
        abstract String[] getBottomBarTitleArray();
        //获取底部导航栏的图片数组
        abstract int[] getBottomBarIconArray();

    }

}
