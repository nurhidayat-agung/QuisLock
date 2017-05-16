package com.amicta.kazt.quislock.services;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

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
    private boolean canLoop = true;

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        listPackage.clear();
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> taskInfo = am.getRunningAppProcesses();
        //Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
        String aaa = taskInfo.get(0).processName;
        Log.d("cek", "package : " + aaa);
        listPackage = sharedpref.getPackage();
        for (int a = 0; a < listPackage.size(); a++){
            Log.d("cek", "daftar list package ke-"+a+" : " + listPackage.get(a));
            if (listPackage.get(a).equalsIgnoreCase(aaa)){
                if (!sharedpref.isBelajar()){
                    Intent goLock = new Intent(this, PopUp.class);
                    goLock.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(goLock);
                }
            }
        }
        //Toast.makeText(this, "onStartCommand", Toast.LENGTH_SHORT).show();
        onTaskRemoved(intent);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
        sharedpref = new SharedPref(this);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restart = new Intent(getApplicationContext(), this.getClass());
        restart.setPackage(getPackageName());
        startService(restart);
        Toast.makeText(this, "ontaskremove", Toast.LENGTH_SHORT).show();

        super.onTaskRemoved(rootIntent);
    }
}