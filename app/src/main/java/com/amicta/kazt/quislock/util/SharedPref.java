package com.amicta.kazt.quislock.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.amicta.kazt.quislock.model.LoginModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 5/13/17.
 */

public class SharedPref {
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    final String KEY_IS_LOGGED_IN = "isLoggedIn";
    final String LABEL_SIZE = "label_size";
    final String PACKAGE_SIZE = "pancakge_size";
    final String BOOL_BELAJAR = "isbelajar";
    final String USERNAME = "username";
    final String PASSWORD = "password";
    final String ISFIRSTUSE = "isfirstuse";
    final String ISLOOP = "isloop";

    public SharedPref(Context context) {
        sp = context.getSharedPreferences("quislock_app", Context.MODE_PRIVATE);
        spe = sp.edit();
    }

    public boolean getLoop(){
        return sp.getBoolean(ISLOOP, true);
    }

    public void setLoop(boolean loop){
        spe.putBoolean(ISLOOP, loop);
        spe.commit();
    }

    public void setBelajar(boolean sudah){
        spe.putBoolean(BOOL_BELAJAR, sudah);
        spe.commit();
    }

    public void setFirst(boolean first){
        spe.putBoolean(ISFIRSTUSE, first);
        spe.commit();

    }

    public boolean isFirst(){
        return sp.getBoolean(ISFIRSTUSE, true);
    }

    public boolean isBelajar(){
        return sp.getBoolean(BOOL_BELAJAR,false);
    }

    public void setIsLoggedIn(boolean isLoggedIn){
        spe.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        spe.commit();
    }

    public boolean isLoggedIn(){
        return sp.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void setPass(LoginModel loginModel){
        spe.putString(USERNAME, loginModel.getUsername());
        spe.putString(PASSWORD, loginModel.getPassword());
        spe.commit();
    }

    public boolean isPass(LoginModel loginModel){
        if (loginModel.getUsername().equalsIgnoreCase(sp.getString(USERNAME,""))){
            if (loginModel.getPassword().equalsIgnoreCase(sp.getString(PASSWORD,""))){
                return true;
            }
        }
        return false;
    }

    public boolean saveLabel(List<String> pushLabel){
        boolean cek = false;
        for (int a = 0; a < pushLabel.size(); a++){
            spe.putString("label"+a, pushLabel.get(a));
            if (a == (pushLabel.size()-1))cek =  true;
        }
        spe.putInt(LABEL_SIZE, pushLabel.size());
        spe.commit();
        return cek;
    }

    public List<String> getLabel(){
        List<String> labels = new ArrayList<>();
        int b = sp.getInt(LABEL_SIZE,0);
        for (int a = 0; a < b; a++){
            String label = sp.getString("label"+a, "");
            labels.add(label);
        }
        return labels;
    }

    public boolean savePackage(List<String> pushPackage){
        boolean cek = false;
        for (int a = 0; a < pushPackage.size(); a++){
            spe.putString("package"+a, pushPackage.get(a));
            if (a == (pushPackage.size()-1)) cek = true;
        }
        spe.putInt(PACKAGE_SIZE, pushPackage.size());
        spe.commit();
        return cek;
    }

    public List<String> getPackage(){
        List<String> packages = new ArrayList<>();
        int b = sp.getInt(PACKAGE_SIZE,0);
        for (int a = 0; a < b; a++){
            String packagee = sp.getString("package"+a, "");
            packages.add(packagee);
        }
        return packages;
    }

    public boolean clearData(){
        spe.putInt(LABEL_SIZE, 0);
        spe.putInt(PACKAGE_SIZE, 0);
        return true;
    }
}
