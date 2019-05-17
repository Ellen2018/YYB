package com.ellen.yyb.ui.main;

import android.content.Context;

import com.ellen.sqlitelbrary.ruibingsqlite.ruibingsqlite.RuiBingLibrary;
import com.ellen.sqlitelbrary.ruibingsqlite.ruibingsqlite.RuiBingTable;
import com.ellen.yyb.R;
import com.ellen.yyb.bean.NewsTitle;

import java.util.List;

public class MainModel implements MainAgree.MainAgreeModel {
    private String[] titles = {"新闻","视讯","社区","我"};

    private RuiBingTable<NewsTitle.DataBean> ruiBingTable;

    MainModel(Context context){
        //创建库和表
        RuiBingLibrary ruiBingLibrary = new RuiBingLibrary(context,"news",null,1);
        ruiBingLibrary.create();
        ruiBingTable = new RuiBingTable<>(ruiBingLibrary,"news_title",NewsTitle.DataBean.class);
        ruiBingTable.create();
    }

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
