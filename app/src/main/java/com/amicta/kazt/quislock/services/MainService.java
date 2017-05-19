package com.amicta.kazt.quislock.services;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.amicta.kazt.quislock.adapter.DBAdapter;
import com.amicta.kazt.quislock.view.PopUp;
import com.amicta.kazt.quislock.util.SharedPref;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kazt on 5/12/17.
 */

public class MainService extends Service {
    private SharedPref sharedpref;
    List<String> listPackage = new ArrayList<>();
    private DBAdapter dbAdapter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (sharedpref.getLoop()){
            onTaskRemoved(intent);
        }
        listPackage.clear();
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> taskInfo = am.getRunningAppProcesses();
        //Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
        String aaa = taskInfo.get(0).processName;
        Log.d("cek", "package : " + aaa);
        if (dbAdapter.isTherePackage(aaa) && !sharedpref.isBelajar()){
            Intent goLock = new Intent(this, PopUp.class);
            goLock.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            goLock.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(goLock);
        }

        //Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Sistem Pengunci Dijalankan", Toast.LENGTH_LONG).show();
        dbAdapter = new DBAdapter(this,1);
        sharedpref = new SharedPref(this);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Sistem Penunci Dimatikan", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restart = new Intent(getApplicationContext(), this.getClass());
        restart.setPackage(getPackageName());
        startService(restart);
        //Toast.makeText(this, "ontaskremove", Toast.LENGTH_SHORT).show();

        super.onTaskRemoved(rootIntent);
    }
}



//        listPackage = sharedpref.getPackage();
//        for (int a = 0; a < listPackage.size(); a++){
//            Log.d("cek", "daftar list package ke-"+a+" : " + listPackage.get(a));
//            if (listPackage.get(a).equalsIgnoreCase(aaa)){
//                if (!sharedpref.isBelajar()){
//                    Intent goLock = new Intent(this, PopUp.class);
//                    goLock.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(goLock);
//                }
//            }
//        }