package com.amicta.kazt.quislock.view;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.amicta.kazt.quislock.model.LoginModel;
import com.amicta.kazt.quislock.util.SharedPref;
import com.example.kazt.quislock.R;
import com.example.kazt.quislock.databinding.ActivitySplashScreenBinding;
import com.example.kazt.quislock.databinding.LoginPopUpBinding;
import com.example.kazt.quislock.databinding.SetPassPopUpBinding;

public class SplashScreen extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private SharedPref sharedPref;
    private Dialog setPass;
    private Dialog login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(SplashScreen.this,R.layout.activity_splash_screen);
        sharedPref = new SharedPref(SplashScreen.this);
        new Handler().postDelayed(() -> {
            if (sharedPref.isFirst()){
                spawnSetPass();
            }else {
                spawnLogin();
            }
        },SPLASH_DISPLAY_LENGTH);

    }

    public void spawnLogin() {
        login = new Dialog(SplashScreen.this);
        login.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LoginPopUpBinding binding = LoginPopUpBinding.inflate(LayoutInflater.from(SplashScreen.this), null);
        login.setContentView(binding.getRoot());
        login.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = login.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        login.setCancelable(false);
        login.setCanceledOnTouchOutside(false);
        binding.btnLogin.setOnClickListener(v -> {
            LoginModel loginModel = new LoginModel();
            loginModel.setUsername(binding.edtUsername.getText().toString());
            loginModel.setPassword(binding.edtPassword.getText().toString());
            if (sharedPref.isPass(loginModel))
            {
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                login.dismiss();
                SplashScreen.this.finish();
            }
        });
        binding.btnMenu.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            SplashScreen.this.startActivity(i);
            login.dismiss();
            finish();
        });
        login.show();
    }

    private void spawnSetPass() {
        setPass = new Dialog(SplashScreen.this);
        setPass.requestWindowFeature(Window.FEATURE_NO_TITLE);
        SetPassPopUpBinding binding = SetPassPopUpBinding.inflate(LayoutInflater.from(SplashScreen.this),null);
        setPass.setContentView(binding.getRoot());
        setPass.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Window window = setPass.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        setPass.setCancelable(false);
        setPass.setCanceledOnTouchOutside(false);
        binding.btnMenu.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            SplashScreen.this.startActivity(i);
            setPass.dismiss();
            finish();
        });
        binding.btnSetPass.setOnClickListener(v -> {
            if (binding.edtPassword.getText().toString().equalsIgnoreCase(binding.edtKonfirmasi.getText().toString())){
                LoginModel loginModel = new LoginModel();
                loginModel.setUsername(binding.edtUsername.getText().toString());
                loginModel.setPassword(binding.edtPassword.getText().toString());
                sharedPref.setPass(loginModel);
                sharedPref.setFirst(false);
                spawnLogin();
                Toast.makeText(this, "password tersimpan", Toast.LENGTH_SHORT).show();
                setPass.dismiss();
            }else {
                Toast.makeText(this, "konfirmasi password salah", Toast.LENGTH_SHORT).show();
            }

        });
        setPass.show();
    }
}
