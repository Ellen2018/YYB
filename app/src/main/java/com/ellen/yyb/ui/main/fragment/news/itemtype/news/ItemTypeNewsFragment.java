package com.ellen.yyb.ui.main.fragment.news.itemtype.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;
import com.ellen.yyb.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import com.ellen.yyb.bean.NewsBean;
import com.ellen.yyb.bean.NewsTitle;
import com.ellen.yyb.helper.GsonHelper;
import com.ellen.yyb.mvp.fragment.BaseMvpFragment;
import com.ellen.yyb.ui.main.fragment.news.itemtype.news.adapter.ItemTypeNewsRecyclerViewAdapter;
import com.ellen.yyb.ui.news_details.NewsDetailsActivity;
import com.ellen.yyb.ui.news_details.NewsDetailsAgree;
import com.ellen.yyb.ui.news_details.NewsDetailsModel;
import com.ellen.yyb.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class ItemTypeNewsFragment extends BaseMvpFragment<ItemTypeNewsFragmentPresenter> implements
        ItemTypeNewsFragmentAgree.ItemTypeNewsFragmentAgreeView,BaseFragment.ButterKnifeInterface{

    private NewsTitle.DataBean dataBean;
    private Unbinder unbinder;
    private ItemTypeNewsRecyclerViewAdapter itemTypeNewsRecyclerViewAdapter;

    @BindView(R.id.recyclerview_data_news)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_data_news)
    ProgressBar progressBar;

    public ItemTypeNewsFragment(NewsTitle.DataBean dataBean){
        this.dataBean = dataBean;
    }

    @Override
    protected void initData() {
        mFragmentPresenter.requestNetJson(dataBean.getUrl());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_news_item_type_news;
    }

    @Override
    public void initButterKnife(View view) {
        unbinder = ButterKnife.bind(this,view);
    }

    @Override
    public void unBindButterKnife() {
        unbinder.unbind();
    }

    @Override
    protected void initMvp() {
        mFragmentPresenter = new ItemTypeNewsFragmentPresenter();
        mFragmentPresenter.mFragmentModel = new ItemTypeNewsFragmentModel();
        mFragmentPresenter.mFragmentView = this;
    }

    @Override
    public void updateUI(String json) {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        NewsBean newsBean = GsonHelper.getGsonInstance().fromJson(json,NewsBean.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemTypeNewsRecyclerViewAdapter = new ItemTypeNewsRecyclerViewAdapter(getActivity(),newsBean);
        recyclerView.setAdapter(itemTypeNewsRecyclerViewAdapter);
        itemTypeNewsRecyclerViewAdapter.setOnItemClickListener(new ItemTypeNewsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position,String chooseUrl) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra(NewsDetailsModel.NEWS_DETAILS_URL,chooseUrl);
                startActivity(intent);
            }

            @Override
            public void onBannerClick(int position, String chooseUrl) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra(NewsDetailsModel.NEWS_DETAILS_URL,chooseUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public void reuqestNetError(String errMessage) {
        Toast.makeText(getActivity(),"网络错误，请求数据失败",Toast.LENGTH_SHORT).show();
    }

}
