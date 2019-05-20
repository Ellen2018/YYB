package com.ellen.yyb.ui.main.fragment.video;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;
import com.ellen.yyb.bean.TrailersBean;
import com.ellen.yyb.bean.Video;
import com.ellen.yyb.helper.GsonHelper;
import com.ellen.yyb.mvp.fragment.BaseMvpFragment;
import com.ellen.yyb.ui.main.fragment.video.adapter.VideoRecyclerViewAdapter;

import java.util.List;

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
       //先加载已经缓存过的数据
       mFragmentPresenter.laodSavedData();
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
        mFragmentPresenter.mFragmentModel = new VideoFragmentModel(getActivity());
        mFragmentPresenter.mFragmentView = this;
    }

    @Override
    public void updateVideoData(String json) {
        Video video = GsonHelper.getGsonInstance().fromJson(json,Video.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new VideoRecyclerViewAdapter(getActivity(),video.getTrailers()));
        //缓存数据
        mFragmentPresenter.saveData(video);
    }

    @Override
    public void requestDataError(String errMessage) {

    }

    @Override
    public void loadSavedData(Video video) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new VideoRecyclerViewAdapter(getActivity(),video.getTrailers()));
    }

}
