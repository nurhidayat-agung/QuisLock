package com.amicta.kazt.quislock.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.FragmentSettingBinding;

/**
 * Created by kazt on 5/11/17.
 */

public class FragSetting extends Fragment{
    FragmentSettingBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);


        return binding.getRoot();
    }
}
