package com.example.custombottomnavigationview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.custombottomnavigationview.databinding.ActivityMainBinding;
import com.example.custombottomnavigationview.fragment.MoreFragment;
import com.example.custombottomnavigationview.fragment.ProfileFragment;
import com.example.custombottomnavigationview.fragment.ServiceFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding = null;
    private ProfileFragment profileFragment = null;
    private ServiceFragment serviceFragment = null;
    private MoreFragment moreFragment = null;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private View.OnClickListener mOnClickListener = v -> {
        selector(v.getId());
        switch (v.getId()) {
            case R.id.rl_profile:
                loadFragment(profileFragment);
                break;
            case R.id.rl_service:
                loadFragment(serviceFragment);
                break;
            case R.id.rl_more:
                loadFragment(moreFragment);
                break;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        init();

    }

    private void init() {
        profileFragment = new ProfileFragment();
        serviceFragment = new ServiceFragment();
        moreFragment = new MoreFragment();
        manager = getSupportFragmentManager();
        mainBinding.rlProfile.setOnClickListener(mOnClickListener);
        mainBinding.rlService.setOnClickListener(mOnClickListener);
        mainBinding.rlMore.setOnClickListener(mOnClickListener);
        mainBinding.rlProfile.performClick();
    }

    private void loadFragment(Fragment fragment) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl, fragment);
        transaction.commit();
    }

    private void selector(int id) {
        switch (id) {
            case R.id.rl_profile:
                mainBinding.ivProfile.setSelected(true);
                mainBinding.ivService.setSelected(false);
                mainBinding.ivMore.setSelected(false);
                break;
            case R.id.rl_service:
                mainBinding.ivProfile.setSelected(false);
                mainBinding.ivService.setSelected(true);
                mainBinding.ivMore.setSelected(false);
                break;
            case R.id.rl_more:
                mainBinding.ivProfile.setSelected(false);
                mainBinding.ivService.setSelected(false);
                mainBinding.ivMore.setSelected(true);
                break;
        }
    }
}