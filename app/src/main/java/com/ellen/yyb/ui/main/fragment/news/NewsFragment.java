package com.ellen.yyb.ui.main.fragment.news;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;
import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.mvp.fragment.BaseMvpFragment;
import com.ellen.yyb.ui.main.fragment.news.itemtype.bigimgitl.ItemTypeBigimagitlFragment;
import com.ellen.yyb.ui.main.fragment.news.itemtype.cnews.ItemTypeCNewsFragment;
import com.ellen.yyb.ui.main.fragment.news.itemtype.news.ItemTypeNewsFragment;
import com.ellen.yyb.ui.main.fragment.news.itemtype.web.ItemTypeWebFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsFragment extends BaseMvpFragment<NewsFragmentPresenter> implements
        NewsFragmentAgree.NewsFragmentAgreeView, BaseFragment.ButterKnifeInterface {

    private Unbinder unbinder;

    @BindView(R.id.smarttablayout_fragment_news_main)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewpager_fragment_news_main)
    ViewPager viewPager;


    @Override
    protected void initData() {
        mFragmentPresenter.requestNewsTitleFromNet();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_main;
    }

    @Override
    public void initButterKnife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void unBindButterKnife() {
        unbinder.unbind();
    }

    @Override
    protected void initMvp() {
        mFragmentPresenter = new NewsFragmentPresenter();
        mFragmentPresenter.mFragmentModel = new NewsFragmentModel();
        mFragmentPresenter.mFragmentView = this;
    }

    @Override
    public void updateNewTitle(final List<NewsTitle.DataBean> dataBeanList) {
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                String itemType = dataBeanList.get(i).getItemType();
                if (getString(R.string.item_type_news).equals(itemType)) {
                    return new ItemTypeNewsFragment(dataBeanList.get(i));
                } else if (getString(R.string.item_type_cnews).equals(itemType)) {
                    return new ItemTypeCNewsFragment();
                } else if (getString(R.string.item_type_web).equals(itemType)) {
                    return new ItemTypeWebFragment(dataBeanList.get(i).getUrl());
                } else if(getString(R.string.item_type_bigimgitl).equals(itemType)){
                    return new ItemTypeBigimagitlFragment();
                }else {
                    return null;
                }
            }

            @Override
            public int getCount() {
                return dataBeanList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return dataBeanList.get(position).getTitle();
            }
        });
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void requestNewsTitleFailure() {
        Toast.makeText(getActivity(), "获取网络数据失败", Toast.LENGTH_SHORT).show();
    }
}
