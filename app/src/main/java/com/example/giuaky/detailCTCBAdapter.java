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

public class detailCTCBAdapter extends ArrayAdapter<detailCTCB> {
    Context context;
    int layoutResouredId;
    ArrayList<ChiTietTTCB> data= null;
    public detailCTCBAdapter(@NonNull Context context, int layoutResouredId, @NonNull ArrayList<ChiTietTTCB> data) {
        super(context, layoutResouredId);
        this.context = context;
        this.layoutResouredId = layoutResouredId;
        this.data = data;
    }

    static class detailHolder{
        ImageView imgmh;
        TextView tvbaiso, tvdiem;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        detailHolder holder = null;
        if (row != null){
            holder = (detailCTCBAdapter.detailHolder)row.getTag();
        }
        else{

            holder = new detailHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_ttp,parent,false);

            holder.tvbaiso =(TextView)row.findViewById(R.id.tvBaiso);
            holder.tvdiem =(TextView) row.findViewById(R.id.tvdiem);
            holder.imgmh=(ImageView)row.findViewById(R.id.hinhmonhoctest);
            row.setTag(holder);
        }
        final ChiTietTTCB gv = data.get(position);
        holder.tvbaiso.setText(gv.getBaiso());
        holder.tvdiem.setText(gv.getDiem());
        byte[] outImage = gv.getHinhanh();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.imgmh.setImageBitmap(theImage);
        return row;
    }
}
