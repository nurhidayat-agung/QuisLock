package com.amicta.kazt.quislock.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

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
        dialog.show();
    }
}
