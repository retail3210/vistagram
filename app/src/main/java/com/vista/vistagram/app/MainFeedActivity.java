package com.vista.vistagram.app;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vista.vistagram.R;
import com.vista.vistagram.models.Article;

import java.util.List;

public class MainFeedActivity extends BaseActivity {

    DrawerLayout drawer;
    RecyclerView feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();

        feedList = findView(R.id.mainList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

        private List<Article> articles;

        public FeedAdapter(List<Article> articles) {
            this.articles = articles;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_feed_card, null, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Article article = articles.get(position);
            holder.authorName.setText(article.title);
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView authorName, writeDate, body;
            public ImageView authorProfile, contentImage;

            public ViewHolder(View itemView) {
                super(itemView);
                authorName = (TextView) itemView.findViewById(R.id.profileName);
                writeDate = (TextView) itemView.findViewById(R.id.creationDate);
                body = (TextView) itemView.findViewById(R.id.body);
                authorProfile = (ImageView) itemView.findViewById(R.id.profilePicture);
                contentImage = (ImageView) itemView.findViewById(R.id.contentPhoto);
            }
        }
    }
}
