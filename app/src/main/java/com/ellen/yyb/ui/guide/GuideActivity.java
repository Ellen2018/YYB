package com.ellen.yyb.ui.guide;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseActivity;
import com.ellen.yyb.mvp.BaseMvpActivity;
import com.ellen.yyb.ui.main.MainActivity;
import com.ellen.yyb.ui.splash.SplashActivity;
import com.ellen.yyb.util.statusutil.StatusUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends BaseMvpActivity<GuidePresenter> implements
        GuideAgree.GuideAgreeView,
        BaseActivity.ButterKnifeInterface {

    @BindView(R.id.viewpager_guide)
    ViewPager viewPager;
    @BindView(R.id.indicator_guide)
    CircleIndicator circleIndicator;
    @BindView(R.id.tv_jump_guide)
    TextView tvJumpGuide;

    @OnClick(R.id.tv_jump_guide)
    void onClick(View view){
        jumpToMain();
    }

    @Override
    public void initMvp() {
        mPresenter = new GuidePresenter();
        mPresenter.mModel = new GuideModel();
        mPresenter.mView = this;
    }

    @Override
    protected void setStatus() {
        StatusUtils.setFullScreen(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //判断是否首次启动应用
        mPresenter.checkUserFirstLanucher();
    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return null;
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void jumpToMain() {
        mPresenter.saveFirstLanucher();
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void jumpToSplash() {
        mPresenter.saveFirstLanucher();
        Intent intent = new Intent(GuideActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showJumpTextView() {
        tvJumpGuide.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideJumpTextView() {
        tvJumpGuide.setVisibility(View.GONE);
    }

    @Override
    public void loadingGuideImage() {
        mPresenter.loadingGuideImage(this,circleIndicator,viewPager);
    }
}
