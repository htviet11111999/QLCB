package com.example.giuaky;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChamBaiActivity extends AppCompatActivity {

    private ListView lvDanhSach;
    ArrayList<ChamBai> data = null;
    ArrayList<PhieuChamBai> datapcb = null;
    ArrayList<String> datasop = new ArrayList<>();
    ChamBaiAdapter adapter = null;
    Button btn_thoat, btn_load;
    TextView txtMaGV,txtTen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_giaovien);

        lvDanhSach = (ListView)findViewById(R.id.lv_chambai);
        btn_load = (Button) findViewById(R.id.btn_loadcb);
        btn_thoat = (Button) findViewById(R.id.btn_thoatcb);
        txtMaGV = (TextView) findViewById(R.id.edt_magv);
        txtTen = (TextView) findViewById(R.id.edt_hotengv);
        GiaoVienDatabase db = new GiaoVienDatabase(this);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");


        txtMaGV.setText(username);
        txtTen.setText(db.getTenGV(username));
        db.getSLphieu(datasop,username);

        data = new ArrayList<>(datasop.size());

        adapter = new ChamBaiAdapter(this, R.layout.listview_lggv, data);
        lvDanhSach.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChamBaiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                db.getAllDataCB(data,username);
                adapter.notifyDataSetChanged();
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChamBaiActivity.this, CCTTCBActivity.class);
                intent.putExtra("maphieu",data.get(position).getSophieu());
                intent.putExtra("tenmh", db.getTenMH(data.get(position).getMamh()));
                intent.putExtra("mamh",data.get(position).getMamh());
                intent.putExtra("soluongbai",data.get(position).getSoluongbai());
                intent.putExtra("username1",username);
                startActivity(intent);
            }
        });
    }

}
