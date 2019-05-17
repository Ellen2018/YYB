package com.ellen.yyb.ui.main.fragment.news;

import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.helper.GsonHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsFragmentPresenter extends NewsFragmentAgree.NewsFragmentAgreePresenter {
    @Override
    void requestNewsTitleFromNet() {
        Observable.create(new ObservableOnSubscribe<String>() {

                              @Override
                              public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                  String json = mFragmentModel.getNewsTitle();
                                  emitter.onNext(json);
                                  emitter.onComplete();
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
                       mFragmentView.updateNewTitle(GsonHelper.getGsonInstance().fromJson(s, NewsTitle.class).getData());
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
