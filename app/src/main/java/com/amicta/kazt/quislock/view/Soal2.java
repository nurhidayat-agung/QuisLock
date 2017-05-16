package com.amicta.kazt.quislock.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amicta.kazt.quislock.util.SharedPref;
import com.example.kazt.quislock.R;

public class Soal2 extends AppCompatActivity {
    private Button btn2;
    private EditText edt2;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal2);
        sharedPref = new SharedPref(this);
        btn2 = (Button) findViewById(R.id.btn_soal2);
        edt2 = (EditText) findViewById(R.id.edt_soal2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt2.getText().toString().equalsIgnoreCase("rasul")){
                    Toast.makeText(Soal2.this, "Selamat jawaban kamu bener", Toast.LENGTH_SHORT).show();
                    sharedPref.setBelajar(true);
                    finish();
                }else {
                    Toast.makeText(Soal2.this, "Salah ayo di pikir lagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
