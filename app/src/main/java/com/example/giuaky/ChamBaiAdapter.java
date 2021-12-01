package com.example.giuaky;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChamBaiAdapter extends ArrayAdapter<ChamBai> {

    Context context;
    int layoutResouredId;
    ArrayList<ChamBai> data= null;


    public ChamBaiAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<ChamBai> data ) {
        super(context, layoutResouredId, data);
        this.context = context;
        this.layoutResouredId = layoutResouredId;
        this.data = data;

    }
    static class ChamBaiHolder{
        TextView tvsophieu , tvmamh, soluongbai;
        PieChart pieChart;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ChamBaiHolder holder = null;
        if (row != null){
            holder = (ChamBaiHolder)row.getTag();
        }
        else{

            holder = new ChamBaiHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_lggv,parent,false);

            holder.tvsophieu =(TextView)row.findViewById(R.id.tvsophieu);
            holder.tvmamh =(TextView)row.findViewById(R.id.tvmamon);
            holder.soluongbai =(TextView)row.findViewById(R.id.tvsoluongbai);
            holder.pieChart=(PieChart)row.findViewById(R.id.pieChart);
            row.setTag(holder);
        }
        final ChamBai gv = data.get(position);
        ArrayList<PieEntry> slb = new ArrayList<>();
        holder.tvsophieu.setText("Số phiếu: "+gv.getSophieu());
        holder.tvmamh.setText("Mã môn học: "+gv.getMamh());
        holder.soluongbai.setText("Số lượng bài: "+gv.getSoluongbai());

        GiaoVienDatabase db = new GiaoVienDatabase(context);

        slb.add(new PieEntry(db.getBaiDC(gv.getSophieu()),"Đã chấm"));
        slb.add(new PieEntry(db.getBaiCC(gv.getSophieu()),"Chưa chấm"));

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
