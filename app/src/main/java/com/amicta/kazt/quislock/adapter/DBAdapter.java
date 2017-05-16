package com.amicta.kazt.quislock.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.amicta.kazt.quislock.util.Constant;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by kazt on 5/16/17.
 */

public class DBAdapter extends SQLiteAssetHelper {

    public DBAdapter(Context context, String name, String storageDirectory, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constant.DBFILE, storageDirectory, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
