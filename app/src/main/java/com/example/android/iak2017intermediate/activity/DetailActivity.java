package com.example.android.iak2017intermediate.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.iak2017intermediate.R;
import com.example.android.iak2017intermediate.model.ArticlesItem;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_NEWS = "news";
    private ArticlesItem mArticleItem;

    public static void start(Context context, String newsJson){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_EXTRA_NEWS, newsJson);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(getIntent().hasExtra(KEY_EXTRA_NEWS)){
            String newsJson = getIntent().getStringExtra(KEY_EXTRA_NEWS);
            mArticleItem = new ArticlesItem().fromJson(newsJson);
            Toast.makeText(this, "Show news "+ mArticleItem.getTitle(), Toast.LENGTH_SHORT).show();
        }else{
            finish();
        }
    }
}
