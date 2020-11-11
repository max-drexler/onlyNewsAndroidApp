package com.example.onlynews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

//TODO: Set up other fragments and interactions with news client

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private Toolbar mTopBar;
    private NavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setOnNavigationItemSelectedListener(navListener);
        mBottomNav.setSelectedItemId(R.id.BBfeed);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new FeedFragment()).commit();

        mTopBar = findViewById(R.id.topToolBar);
        mNavView = findViewById(R.id.navigation_view);

        mTopBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNavView.setVisibility(View.VISIBLE);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment newFrag = null;

            switch (item.getItemId()) {
                case R.id.BBfeed:
                    newFrag = new FeedFragment();
                    break;
                case R.id.BBsearch:
                    newFrag = new SearchFragment();
                    break;
                case R.id.BBsettings:
                    newFrag = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, newFrag).commit();
            return true;
        }
    };
}