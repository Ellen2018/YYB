package com.ellen.yyb.ui.splash;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter extends SplashAgree.SplashAgreePresenter {

    @Override
    public void jump() {
        mModel.isClcikJump = true;
        mView.jumpToApplication();
    }

    @Override
    public void startCountdownJump() {
        final ExecutorService splashTask = Executors.newSingleThreadExecutor();
        splashTask.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<mModel.jumpAllTime && !mModel.isClcikJump;i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    final int finalI = i;
                    Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> emitter){
                               emitter.onNext("跳过"+(mModel.jumpAllTime - finalI));
                        }
                    }).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {
                                mView.updateJumpData(s);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });



                }
                mView.jumpToApplication();
                //结束线程池
                splashTask.shutdown();
            }
        });
    }

    @Override
    public void loadImage(Context context, ImageView imageView) {
        String url = "http://lorempixel.com/1600/900";
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
}
