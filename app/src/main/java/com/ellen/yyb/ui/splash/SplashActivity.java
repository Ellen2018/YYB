package com.ellen.yyb.ui.splash;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseActivity;
import com.ellen.yyb.mvp.BaseMvpActivity;
import com.ellen.yyb.ui.main.MainActivity;
import com.ellen.yyb.util.statusutil.StatusUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements
        SplashAgree.SplashAgreeView,
        BaseActivity.ButterKnifeInterface {

    @BindView(R.id.iv_splash_bg)
    ImageView ivSplashBg;
    @BindView(R.id.tv_splash_jump)
    TextView tvSplashJump;

    @OnClick(R.id.tv_splash_jump)
    void onClick(View view){
        jump();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCountdownJump();
    }

    @Override
    public void initMvp() {
        mPresenter = new SplashPresenter();
        mPresenter.mModel = new SplashModel();
        mPresenter.mView = this;
    }

    @Override
    protected void setStatus() {
       //设置全屏
        StatusUtils.setFullScreen(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return null;
    }

    @Override
    public void jump() {
       mPresenter.jump();
    }

    @Override
    public void startCountdownJump() {
        loadNetIamge(ivSplashBg);
       mPresenter.startCountdownJump();
    }

    @Override
    public void updateJumpData(String data) {
        tvSplashJump.setText(data);
    }

    @Override
    public void jumpToApplication() {
       //判断MianActivity是否存在
        List<Activity> activityList = BaseActivity.getActivityList();
        if(activityList == null){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            boolean isHaveMainActivity = false;
            for (Activity activity : activityList) {
                if(activity.getClass() == MainActivity.class){
                    //说明之前已经跳转到主界面了
                    isHaveMainActivity = true;
                }
            }
            if(!isHaveMainActivity){
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }

    @Override
    public void loadNetIamge(ImageView imageView) {
        mPresenter.loadImage(this,imageView);
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }
}
