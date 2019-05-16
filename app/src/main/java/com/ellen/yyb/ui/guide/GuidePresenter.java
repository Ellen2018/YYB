package com.ellen.yyb.ui.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.ellen.yyb.base.adapter.viewpager.BaseFragmentPagerAdapter;
import com.ellen.yyb.ui.guide.fragment.GuideItemFragment;

import me.relex.circleindicator.CircleIndicator;

public class GuidePresenter extends GuideAgree.GuideAgreePresenter {

    @Override
    void loadingGuideImage(FragmentActivity activity, CircleIndicator circleIndicator,ViewPager viewPager) {
        BaseFragmentPagerAdapter baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(activity.getSupportFragmentManager()) {
            @Override
            protected int getFragmentPagerSize() {
                return mModel.getGuideImageArray().length;
            }

            @Override
            protected Fragment getFragment(int position) {
                return new GuideItemFragment(mModel.getGuideImageArray()[position]);
            }
        };
        viewPager.setAdapter(baseFragmentPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        baseFragmentPagerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                  if(i == mModel.getGuideImageArray().length - 1){
                       mView.showJumpTextView();
                  }else {
                      mView.hideJumpTextView();
                  }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    void saveFirstLanucher() {
        mModel.saveFirstLanucher(true);
    }

    @Override
    void checkUserFirstLanucher() {
        if(mModel.isFirstLanucher()){
            mView.jumpToSplash();
        }else {
            mView.loadingGuideImage();
        }
    }

}
