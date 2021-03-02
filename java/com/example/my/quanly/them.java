package com.example.my.quanly;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class them extends AppCompatActivity {
    EditText s1,s2,  s3,  s5, s6, s7, s8, s9, s10, s11;
    Button b1, b2, b3;
    ImageView img;
 private ArrayList<Uri> list;
    dbtrangchu db;
      int ChupHinh = 123;
      int ChonHinh = 321;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them);
        db = new dbtrangchu(this);
        ActionBar actionBar = getSupportActionBar();
        init();
        event();
        eventt();

        actionBar.setTitle("Thêm phòng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void init() {

        s1 = (EditText) findViewById(R.id.id);
        s3 = (EditText) findViewById(R.id.lp);
        s2 = (EditText) findViewById(R.id.dienthoai);
        s5 = (EditText) findViewById(R.id.gp);
        s6 = (EditText) findViewById(R.id.gd);
        s7 = (EditText) findViewById(R.id.gn);
        s8 = (EditText) findViewById(R.id.dd);
        s9 = (EditText) findViewById(R.id.mt);
        s10 = (EditText) findViewById(R.id.dia);
        s11 = (EditText) findViewById(R.id.tieude);


        b1 = (Button) findViewById(R.id.chupanh);
        b2 = (Button) findViewById(R.id.chonanh);
        b3 = (Button) findViewById(R.id.btn);
        img = (ImageView) findViewById(R.id.imageView6);
    }

    private void event() {

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, ChupHinh);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, ChonHinh);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==ChupHinh && resultCode == RESULT_OK && data!=null)
        {
            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
            img.setImageBitmap(bitmap);

        }
        if (requestCode==ChonHinh && resultCode == RESULT_OK && data!=null)
        {
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void eventt() {

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer maphong = Integer.parseInt(s1.getText().toString());

                String tieude = s11.getText().toString();
                String loaiphong = s3.getText().toString();
                String dienthoai = s2.getText().toString();
                String giaphong = s5.getText().toString();
                String giadien = s6.getText().toString();
                String gianuoc = s7.getText().toString();
                String dodac = s8.getText().toString();
                String mota = s9.getText().toString();
                String diadiem = s10.getText().toString();

                byte[] anh = getByteArrayFromImageView(img);

                boolean chk = db.check(maphong);
                if (chk == false) {
                    Toast.makeText(getApplicationContext(), "Mã phòng trùng", Toast.LENGTH_SHORT).show();

                }


                boolean ins = db.insert(maphong, tieude, loaiphong,  giaphong, giadien, gianuoc, dodac, mota, diadiem,dienthoai, anh);
                if (ins == true) {


                    Toast.makeText(getApplicationContext(), "Đã thêm phòng ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(them.this,quantrixem.class);
                    startActivity(intent);
                    finish();
                }



            }
        });
    }


    private byte[] getByteArrayFromImageView(ImageView imgMatHang) {
        BitmapDrawable drawable = (BitmapDrawable) imgMatHang.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
