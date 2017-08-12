package com.example.android.iak2017intermediate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.iak2017intermediate.BuildConfig;
import com.example.android.iak2017intermediate.R;
import com.example.android.iak2017intermediate.adapter.NewsAdapter;
import com.example.android.iak2017intermediate.adapter.NewsClickListener;
import com.example.android.iak2017intermediate.model.APIResponse;
import com.example.android.iak2017intermediate.model.ArticlesItem;
import com.example.android.iak2017intermediate.rest.ApiClient;
import com.example.android.iak2017intermediate.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsClickListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapterDummy;
    private NewsAdapter mAdapterApi;
    private final String NEWS_SOURCE = "mtv-news";
    private List<ArticlesItem> mArticleItems = new ArrayList<>();
    private static final String TAG = MainActivity.class.getSimpleName();

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

        //mAdapterDummy = new NewsAdapter(getDummyData());
        mAdapterApi = new NewsAdapter(mArticleItems);
        mRecyclerView.setLayoutManager(mLayoutManager);

        getData();
        mRecyclerView.setAdapter(mAdapterApi);
        mAdapterApi.setItemClickListener(this);

    }

    private List<ArticlesItem> getDummyData() {
        List<ArticlesItem> dummyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArticlesItem dummyNews = new ArticlesItem();
            dummyNews.setTitle("News Title for Data #" + String.valueOf(i+1));
            dummyNews.setDescription("News Description for Data #" + String.valueOf(i+1));
            dummyNews.setUrlToImage("https://s-media-cache-ak0.pinimg.com/originals/35/32/b5/3532b50d1d23fc5c85796110ffbe799e.jpg");
            //FOR EXAMPLE ONLY
            dummyList.add(dummyNews);
        }

        return dummyList;
    }

    private void getData(){
        ApiService apiService = ApiClient.getRetrofitClient().create(ApiService.class);
        Call<APIResponse> apiResponseCall = apiService.getArticles(
                NEWS_SOURCE,
                BuildConfig.API_KEY
        );
        apiResponseCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                if(apiResponse != null){
                    mArticleItems = apiResponse.getArticles();
                    mAdapterApi.setData(mArticleItems);
                    //mAdapterApi.notifyDataSetChanged();
                    //mRecyclerView.notifyAll();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onItemNewsClicked(ArticlesItem newsItem) {
        Toast.makeText(this, newsItem.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
