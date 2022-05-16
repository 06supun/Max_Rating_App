package com.example.max_rating_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.max_rating_app.GamesFragment;
import com.example.max_rating_app.LoginActivity;
import com.example.max_rating_app.MoviesFragment;
import com.example.max_rating_app.R;
import com.example.max_rating_app.VideosFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button btn_logout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private GamesFragment gamesFragment;
    private VideosFragment videosFragment;
    private MoviesFragment moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btn_logout = findViewById(R.id.btn_logout);
        toolbar = findViewById(R.id.toolbar_main);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        // fragment pages set
        gamesFragment = new GamesFragment();
        moviesFragment = new MoviesFragment();
        videosFragment = new VideosFragment();
        // set view page to the tab
        tabLayout.setupWithViewPager(viewPager);

        // set adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(gamesFragment, "Games");
        viewPagerAdapter.addFragment(moviesFragment, "Movies");
        viewPagerAdapter.addFragment(videosFragment, "Videos");

        viewPager.setAdapter(viewPagerAdapter);

        // logout button
        btn_logout.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });
    }

//view pager adapter inner class

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        protected List<String> fragments_titles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        // adding fragment

        public void addFragment(Fragment fragment, String titles) {
            fragments.add(fragment);
            fragments_titles.add(titles);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragments_titles.get(position);
        }
    }
}
