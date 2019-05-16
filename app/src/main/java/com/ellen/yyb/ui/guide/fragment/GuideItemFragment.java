package com.ellen.yyb.ui.guide.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ellen.yyb.R;
import com.ellen.yyb.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class GuideItemFragment extends BaseFragment implements BaseFragment.ButterKnifeInterface {

    private Unbinder unbinder;

    @BindView(R.id.iv_fragment_item_guide)
    ImageView imageView;

    private int imageResourceId;

    public GuideItemFragment(int imageResourceId){
        this.imageResourceId = imageResourceId;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Glide.with(getActivity()).load(imageResourceId).into(imageView);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_item_guide;
    }

    @Override
    public void initButterKnife(View view) {
        unbinder = ButterKnife.bind(this,view);
    }

    @Override
    public void unBindButterKnife() {
         unbinder.unbind();
    }
}
