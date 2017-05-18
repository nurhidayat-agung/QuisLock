package com.amicta.kazt.quislock.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.amicta.kazt.quislock.model.AppInfo;
import com.amicta.kazt.quislock.util.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazt on 5/16/17.
 */

public class DBAdapter extends SQLiteAssetHelper {
    private Context context;

    public DBAdapter(Context context, int version) {
        super(context, Constant.DBFILE, null, version);
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }

    public void addSelectedPackage(AppInfo appInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.fPACKAGE_SELECT, appInfo.getPackageApp());
        contentValues.put(Constant.fLABEL_SELECT, appInfo.getLabelApp());
        db.insertOrThrow(Constant.SELECT_PACKAGE,"",contentValues);
    }

    public List<AppInfo> getSelectPackage(){
        List<AppInfo> appInfos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Constant.SELECT_PACKAGE,null);
        if (cursor.getCount() == 1){
            cursor.moveToFirst();
            AppInfo appInfo = new AppInfo();
            appInfo.setLabelApp(cursor.getString(cursor.getColumnIndex(Constant.fLABEL_SELECT)));
            appInfo.setPackageApp(cursor.getString(cursor.getColumnIndex(Constant.fPACKAGE_SELECT)));
            appInfos.add(appInfo);
        }else {
            while (cursor.moveToNext()){
                AppInfo appInfo = new AppInfo();
                appInfo.setLabelApp(cursor.getString(cursor.getColumnIndex(Constant.fLABEL_SELECT)));
                appInfo.setPackageApp(cursor.getString(cursor.getColumnIndex(Constant.fPACKAGE_SELECT)));
                appInfos.add(appInfo);
            }
        }
        cursor.close();
        return appInfos;
    }

    public boolean isTherePackage(String packageName){
        boolean backValue;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Constant.SELECT_PACKAGE+" WHERE "+Constant.fPACKAGE_SELECT+" like '"+packageName+"';",null);
        if (cursor.getCount() >= 1){
            backValue = true;
        }else {
            backValue = false;
        }
        cursor.close();
        return backValue;
    }

    public void rmvSelectedPackage(String label){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from select_package where label_select like '"+label+"';");
    }


}
