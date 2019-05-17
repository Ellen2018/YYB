package com.ellen.yyb.ui.main;

import android.content.Context;


import com.ellen.yyb.R;

public class MainModel implements MainAgree.MainAgreeModel {
    private String[] titles = {"新闻","视讯","社区","我"};

    private int[] icons = {
            R.mipmap.item1_main_news,
            R.mipmap.item2_main_video,
            R.mipmap.item3_main_shequ,
            R.mipmap.item4_main_me
    };

    @Override
    public String[] getBottomBarTitleArray() {
        return titles;
    }

    @Override
    public int[] getBottomBarIconArray() {
        return icons;
    }

}
