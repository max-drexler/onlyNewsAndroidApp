package com.example.onlynews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.onlynews.util.NewsArticle;
import com.example.onlynews.views.ArticleView;

public class FeedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ScrollView sv = getView().findViewById(R.id.feedScrollView);
        LinearLayout ll = getView().findViewById(R.id.feedLinLayout);
        ll.setPadding(10, 10, 10, 10);
        sv.setVerticalScrollBarEnabled(false);
        sv.setSmoothScrollingEnabled(true);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 700);
        layoutParams.setMargins(0, 20, 0, 20);

        ArticleView av = new ArticleView(getContext(), new NewsArticle(null, null, "Biden Transition Live Updates: More Republicans Acknowledge Trump's Loss", null, null, null, null, "content"));
        for (int i = 0; i < 10; i++) {
            av.setPadding(10, 20, 10, 10);
            ll.addView(av, layoutParams);
            av = new ArticleView(getContext(), new NewsArticle(null, null, "Biden Transition Live Updates: More Republicans Acknowledge Trump's Loss", null, null, null, null, "content"));
        }
    }
}
