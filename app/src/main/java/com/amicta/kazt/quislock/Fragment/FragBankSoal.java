package com.amicta.kazt.quislock.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amicta.kazt.quislock.adapter.RecyclerBPMenuMainAdapter;
import com.amicta.kazt.quislock.model.ModelBPMainMenuItem;
import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.FragmentBankSoalBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 5/11/17.
 */

public class FragBankSoal extends Fragment{
    private FragmentBankSoalBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<ModelBPMainMenuItem> itemsMenu = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bank_soal, container, false);
        layoutManager = new GridLayoutManager(getActivity(),2);
        binding.rvBpMenuBp.setLayoutManager(layoutManager);
        for (int a = 0; a <= 20; a++){
            int c = a * 100000;
            int d = a * 100;
            ModelBPMainMenuItem item = new ModelBPMainMenuItem(a,""+c,d,R.drawable.book);
            itemsMenu.add(item);
        }
        adapter = new RecyclerBPMenuMainAdapter(getActivity(),itemsMenu);
        binding.rvBpMenuBp.setAdapter(adapter);
        return binding.getRoot();
    }
}
