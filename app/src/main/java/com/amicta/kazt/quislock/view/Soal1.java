package com.amicta.kazt.quislock.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kazt.quislock.R;

public class Soal1 extends AppCompatActivity {
    private Button btn1;
    private EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_soal1);
        btn1 = (Button) findViewById(R.id.btn_soal1);
        edt1 = (EditText) findViewById(R.id.edt_soal1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1.getText().toString().equalsIgnoreCase("jokowi")){
                    Toast.makeText(Soal1.this, "selamat jawaban kamu benar", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Soal1.this, Soal2.class));
                    finish();
                }else {
                    Toast.makeText(Soal1.this, "masa gitu aja salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
