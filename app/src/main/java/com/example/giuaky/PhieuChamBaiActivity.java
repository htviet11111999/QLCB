package com.example.giuaky;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PhieuChamBaiActivity extends AppCompatActivity {
    private ListView lvDanhSach;
    ArrayList<PhieuChamBai> data = new ArrayList<>();
    ArrayList<MonHoc> datamh = new ArrayList<>();
    PhieuChamBaiAdapter adapter = null;
    Button btn_them, btn_thoat, btn_load;
    EditText txtSophieu, txtSoluongbai;
    ArrayList<String> dataGV = new ArrayList<>();
    String getMaGV;

    ArrayList<String> dataMH = new ArrayList<>();
    ArrayList<GiaoBaiSpinner> arrayList = new ArrayList<GiaoBaiSpinner>();
    ArrayList<GiaoVien> datagv = new ArrayList<>();


    TextView tvi_tengv, tvi_tenmh;
    EditText edt_ngaygb;
    Spinner spn_gv;
    Spinner spn_mh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaobai);

        GiaoVienDatabase db = new GiaoVienDatabase(this);
        dataGV.clear();
        dataMH.clear();
        db.getAllMaGV(dataGV);
        db.getAllMaMH(dataMH);
        db.getAllData(datagv);
        db.getAllDatamh(datamh);


        tvi_tengv = (TextView) findViewById(R.id.tvi_tengv);
        tvi_tenmh = (TextView) findViewById(R.id.tvi_tenmh);
        lvDanhSach = (ListView) findViewById(R.id.lsvShow);
        txtSophieu = (EditText) findViewById(R.id.edt_sophieu);
        txtSoluongbai = (EditText) findViewById(R.id.edt_soluongbai);
        btn_them = (Button) findViewById(R.id.btn_giaobai);
        btn_thoat = (Button) findViewById(R.id.btn_thoatgb);
        btn_load = (Button) findViewById(R.id.btn_loadgb);
        edt_ngaygb = (EditText) findViewById(R.id.edt_ngaygb);
        spn_gv = (Spinner) findViewById(R.id.spn_giaovien);
        spn_mh = (Spinner) findViewById(R.id.spn_mon);
        edt_ngaygb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        for (int i = 0; i < datagv.size(); i++) {
            arrayList.add(new GiaoBaiSpinner(datagv.get(i).getHinhanh(), datagv.get(i).getMa()));
        }
        GiaoBaiSpinnerAdapter adapterGV = new GiaoBaiSpinnerAdapter(this, R.layout.dong_gv, arrayList);
        spn_gv.setAdapter(adapterGV);
        spn_gv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvi_tengv.setText(db.getTenGV(dataGV.get(position)));
                getMaGV = arrayList.get(position).getMagv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapterMH = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataMH);
        adapterMH.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spn_mh.setAdapter(adapterMH);
        spn_mh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvi_tenmh.setText(db.getTenMH(dataMH.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adapter = new PhieuChamBaiAdapter(this, R.layout.listview_giaobai, data);
        lvDanhSach.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nhap();
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
                PhieuChamBai pcb = data.get(position);
                txtSophieu.setText(pcb.getMa());
                txtSoluongbai.setText(pcb.getSoluongbai());
                edt_ngaygb.setText(pcb.getNgaygb());
                spn_gv.setSelection(position);
                spn_mh.setSelection(position);
                Toast.makeText(PhieuChamBaiActivity.this, pcb.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhieuChamBaiActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void ChonNgay() {
        Calendar cal = Calendar.getInstance();
        int ngay = cal.get(Calendar.DATE);
        int thang = cal.get(Calendar.MONTH);
        int nam = cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        cal.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edt_ngaygb.setText(simpleDateFormat.format(cal.getTime()));
                    }
                }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private PhieuChamBai getPhieuChamBai() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        PhieuChamBai pcb = new PhieuChamBai();
        pcb.setMa(txtSophieu.getText().toString().trim());
        pcb.setSoluongbai(txtSoluongbai.getText().toString().trim());
        pcb.setNgaygb(edt_ngaygb.getText().toString().trim());
        pcb.setMagv(getMaGV.trim());
        pcb.setMamh(spn_mh.getSelectedItem().toString());
        pcb.setBaidacham(0);
        pcb.setBaichuacham(0);
        return pcb;
    }

    public void LoadData() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        data.clear();
        db.getAllDataPCB(data);
        adapter.notifyDataSetChanged();
    }

    public void Nhap() {
        GiaoVienDatabase db = new GiaoVienDatabase(this);
        PhieuChamBai pcb = getPhieuChamBai();
        db.savePCB(pcb);
        for (int i = 1; i <= Integer.parseInt(txtSoluongbai.getText().toString().trim()); i++) {
            ChiTietTTCB dt = new ChiTietTTCB();
            dt.setMamh(spn_mh.getSelectedItem().toString());
            for (int j = 0; j < datamh.size(); j++) {
                if (datamh.get(j).getMa().equals(spn_mh.getSelectedItem().toString()) == true) {
                    dt.setHinhanh(datamh.get(j).getHinhanh());
                }
            }
            dt.setSophieu(txtSophieu.getText().toString().trim());
            dt.setBaiso(String.valueOf(i));
            dt.setDiem("0");
            db.saveCT(dt);
        }

    }


}
