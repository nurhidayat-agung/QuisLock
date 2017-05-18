package com.amicta.kazt.quislock.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.LayoutPopupLockBinding;

/**
 * Created by kazt on 5/10/17.
 */

public class PopUp extends Activity {
    private Dialog dialog;
    private LayoutPopupLockBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = LayoutPopupLockBinding.inflate(LayoutInflater.from(this),null);
        dialog.setContentView(binding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        dialog.setCanceledOnTouchOutside(false);
        binding.btnPopupBpKonfirmasiYa.setOnClickListener(v -> startActivity(new Intent(this, Soal1.class)));
        binding.btnPopupBpKonfirmasiTidak.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            PopUp.this.startActivity(i);
            finish();
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        PopUp.this.startActivity(i);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("cek","this is onstart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cek","this is onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("cek","this is onStop");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("cek","this is onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cek","this is onDestroy");

    }
}
