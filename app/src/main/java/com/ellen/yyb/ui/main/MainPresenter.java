package com.ellen.yyb.ui.main;

public class MainPresenter extends MainAgree.MainAgreePresenter {
    @Override
    String[] getBottomBarTitleArray() {
        return mModel.getBottomBarTitleArray();
    }

    @Override
    int[] getBottomBarIconArray() {
        return mModel.getBottomBarIconArray();
    }
}
