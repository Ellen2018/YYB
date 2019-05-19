package com.ellen.yyb.ui.main.fragment.news.itemtype.web;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.RelativeLayout;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class ItemTypeWebFragment extends BaseFragment implements BaseFragment.ButterKnifeInterface{

    private Unbinder unbinder;
    private String url;
    private  AgentWeb agentWeb;

    @BindView(R.id.relativelayout_webview)
    RelativeLayout relativeLayout;

    public ItemTypeWebFragment(String url) {
        this.url = url;
    }


    @Override
    protected void initData() {
     agentWeb =  AgentWeb.with(getActivity())
                .setAgentWebParent(relativeLayout, new RelativeLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .closeWebViewClientHelper()
                .interceptUnkownUrl()
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_item_type_web;
    }

    @Override
    public void initButterKnife(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void unBindButterKnife() {
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        agentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        agentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
       agentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }

}
