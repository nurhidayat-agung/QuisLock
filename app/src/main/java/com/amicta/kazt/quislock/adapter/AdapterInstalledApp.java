package com.amicta.kazt.quislock.adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amicta.kazt.quislock.model.AppInfo;
import com.example.kazt.quislock.databinding.InflateInstalledAppBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 5/17/17.
 */

public class AdapterInstalledApp extends RecyclerView.Adapter<AdapterInstalledApp.MyViewHolder> {
    private LayoutInflater inflater;
    private InflateInstalledAppBinding binding;
    private Context context;
    private List<AppInfo> applicationInfos = new ArrayList<>();
    private DBAdapter dbAdapter;


    public AdapterInstalledApp(Context context, List<AppInfo> applicationInfos) {
        this.context = context;
        this.applicationInfos = applicationInfos;
        this.dbAdapter = new DBAdapter(this.context,1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        binding = InflateInstalledAppBinding.inflate(inflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AppInfo applicationInfo = applicationInfos.get(position);
        holder.bind(applicationInfo);
        if (dbAdapter.isTherePackage(applicationInfo.getPackageApp())){
            holder.binding.cbxSelectPackage.setChecked(true);
        }else {
            holder.binding.cbxSelectPackage.setChecked(false);
        }
        holder.binding.cbxSelectPackage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                dbAdapter.addSelectedPackage(applicationInfo);
            }else {
                dbAdapter.rmvSelectedPackage(applicationInfo.getLabelApp());
            }
        });
    }


    @Override
    public int getItemCount() {
        return applicationInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final InflateInstalledAppBinding binding;

        public MyViewHolder(InflateInstalledAppBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(AppInfo applicationInfo){
            binding.setAppInfo(applicationInfo);
            binding.executePendingBindings();
        }
    }

}
