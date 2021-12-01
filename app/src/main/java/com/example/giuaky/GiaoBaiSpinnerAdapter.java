package com.example.giuaky;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

public class GiaoBaiSpinnerAdapter extends BaseAdapter {
    Context context;
    int myLayout;
    List<GiaoBaiSpinner> arrayList;

    public GiaoBaiSpinnerAdapter(Context context, int myLayout, List<GiaoBaiSpinner> arrayList) {
        this.context = context;
        this.myLayout = myLayout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,null);

        TextView txtmagv = (TextView)convertView.findViewById(R.id.magvspinner);
        ImageView img = (ImageView)convertView.findViewById(R.id.hinhgvspinner);

        txtmagv.setText("Mã giáo viên: "+arrayList.get(position).getMagv());
        byte[] outImage = arrayList.get(position).getHinh();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        img.setImageBitmap(theImage);

        return convertView;
    }
}
