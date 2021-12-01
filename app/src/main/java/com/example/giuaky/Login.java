package com.example.giuaky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button btn_qlgv, btn_qlmh, btn_giaobai, btn_thoat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn_qlgv = (Button)findViewById(R.id.btn_qlgv);
        btn_qlmh = (Button)findViewById(R.id.btn_qlmh);
        btn_giaobai = (Button)findViewById(R.id.btn_giaobai);
        btn_thoat = (Button)findViewById(R.id.btn_thoat);

        btn_qlgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, GiaoVienActivity.class);
                startActivity(intent);
            }
        });

        btn_qlmh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MonHocActivity.class);
                startActivity(intent);
            }
        });

        btn_giaobai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, PhieuChamBaiActivity.class);
                startActivity(intent);
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
