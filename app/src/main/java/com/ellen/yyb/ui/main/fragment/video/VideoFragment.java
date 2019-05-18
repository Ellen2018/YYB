package com.ellen.yyb.ui.main.fragment.video;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;
import com.ellen.yyb.bean.Video;
import com.ellen.yyb.helper.GsonHelper;
import com.ellen.yyb.mvp.fragment.BaseMvpFragment;
import com.ellen.yyb.ui.main.fragment.video.adapter.VideoRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class VideoFragment extends BaseMvpFragment<VideoFragmentPresenter> implements
        BaseFragment.ButterKnifeInterface, VideoFragmentAgree.VideoFragmentAgreeView {

    @BindView(R.id.recyclerview_video)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    @Override
    protected void initData() {
       mFragmentPresenter.requestVideoJson();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_main;
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
        mFragmentPresenter = new VideoFragmentPresenter();
        mFragmentPresenter.mFragmentModel = new VideoFragmentModel();
        mFragmentPresenter.mFragmentView = this;
    }

    @Override
    public void updateVideoData(String json) {
        Log.e("请求的Json数据",json);
        Video video = GsonHelper.getGsonInstance().fromJson(json,Video.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new VideoRecyclerViewAdapter(getActivity(),video.getTrailers()));
    }

    @Override
    public void requestDataError(String errMessage) {

    }
}
