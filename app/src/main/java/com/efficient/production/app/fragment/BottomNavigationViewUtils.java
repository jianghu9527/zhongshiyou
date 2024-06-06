package com.efficient.production.app.fragment;


import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class BottomNavigationViewUtils {


    private FragmentManager fragmentManager;
    private int containerId;
    private SparseArray<Fragment> fragments = new SparseArray<>();
    private Fragment currentFragment;

    public BottomNavigationViewUtils(FragmentManager fragmentManager, int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public void addFragment(int menuItemId, Fragment fragment) {
        fragments.put(menuItemId, fragment);
    }

    public void setupWithBottomNavigationView(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = fragments.get(item.getItemId());
            if (fragment != null) {
                switchFragment(fragment);
                return true;
            }
            return false;
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(containerId, fragment);
        }

        currentFragment = fragment;
        transaction.commit();
    }

}
