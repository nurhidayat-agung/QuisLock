package com.amicta.kazt.quislock.Fragment;


import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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
    private ArrayAdapter<String> adapter = null;
    private PackageManager pm;
    private List<ApplicationInfo> apps;
    private List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();
    private List<ApplicationInfo> systemApps = new ArrayList<ApplicationInfo>();
    private List<ApplicationInfo> factoryApps = new ArrayList<ApplicationInfo>();
    private List<ApplicationInfo> selectedApp = new ArrayList<ApplicationInfo>();
    private SharedPref sharedPref;
    private List<String> listLabel = new ArrayList<>();
    private List<String> listPackage = new ArrayList<>();
    private List<Integer> listPostion = new ArrayList<>();
    private List<Boolean> selectedFlag = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_app_manager, container, false);
        sharedPref = new SharedPref(getActivity());
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
            }
        }
        for (int a = 0; a < arrDummy.size(); a++){
            selectedFlag.add(false);
        }
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_checked, arrDummy);
        binding.listApp.setAdapter(adapter);
        listLabel = sharedPref.getLabel();
        listPackage = sharedPref.getPackage();
        for (int a = 0; a < listPackage.size(); a++){
            for (int b = 0; b < installedApps.size(); b++){
                String pos = installedApps.get(b).packageName;
                if (listPackage.get(a).equalsIgnoreCase(pos)){
                    listPostion.add(a,b);
                }
            }
        }
        for (int c = 0; c < listPostion.size(); c++){
            binding.listApp.setItemChecked(listPostion.get(c), true);
            selectedFlag.add(listPostion.get(c), true);
        }

        binding.listApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray sparseBooleanArray = binding.listApp.getCheckedItemPositions();
                System.out.println("Clicked Position := "+position +" Value: "+sparseBooleanArray.get(position));
                if (sparseBooleanArray.get(position)){
                    selectedApp.add(installedApps.get(position));
                    selectedFlag.add(position, true);
                }else {
                    selectedApp.remove(installedApps.get(position));
                    selectedFlag.add(position, false);
                }
                for (int a = 0; a < selectedApp.size(); a++){
                    Log.d("cek", "isi selected app ke-"+ a + " : " + selectedApp.get(a).loadLabel(pm));
                }
            }
        });

        binding.btnKunci.setOnClickListener(v -> {
            listPackage.clear();
            listLabel.clear();
            int aw = 0;
            for (boolean aa : selectedFlag){
                if (selectedFlag.get(aw)){
                    listLabel.add(installedApps.get(aw).loadLabel(pm).toString());
                    listPackage.add(installedApps.get(aw).packageName);
                }
                aw++;
            }
            sharedPref.saveLabel(listLabel);
            sharedPref.savePackage(listPackage);
        });

        binding.btnRefresh.setOnClickListener(v -> {
            for (int a = 0; a < arrDummy.size(); a++){
                binding.listApp.setItemChecked(a, false);
            }
            listPackage.clear();
            listLabel.clear();
            sharedPref.clearData();
        });

        binding.btnStart.setOnClickListener(v -> {
            getActivity().startService(new Intent(getActivity(), MainService.class));
        });
        binding.btnStop.setOnClickListener(v -> {
            getActivity().stopService(new Intent(getActivity(),MainService.class));
        });
        return binding.getRoot();
    }
}


