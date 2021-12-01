package com.example.giuaky;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CCTTCBActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList<ChiTietTTCB> data = new ArrayList<>();
    ArrayList<MonHoc> dataMH = new ArrayList<>();
    ArrayList<detailCTCB> data2 ;
    detailCTCBAdapter adapter = null;
    TextView tvmaphieu, tvmamh, tvsobai;
    TextView tvbaiso, tvdiem;
    EditText edtDiem;
    Button  btn_luu,btn_thoat, btn_load;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietphieu);


        lvDanhSach = (ListView)findViewById(R.id.lv_ttp);
        tvmaphieu = (TextView)findViewById(R.id.edt_mattp);
        tvmamh = (TextView)findViewById(R.id.edt_mamonttp);
        tvsobai = (TextView)findViewById(R.id.edt_slbaittp);

        tvbaiso = (TextView) findViewById(R.id.tvbais);
        edtDiem =(EditText) findViewById(R.id.edt_diem);

        tvdiem =(TextView)findViewById(R.id.tvdiem);

        btn_luu =(Button)findViewById(R.id.btn_luuttp);
        btn_load=(Button)findViewById(R.id.btn_loadttp);
        btn_thoat =(Button)findViewById(R.id.btn_thoatttp);

        GiaoVienDatabase db = new GiaoVienDatabase(this);
        Intent intent = getIntent();
        String maphieu = intent.getStringExtra("maphieu");
        String tenmh = intent.getStringExtra("tenmh");
        String sobai = intent.getStringExtra("soluongbai");
        String mamh = intent.getStringExtra("mamh");
        String username = intent.getStringExtra("username1");
        tvmaphieu.setText(maphieu);
        tvmamh.setText(mamh);
        tvsobai.setText(sobai);

        db.getAllDatamh(dataMH);

        db.getAllDataChamBai(data,maphieu,mamh);

        data =new ArrayList<ChiTietTTCB>(Integer.parseInt(sobai));
       /* for(int i =1; i<= Integer.parseInt(sobai);i++){
            ChiTietTTCB dt = new ChiTietTTCB();
            for(int j = 0; j< dataMH.size(); j++){
                if(dataMH.get(j).getMa().equals(mamh)==true){
                    dt.setHinhanh(dataMH.get(j).getHinhanh());
                }
            }
            dt.setBaiso(String.valueOf(i));
            dt.setDiem("0");
           data.add(dt);
        }*/
        adapter = new detailCTCBAdapter(this, R.layout.listview_ttp,data);
        lvDanhSach.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CCTTCBActivity.this, ChamBaiActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("maphieu", maphieu);

                db.getAllDataChamBai(data,maphieu,mamh);
                int baidacham =0;
                int baichuacham=0;
                PhieuChamBai dt = new PhieuChamBai();
                for(int i=0; i<data.size();i++) {
                    if (data.get(i).getDiem().equals("0")==true) {
                        baichuacham = baichuacham + 1;

                    } else {
                        baidacham = baidacham + 1;

                    }
                }
                baidacham = baidacham*50/Integer.parseInt(sobai);
                dt.setBaidacham(baidacham);
                baichuacham = baichuacham*50/Integer.parseInt(sobai);
                dt.setBaichuacham(baichuacham);
                dt.setMa(maphieu);
                db.updateDiemPhieuChamBai(dt);
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChiTietTTCB gv = data.get(position);
                tvbaiso.setText(gv.getBaiso());
                edtDiem.setText(gv.getDiem());
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ChiTietTTCB dt = new ChiTietTTCB();
                    dt.setDiem(edtDiem.getText().toString().trim());
                    dt.setMamh(mamh);
                    dt.setSophieu(maphieu);
                    dt.setBaiso(tvbaiso.getText().toString().trim());
                    Update(dt);
                    LoadData();

            }
        });


    }

    private ChiTietTTCB getChiTiet() {
        ChiTietTTCB ct = new ChiTietTTCB();
        ct.setMamh(tvmamh.getText().toString().trim());
        ct.setDiem(edtDiem.getText().toString().trim());
        return ct;
    }

    public void LoadData() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        Intent intent = getIntent();
        String maphieu = intent.getStringExtra("maphieu");
        String mamh = intent.getStringExtra("mamh");
        db.getAllDataChamBai(data,maphieu,mamh);
        adapter.notifyDataSetChanged();
    }
    public void Update(ChiTietTTCB gv) {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        db.updateChamBai(gv);
        LoadData();
    }
}
