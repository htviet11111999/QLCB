package com.example.giuaky;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class GiaoVienAdapter extends ArrayAdapter<GiaoVien> {

    Context context;
    int layoutResouredId;
    ArrayList<GiaoVien> data= null;

    public GiaoVienAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<GiaoVien> data) {
        super(context, layoutResouredId, data);
        this.context = context;
        this.layoutResouredId = layoutResouredId;
        this.data = data;
    }
    static class GiaoVienHolder{
        TextView tvmagv , tvmk, tvtengv;
        ImageView img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        GiaoVienHolder holder = null;
        if (row != null){
            holder = (GiaoVienHolder)row.getTag();
        }
        else{

            holder = new GiaoVienHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_giaovien,parent,false);

            holder.tvmagv =(TextView)row.findViewById(R.id.magv);
            holder.tvmk =(TextView)row.findViewById(R.id.mk);
            holder.tvtengv =(TextView)row.findViewById(R.id.tengv);
            holder.img=(ImageView)row.findViewById(R.id.hinhgv);
            row.setTag(holder);
        }
        final GiaoVien gv = data.get(position);
        holder.tvmagv.setText("Mã giáo viên: "+gv.getMa());
        holder.tvmk.setText("Mật khẩu: "+gv.getMk());
        holder.tvtengv.setText("Họ tên giáo viên: "+gv.getTen());
        byte[] outImage = gv.getHinhanh();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.img.setImageBitmap(theImage);

        return row;
    }
}
