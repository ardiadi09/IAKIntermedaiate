package com.example.android.iak2017intermediate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.iak2017intermediate.R;
import com.example.android.iak2017intermediate.activity.MainActivity;
import com.example.android.iak2017intermediate.model.ArticlesItem;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Support on 8/6/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<ArticlesItem> mNewsList;
    private NewsClickListener newsClickListener;

    public NewsAdapter(List<ArticlesItem> mNewsList) {
        this.mNewsList = mNewsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        ArticlesItem news = mNewsList.get(position);
        holder.bind(news);
        holder.btnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newsClickListener != null){
                    newsClickListener.onItemNewsClicked(
                            mNewsList.get(position)
                    );
                }
            }
        });
    }

    public void setData(List<ArticlesItem> datas){
        mNewsList.clear();
        mNewsList.addAll(datas);
        notifyDataSetChanged();
    }

    public void setItemClickListener(NewsClickListener clickListener){
        if(clickListener != null){
            this.newsClickListener = clickListener;
        }
    }
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivNewsPhoto) ImageView ivNewsPhoto;
        @BindView(R.id.tvNewsTitle) TextView tvNewsTitle;
        @BindView(R.id.tvNewsDescription) TextView tvNewsDescription;
        @BindView(R.id.btnReadMore) Button btnReadMore;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ArticlesItem newsItem) {
            tvNewsTitle.setText(newsItem.getTitle());
            tvNewsDescription.setText(newsItem.getDescription());

            Glide.with(tvNewsTitle.getContext())
                    .load(newsItem.getUrlToImage())
                    .into(ivNewsPhoto);
        }


    }


}
