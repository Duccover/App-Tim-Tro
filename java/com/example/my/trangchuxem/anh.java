package com.example.my.trangchuxem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class anh {
    public String BlobToString(byte[] blob){
        BitmapFactory bitmapFactory = new BitmapFactory();
        ByteArrayOutputStream byos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeByteArray(blob,0,blob.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String img = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return img;
    }
    public Bitmap StringToBitmap(String InputString){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] imgbyte = byteArrayOutputStream.toByteArray();
        imgbyte = Base64.decode(InputString,Base64.DEFAULT);
        Bitmap decodebitmap = BitmapFactory.decodeByteArray(imgbyte,0,imgbyte.length);
        return decodebitmap;
    }
}
