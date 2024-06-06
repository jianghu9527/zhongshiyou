package com.efficient.production.app.fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.efficient.production.app.R;

public class MainFragment extends AppCompatActivity {



    private BottomNavigationViewUtils bottomNavigationViewUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationViewUtils = new BottomNavigationViewUtils(getSupportFragmentManager(), R.id.fragment_container);

        bottomNavigationViewUtils.addFragment(R.id.nav_home, new HomeFragment());
        bottomNavigationViewUtils.addFragment(R.id.nav_dashboard, new DashboardFragment());
        bottomNavigationViewUtils.addFragment(R.id.nav_notifications, new NotificationsFragment());

        bottomNavigationViewUtils.setupWithBottomNavigationView(bottomNavigationView);

        // Optionally, set the initial fragment to be displayed
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("-------------MainFragment---------","---MainFragment------onResume-------------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("-------------MainFragment---------","---MainFragment------onRestart-------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("-------------MainFragment---------","---MainFragment------onDestroy-------------");
    }

}
