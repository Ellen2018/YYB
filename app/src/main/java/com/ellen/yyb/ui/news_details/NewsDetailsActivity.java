package com.ellen.yyb.ui.news_details;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseActivity;
import com.ellen.yyb.mvp.activity.BaseMvpActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailsActivity extends BaseMvpActivity<NewsDetailsPresenter> implements
        NewsDetailsAgree.NewsDetailsAgreeView, BaseActivity.ButterKnifeInterface {

    @BindView(R.id.rl_webview_activity_news_details)
    RelativeLayout relativeLayout;

    private AgentWeb agentWeb;


    @OnClick(R.id.iv_back_activity_news_details)
    void onClick(View view){

        switch (view.getId()){
            case R.id.iv_back_activity_news_details:
                finish();
                break;
        }

    }

    @Override
    public void initMvp() {

    }

    @Override
    protected void setStatus() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_new_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra(NewsDetailsModel.NEWS_DETAILS_URL);
        agentWeb = AgentWeb.with(NewsDetailsActivity.this)
                .setAgentWebParent(relativeLayout, new RelativeLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(Color.BLUE)
                .closeWebViewClientHelper()
                .interceptUnkownUrl()
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .createAgentWeb()
                .ready()
                .go(url);

    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return null;
    }

    @Override
    public void loadUrl(String url) {

    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
