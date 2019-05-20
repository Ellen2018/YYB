package com.ellen.yyb.ui.main.fragment.news.itemtype.news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ellen.yyb.R;
import com.ellen.yyb.base.adapter.recyclerview.BaseMultipleRecyclerViewAdapter;
import com.ellen.yyb.base.adapter.recyclerview.BaseViewHolder;
import com.ellen.yyb.bean.NewsBean;
import com.ellen.yyb.helper.GlideImageLoader;
import com.ellen.yyb.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class ItemTypeNewsRecyclerViewAdapter extends BaseMultipleRecyclerViewAdapter {

    private NewsBean newsBean;
    private static final int ITEM_TYPE_BANNER = 1;
    private static final int ITEM_TYPE_NEWS = 2;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public ItemTypeNewsRecyclerViewAdapter(Context context,NewsBean newsBean) {
        super(context);
        this.newsBean = newsBean;
    }

    @Override
    protected int getMultipleItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE_BANNER;
        }else {
            return ITEM_TYPE_NEWS;
        }
    }

    @Override
    protected int getItemSize() {
        return newsBean.getData().getItemList().size() + 1;
    }

    @Override
    protected BaseViewHolder getNewBaseViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        if(itemType == ITEM_TYPE_BANNER){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_type_news_banner,null);
            return new BannerViewHolder(view);
        }else {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_type_news_item,null);
            return new ItemViewHolder(view);
        }
    }

    @Override
    protected void showData(BaseViewHolder baseViewHolder, final int position) {
        if(baseViewHolder instanceof BannerViewHolder){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) baseViewHolder;
            bannerViewHolder.banner.setImageLoader(new GlideImageLoader());
            bannerViewHolder.banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片集合
            List<String> bannerImages = new ArrayList<>();
            List<String> bannerTitles = new ArrayList<>();
            for(NewsBean.DataBean.BigImgBean bigImgBean:newsBean.getData().getBigImg()){
                bannerImages.add(bigImgBean.getItemImage());
                bannerTitles.add(bigImgBean.getItemTitle());
            }
            bannerViewHolder.banner.setImages(bannerImages);
            bannerViewHolder.banner.setBannerTitles(bannerTitles);
            bannerViewHolder.banner.setBannerAnimation(Transformer.FlipHorizontal);
            bannerViewHolder.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    onItemClickListener.onBannerClick(position,newsBean.getData().getBigImg().get(position).getDetailUrl());
                }
            });
            bannerViewHolder.banner.setDelayTime(5000);
            bannerViewHolder.banner.start();
        }else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) baseViewHolder;
            itemViewHolder.tvTitle.setText(newsBean.getData().getItemList().get(position-1).getItemTitle());
            Glide.with(getContext()).load(newsBean.getData().getItemList().
                    get(position-1).getScrollImage().getImgUrl1()).into(itemViewHolder.imageView);
            itemViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onClick(position-1,newsBean.getData().getItemList().get(position-1).getDetailUrl());
                    }
                }
            });
        }

    }

    public static class BannerViewHolder extends BaseViewHolder{

        Banner banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public static class ItemViewHolder extends BaseViewHolder{

        TextView tvTitle;
        ImageView imageView;
        CardView cardView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_news_title);
            imageView = itemView.findViewById(R.id.iv_nwes_image);
            cardView = itemView.findViewById(R.id.cardview_news);
        }
    }

    public interface OnItemClickListener{

        void onClick(int position,String chooseUrl);
        void onBannerClick(int position,String chooseUrl);

    }
}
