package com.ellen.yyb.ui.main.fragment.video.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ellen.yyb.R;
import com.ellen.yyb.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import com.ellen.yyb.base.adapter.recyclerview.BaseSingleRecyclerViewAdapter;
import com.ellen.yyb.base.adapter.recyclerview.BaseViewHolder;
import com.ellen.yyb.bean.TrailersBean;
import com.ellen.yyb.bean.Video;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class VideoRecyclerViewAdapter extends BaseSingleRecyclerViewAdapter<TrailersBean,VideoRecyclerViewAdapter.VideoViewHolder> {


    public VideoRecyclerViewAdapter(Context context, List<TrailersBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_video;
    }

    @Override
    protected VideoViewHolder getNewViewHolder(View view) {
        return new VideoViewHolder(view);
    }

    @Override
    protected void showData(VideoViewHolder videoViewHolder, int position) {
        videoViewHolder.jzVideoPlayerStandard.setUp(getDataList().get(position).getHightUrl()
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, getDataList().get(position).getMovieName());



        videoViewHolder.jzVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);


        //加载图片：Glide框架
        Glide.with(getContext())
                .load(getDataList().get(position).getCoverImg())
//                .placeholder(R.drawable.news_moren)
//                .error(R.drawable.news_moren)
                .into( videoViewHolder.jzVideoPlayerStandard.thumbImageView);
    }

    static class VideoViewHolder extends BaseViewHolder{

        JZVideoPlayerStandard jzVideoPlayerStandard;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            jzVideoPlayerStandard = itemView.findViewById(R.id.jzplayer);
        }
    }

}
