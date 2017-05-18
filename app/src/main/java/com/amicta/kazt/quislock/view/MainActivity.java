package com.amicta.kazt.quislock.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;

import com.amicta.kazt.quislock.Fragment.FragAppManager;
import com.amicta.kazt.quislock.Fragment.FragBankSoal;
import com.amicta.kazt.quislock.Fragment.FragSetting;
import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SectionsPagerAdapter mSectionsPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        binding.container.setAdapter(mSectionsPagerAdapter);
        binding.tabs.setupWithViewPager(binding.container);

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0){
                fragment = new FragAppManager();
            }else if (position == 1){
                fragment = new FragBankSoal();
            }else if (position == 2){
                fragment = new FragSetting();
            }else {
                fragment = null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Application Manager";
                case 1:
                    return "Bank Soal";
                case 2:
                    return "setting";
            }
            return null;
        }
    }
}
