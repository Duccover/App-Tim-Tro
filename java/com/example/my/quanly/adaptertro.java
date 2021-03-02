package com.example.my.quanly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.trangchuxem.tro;



import java.util.ArrayList;

public class adaptertro extends BaseAdapter {
    Activity context;
    ArrayList<qltro> list;


    public adaptertro(Activity context, ArrayList<qltro> list) {
        this.context = context;
        this.list = list;
    }

    public adaptertro() {

    }

    @Override
    public int getCount() {
        return list.size();
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
        View row = inflater.inflate(R.layout.adapqltro,null);
        ImageView imgMatHang = (ImageView) row.findViewById(R.id.img);
        TextView  tieude = (TextView) row.findViewById(R.id.tieude);
        TextView gia = (TextView) row.findViewById(R.id.gia);
        TextView diadiem = (TextView) row.findViewById(R.id.diadiem);

        final qltro troo = list.get(position);
        tieude.setText(troo.Tieude+"");
        gia.setText(troo.Giaphong+"");
        diadiem.setText(troo.Diadiem+"");

        Bitmap bmMatHang = BitmapFactory.decodeByteArray(troo.anh,0,troo.anh.length);
        imgMatHang.setImageBitmap(bmMatHang);

        return row;
    }


}
