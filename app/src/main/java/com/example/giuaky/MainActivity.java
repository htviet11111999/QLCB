package com.example.giuaky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //khai báo các widget
    EditText user, pass;
    Button login;

    ArrayList<String> datamaGV = new ArrayList<>();
    ArrayList<String> datapassGV = new ArrayList<>();
    //error

    static final String USER_NAME = "admin";
    static final String PASS_WORD = "123";
    //sign in success
    static final String LOG_IN_SUCCESS = "Log in success";
    static final String LOG_IN_FAIL = "Fail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.btn_login);

        GiaoVienDatabase db = new GiaoVienDatabase(this);

        datamaGV.clear();
        datapassGV.clear();
        db.getAllMaGV(datamaGV);
        db.getAllPassGV(datapassGV);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().equals(USER_NAME) && pass.getText().toString().equals(PASS_WORD)){
                    Toast.makeText(MainActivity.this, LOG_IN_SUCCESS,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, LOG_IN_FAIL,Toast.LENGTH_SHORT).show();
                }

                int i =0;
                while (i < datamaGV.size()){
                    if (user.getText().toString().equals(datamaGV.get(i)) && pass.getText().toString().equals(datapassGV.get(i)) ){
                        Toast.makeText(MainActivity.this, LOG_IN_SUCCESS,Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, ChamBaiActivity.class);
                        intent1.putExtra("username",user.getText().toString());
                        startActivity(intent1);
                        break;
                    }
                    else {
                        i++;
                        Toast.makeText(MainActivity.this, LOG_IN_FAIL,Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
    }
    

}