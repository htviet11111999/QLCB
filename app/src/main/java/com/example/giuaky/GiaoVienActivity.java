package com.example.giuaky;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class GiaoVienActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList<GiaoVien> data = new ArrayList<>();
    GiaoVienAdapter adapter = null;
    Button btn_them, btn_sua, btn_thoat, btn_xoa, btn_load;
    EditText txtMa,txtMk, txtTen;
    ImageView imghinhgv, imgUpload;
    int REQUEST_CODE_FOLDER = 456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaovien);

        setControl();
        setEvent();
        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imghinhgv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setControl() {
        lvDanhSach = (ListView)findViewById(R.id.lv_giaovien);
        txtMa = (EditText)findViewById(R.id.edt_ma);
        txtMk = (EditText)findViewById(R.id.edt_mk);
        txtTen = (EditText)findViewById(R.id.edt_ten);
        imghinhgv = (ImageView) findViewById(R.id.hinhgvmain);
        imgUpload = (ImageView) findViewById(R.id.imgUpload);
        btn_them = (Button)findViewById(R.id.btn_them);
        btn_sua = (Button)findViewById(R.id.btn_sua);
        btn_xoa = (Button)findViewById(R.id.btn_xoa);
        btn_thoat = (Button)findViewById(R.id.btn_thoat);
        btn_load = (Button)findViewById(R.id.btn_load);
    }

    public void setEvent() {
        adapter = new GiaoVienAdapter(this, R.layout.listview_giaovien, data);
        lvDanhSach.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nhap();
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoVienActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoVien giaoVien = getGiaoVien();
                Update(giaoVien.getMa());
                LoadData();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoVien giaoVien = getGiaoVien();
                Delete(giaoVien.getMa());
                LoadData();
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiaoVien gv = data.get(position);
                txtMa.setText(gv.getMa());
                txtMk.setText(gv.getMk());
                txtTen.setText(gv.getTen());
                byte[] outImage = gv.getHinhanh();
                ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                imghinhgv.setImageBitmap(theImage);
            }
        });
    }

    public void Nhap() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        GiaoVien giaoVien = getGiaoVien();
        db.save(giaoVien);
    }

    private GiaoVien getGiaoVien() {
        GiaoVien giaoVien = new GiaoVien();
        giaoVien.setMa(txtMa.getText().toString().trim());
        giaoVien.setMk(txtMk.getText().toString().trim());
        giaoVien.setTen(txtTen.getText().toString().trim());
        Bitmap bitmap = ((BitmapDrawable)imghinhgv.getDrawable()).getBitmap();
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bas);
        byte[] imBytes=bas.toByteArray();
        giaoVien.setHinhanh(imBytes);
        return giaoVien;
    }

    public void LoadData() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.getAllData(data);
        adapter.notifyDataSetChanged();
    }

    public void Delete(String sMa) {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.delete(sMa);
        db.getGiaoVien(data);
        adapter.notifyDataSetChanged();
    }

    public void Update(String sMa) {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.delete(sMa);
        db.getGiaoVien(data);
        adapter.notifyDataSetChanged();
        Nhap();
    }
}