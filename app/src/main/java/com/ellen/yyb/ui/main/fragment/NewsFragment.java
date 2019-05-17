package com.ellen.yyb.ui.main.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;
import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.helper.GsonHelper;
import com.google.gson.JsonElement;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsFragment extends BaseFragment implements BaseFragment.ButterKnifeInterface {

    private Unbinder unbinder;

    @BindView(R.id.smarttablayout_fragment_news_main)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewpager_fragment_news_main)
    ViewPager viewPager;

    //新闻的标题
    private String newTitleUrl = "http://m.news.cntv.cn/special/json/fl626/index.json";

    private TitleHandler titleHandler;

    @Override
    protected void initData() {
        titleHandler = new TitleHandler();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(newTitleUrl).get().build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String newsTitleJson = response.body().string();
                Message message = new Message();
                message.obj = newsTitleJson;
                titleHandler.sendMessage(message);
            }
        });

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

    class TitleHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final NewsTitle newsTitle = GsonHelper.getGsonInstance().fromJson((String) msg.obj,NewsTitle.class);
            viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
                @Override
                public Fragment getItem(int i) {
                    return new SmileNewsFragment();
                }

                @Override
                public int getCount() {
                    return newsTitle.getData().size();
                }

                @Nullable
                @Override
                public CharSequence getPageTitle(int position) {
                    return newsTitle.getData().get(position).getTitle();
                }
            });
            tabLayout.setViewPager(viewPager);
        }
    }
}
