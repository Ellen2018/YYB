package com.ellen.yyb.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.yyb.base.BaseFragment;

public abstract class BaseMvpFragment<P extends BaseFragmentPresenter> extends BaseFragment {
    public P mFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initMvp();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initMvp();
}
