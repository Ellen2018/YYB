package com.ellen.yyb.ui.splash;

import android.content.Context;
import android.widget.ImageView;

import com.ellen.yyb.mvp.activity.BaseModel;
import com.ellen.yyb.mvp.activity.BasePresenter;
import com.ellen.yyb.mvp.activity.BaseView;

public interface SplashAgree {

    abstract class SplashAgreeModel implements BaseModel{
        //跳过的时间
        public int jumpAllTime = 5;
        public boolean isClcikJump = false;

    }

    interface SplashAgreeView extends BaseView{

        //用户点击了跳过
        void jump();
        //倒计时跳过
        void startCountdownJump();
        //更新跳过的数据
        void updateJumpData(String data);
        //跳转到应用界面
        void jumpToApplication();
        //加载图片
        void loadNetIamge(ImageView imageView);

    }

    abstract class SplashAgreePresenter extends BasePresenter<SplashAgreeModel,SplashAgreeView>{
        public abstract void jump();
        public abstract void startCountdownJump();
        public abstract void loadImage(Context context, ImageView imageView);
    }

}
