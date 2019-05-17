package com.ellen.yyb.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ellen.yyb.base.BaseActivity;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initMvp();
        super.onCreate(savedInstanceState);
    }

    public abstract void initMvp();
}
