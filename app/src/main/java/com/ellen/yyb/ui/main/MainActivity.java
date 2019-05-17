package com.ellen.yyb.ui.main;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseActivity;
import com.ellen.yyb.base.BaseFragment;
import com.ellen.yyb.mvp.BaseMvpActivity;
import com.ellen.yyb.ui.main.fragment.CommunityFragment;
import com.ellen.yyb.ui.main.fragment.NewsFragment;
import com.ellen.yyb.ui.main.fragment.UserCenterFragment;
import com.ellen.yyb.ui.main.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements
        MainAgree.MainAgreeView, BaseActivity.ButterKnifeInterface {

    @BindView(R.id.bnb_activity_main)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void setStatus() {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String[] titles = mPresenter.getBottomBarTitleArray();
        int[] icons = mPresenter.getBottomBarIconArray();
        //为底部导航栏添加item
        for(int i= 0;i<titles.length;i++){
            BottomNavigationItem bottomNavigationItem = new BottomNavigationItem(icons[i],titles[i]);
            bottomNavigationBar.addItem(bottomNavigationItem);
        }
        bottomNavigationBar
                .setInActiveColor("#8B8B83")//未选中颜色
                .setActiveColor("#3333ff")//选中的颜色
                .setBarBackgroundColor("#ffffff") //整体背景颜色
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        switchFragment(position);
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                })
                .initialise();
        switchFragment(0);
    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return null;
    }

    @Override
    public void initMvp() {
        mPresenter = new MainPresenter();
        mPresenter.mModel = new MainModel();
        mPresenter.mView = this;
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    private BaseFragment currentFragment;

    @Override
    public void switchFragment(int position) {
        String tag = mPresenter.getBottomBarTitleArray()[position];
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(tag);
        if (currentFragment == null) {
            switch (position) {
                case 0:
                    currentFragment = new NewsFragment();
                    break;
                case 1:
                    currentFragment = new VideoFragment();
                    break;
                case 2:
                    currentFragment = new CommunityFragment();
                    break;
                case 3:
                    currentFragment = new UserCenterFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout_main, currentFragment, tag).commit();
        }else {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        }
    }
}
