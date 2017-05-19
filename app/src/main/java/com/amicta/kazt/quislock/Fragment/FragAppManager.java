package com.amicta.kazt.quislock.Fragment;


import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.amicta.kazt.quislock.adapter.AdapterInstalledApp;
import com.amicta.kazt.quislock.model.AppInfo;
import com.amicta.kazt.quislock.services.MainService;
import com.amicta.kazt.quislock.services.SubService;
import com.amicta.kazt.quislock.util.SharedPref;
import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.FragmentAppManagerBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 5/11/17.
 */

public class FragAppManager extends Fragment {
    private FragmentAppManagerBinding binding;
    private List<String> arrDummy = new ArrayList<>();
    private PackageManager pm;
    private List<ApplicationInfo> apps;
    private List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();
    private SharedPref sharedPref;
    private List<AppInfo> listApp = new ArrayList<>();
    private AdapterInstalledApp adapterInstalledApp;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_manager, container, false);
        sharedPref = new SharedPref(getActivity());
        Intent service = new Intent(getActivity(), MainService.class);
        getInstallApp();
        setRecyclerView();
        binding.btnStart.setOnClickListener(v -> {
            sharedPref.setLoop(true);
            sharedPref.setBelajar(false);
            getActivity().startService(service);});
        binding.btnStop.setOnClickListener(v -> {
            sharedPref.setLoop(false);
            getActivity().stopService(service);});
        return binding.getRoot();
    }

    private void setRecyclerView() {
        layoutManager = new LinearLayoutManager(getActivity());
        adapterInstalledApp = new AdapterInstalledApp(getActivity(), listApp);
        binding.listApp.setLayoutManager(layoutManager);
        binding.listApp.setAdapter(adapterInstalledApp);
    }

    private void getInstallApp() {
        int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_UNINSTALLED_PACKAGES;
        pm = getActivity().getPackageManager();
        apps = pm.getInstalledApplications(flags);
        for (ApplicationInfo app : apps){
            if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                // System application
            } else {
                // Installed by user
                arrDummy.add(app.loadLabel(pm).toString());
                installedApps.add(app);
                AppInfo appInfo = new AppInfo();
                appInfo.setPackageApp(app.packageName);
                appInfo.setLabelApp(app.loadLabel(pm).toString());
                appInfo.setIconApp(app.loadIcon(pm));
                listApp.add(appInfo);
            }
        }
    }
}




