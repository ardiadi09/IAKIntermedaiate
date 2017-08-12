package com.example.android.iak2017intermediate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.iak2017intermediate.R;
import com.example.android.iak2017intermediate.adapter.NewsAdapter;
import com.example.android.iak2017intermediate.model.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );

        mAdapter = new NewsAdapter(getDummyData());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<ArticlesItem> getDummyData() {
        List<ArticlesItem> dummyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArticlesItem dummyNews = new ArticlesItem();
            dummyNews.setTitle("News Title for Data #" + String.valueOf(i+1));
            dummyNews.setDescription("News Description for Data #" + String.valueOf(i+1));
            dummyNews.setUrlToImage("https://s-media-cache-ak0.pinimg.com/originals/35/32/b5/3532b50d1d23fc5c85796110ffbe799e.jpg");

            dummyList.add(dummyNews);
        }

        return dummyList;
    }
}
