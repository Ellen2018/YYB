package com.ellen.yyb.ui.main.fragment.video;

import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.helper.GsonHelper;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VideoFragmentPresenter extends VideoFragmentAgree.VideoFragmentAgreePresenter {
    @Override
    void requestVideoJson() {
        Observable.create(new ObservableOnSubscribe<String>() {

                              @Override
                              public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                                  mFragmentModel.getVideoJson(new Callback() {
                                      @Override
                                      public void onFailure(Call call, IOException e) {
                                          emitter.onNext("");
                                          emitter.onComplete();
                                      }

                                      @Override
                                      public void onResponse(Call call, Response response) throws IOException {
                                          emitter.onNext(response.body().string());
                                          emitter.onComplete();
                                      }
                                  });
                              }
                          }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        if(s == ""){
                            //请求网络数据失败
                            mFragmentView.requestDataError("");
                        }else {
                            mFragmentView.updateVideoData(s);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
