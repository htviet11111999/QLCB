package com.example.giuaky;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PhieuChamBaiAdapter extends ArrayAdapter<PhieuChamBai> {

    Context context;
    int layoutResouredId;
    ArrayList<PhieuChamBai> data= null;
    ArrayList<GiaoVien> datagv = new ArrayList<>();
    GiaoVienDatabase db;

    public PhieuChamBaiAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<PhieuChamBai> data) {
        super(context, layoutResouredId, data);
        this.context = context;
        this.layoutResouredId = layoutResouredId;
        this.data = data;
    }
    static class PhieuChamBaiHolder{
        TextView tv_sophieu, tv_ngaygiao, tv_tengv ,tv_tenmh, soluongbai;
        Button btn_xoa;
        PieChart pieChart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PhieuChamBaiHolder holder = null;
        if (row != null){
            holder = (PhieuChamBaiHolder) row.getTag();
        }
        else{

            holder = new PhieuChamBaiHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_giaobai,parent,false);

            holder.tv_sophieu =(TextView)row.findViewById(R.id.sophieu);
            holder.tv_ngaygiao =(TextView)row.findViewById(R.id.ngaygiao);
            holder.tv_tengv =(TextView)row.findViewById(R.id.tengvgb);
            holder.tv_tenmh =(TextView)row.findViewById(R.id.tenmhgb);
            holder.soluongbai =(TextView)row.findViewById(R.id.soluongbai);
            holder.btn_xoa =(Button) row.findViewById(R.id.btn_xoagb);
            holder.pieChart=(PieChart)row .findViewById(R.id.pieChartGB);
            row.setTag(holder);
        }
        final PhieuChamBai pcb = data.get(position);
        ArrayList<PieEntry> slb = new ArrayList<>();
        holder.tv_sophieu.setText("Số phiếu: "+pcb.getMa());
        holder.tv_ngaygiao.setText("Ngày giao bài: "+pcb.getNgaygb());
        holder.tv_tengv.setText("Mã giáo viên: "+pcb.getMagv());
        holder.tv_tenmh.setText("Mã môn học: "+pcb.getMamh());
        holder.soluongbai.setText("Số lượng bài: "+pcb.getSoluongbai());
        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoVienDatabase db = new GiaoVienDatabase(context);
                db.deletePCB(pcb.getMa());
                Toast.makeText(context,"Đã xóa mã "+pcb.getMa(),
                        Toast.LENGTH_SHORT).show();
                db.deleteChamBai(pcb.getMa(),pcb.getMamh());
            }
        });
        GiaoVienDatabase db = new GiaoVienDatabase(context);

        slb.add(new PieEntry(pcb.getBaidacham(),"Đã chấm"));
        slb.add(new PieEntry(pcb.getBaichuacham(),"Chưa chấm"));

        PieDataSet pieDataSet = new PieDataSet(slb,"Thống kê bài chấm");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);

        PieData pieData = new PieData(pieDataSet);
        holder.pieChart.setData(pieData);
        holder.pieChart.getDescription().setEnabled(false);
        holder.pieChart.setCenterText("Bài chấm");
        holder.pieChart.animate();

        return row;
    }
}
