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
import com.ellen.yyb.ui.main.fragment.news.new_fragment.SmileNewsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsFragment extends BaseMvpFragment<NewsFragmentPresenter> implements
        NewsFragmentAgree.NewsFragmentAgreeView,BaseFragment.ButterKnifeInterface {

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
       unbinder = ButterKnife.bind(this,view);
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
                return new SmileNewsFragment();
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
        Toast.makeText(getActivity(),"获取网络数据失败",Toast.LENGTH_SHORT).show();
    }
}
