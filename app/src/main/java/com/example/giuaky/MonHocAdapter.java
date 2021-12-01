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

public class MonHocAdapter extends ArrayAdapter<MonHoc> {

    Context context;
    int layoutResouredId;
    ArrayList<MonHoc> data= null;

    public MonHocAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<MonHoc> data) {
        super(context, layoutResouredId, data);
        this.context = context;
        this.layoutResouredId = layoutResouredId;
        this.data = data;
    }
    static class MonHocHolder{
        TextView tvmamh,tvtenmh;
        ImageView imghinhmh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        MonHocHolder holder = null;
        if (row != null){
            holder = (MonHocHolder) row.getTag();
        }
        else{

            holder = new MonHocHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_monhoc,parent,false);

            holder.tvmamh =(TextView)row.findViewById(R.id.mamh);
            holder.tvtenmh =(TextView)row.findViewById(R.id.tenmh);
            holder.imghinhmh=(ImageView)row.findViewById(R.id.hinhmh);
            row.setTag(holder);
        }
        final MonHoc mh = data.get(position);
        holder.tvmamh.setText("Mã môn học: "+mh.getMa());
        holder.tvtenmh.setText("Tên môn học: "+mh.getTen());
        byte[] outImage = mh.getHinhanh();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imghinhmh.setImageBitmap(theImage);

        return row;
    }
}
