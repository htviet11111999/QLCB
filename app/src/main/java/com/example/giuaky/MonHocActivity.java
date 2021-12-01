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

public class MonHocActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList<MonHoc> data = new ArrayList<>();
    MonHocAdapter adapter = null;
    Button btn_them, btn_sua, btn_thoat, btn_xoa, btn_load;
    EditText txtMa, txtTen;
    ImageView imgmh , imgupload;
    int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monhoc);
        setControl();
        setEvent();
        imgupload.setOnClickListener(new View.OnClickListener() {
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
                imgmh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setControl() {
        lvDanhSach = (ListView)findViewById(R.id.lv_monhoc);
        txtMa = (EditText)findViewById(R.id.edt_mamh);
        txtTen = (EditText)findViewById(R.id.edt_tenmh);
        imgmh=(ImageView)findViewById(R.id.hinhmhmain);
        imgupload=(ImageView)findViewById(R.id.imgUploadmh);
        btn_them = (Button)findViewById(R.id.btn_themmh);
        btn_sua = (Button)findViewById(R.id.btn_suamh);
        btn_xoa = (Button)findViewById(R.id.btn_xoamh);
        btn_thoat = (Button)findViewById(R.id.btn_thoatmh);
        btn_load = (Button)findViewById(R.id.btn_loadmh);

    }

    public void setEvent() {
        adapter = new MonHocAdapter(this, R.layout.listview_monhoc, data);
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
                Intent intent = new Intent(MonHocActivity.this, Login.class);
                startActivity(intent);
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonHoc monHoc = getMonHoc();
                Update(monHoc.getMa());
                LoadData();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonHoc monHoc = getMonHoc();
                Delete(monHoc.getMa());
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
                MonHoc mh = data.get(position);
                txtMa.setText(mh.getMa());
                txtTen.setText(mh.getTen());
                byte[] outImage = mh.getHinhanh();
                ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                imgmh.setImageBitmap(theImage);
            }
        });
    }

    public void Nhap() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        MonHoc monHoc = getMonHoc();
        db.savemh(monHoc);
    }

    private MonHoc getMonHoc() {
       MonHoc monHoc = new MonHoc();
        monHoc.setMa(txtMa.getText().toString().trim());
        monHoc.setTen(txtTen.getText().toString().trim());
        Bitmap bitmap = ((BitmapDrawable)imgmh.getDrawable()).getBitmap();
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bas);
        byte[] imBytes=bas.toByteArray();
        monHoc.setHinhanh(imBytes);
        return monHoc;
    }

    public void LoadData() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.getAllDatamh(data);
        adapter.notifyDataSetChanged();
    }

    public void Delete(String sMa) {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.deletemh(sMa);
        db.getMonHoc(data);
        adapter.notifyDataSetChanged();
    }

    public void Update(String sMa) {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.deletemh(sMa);
        db.getMonHoc(data);
        adapter.notifyDataSetChanged();
        Nhap();
    }
}