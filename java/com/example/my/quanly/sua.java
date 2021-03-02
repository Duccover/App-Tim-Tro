package com.example.my.quanly;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class sua extends AppCompatActivity {
    EditText  l2, s3, s5, s6, s7, s8, s9, s10, s4;
    Button b1, b2, b3, b4;
    TextView s1;
    ImageView img;
    dbtrangchu db;
    final int ChupHinh = 123;
    final int ChonHinh = 321;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua);
        db = new dbtrangchu(this);
        ActionBar actionBar = getSupportActionBar();
        init();
        event();
        eventt();
        settext();
        actionBar.setTitle("Sửa phòng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void eventt() {

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer maphong = Integer.parseInt(s1.getText().toString());

                String tieude = l2.getText().toString();
                String loaiphong = s3.getText().toString();
                String dienthoai = s10.getText().toString();
                String giaphong = s4.getText().toString();
                String giadien = s5.getText().toString();
                String gianuoc = s6.getText().toString();
                String dodac = s7.getText().toString();
                String mota = s8.getText().toString();
                String diadiem = s9.getText().toString();

                byte[] anh = getByteArrayFromImageView(img);
                boolean ins = db.update(maphong, tieude, loaiphong, giaphong, giadien, gianuoc, dodac, mota, diadiem, dienthoai, anh);
                if (ins == true) {
                    Toast.makeText(getApplicationContext(), "Đã sửa thông tin phòng ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(sua.this,quantrixem.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void settext() {

        s1.setText(getIntent().getStringExtra("mp"));
        l2.setText(getIntent().getStringExtra("td"));
         s3.setText(getIntent().getStringExtra("lp"));
        s4.setText(getIntent().getStringExtra("gp"));
        s5.setText(getIntent().getStringExtra("gd"));
        s6.setText(getIntent().getStringExtra("gn"));
        s7.setText(getIntent().getStringExtra("dd"));
        s8.setText(getIntent().getStringExtra("mt"));
        s9.setText(getIntent().getStringExtra("dia"));
        s10.setText(getIntent().getStringExtra("sdt"));

        qlanh blob2Img = new qlanh();
        img.setImageBitmap(blob2Img.StringToBitmap(getIntent().getStringExtra("img")));

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
        b4.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder al= new AlertDialog.Builder(sua.this);
                al.setTitle("Thông báo");
                al.setMessage("Bạn có muốn xóa không?");
                al.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Integer maphong = Integer.parseInt(s1.getText().toString());
                        boolean d1=db.delete(maphong);
                        if (d1==true)
                        {
                            Toast.makeText(getApplicationContext(),"Đã xóa phòng",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(sua.this,quantrixem.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
                al.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
               AlertDialog alertDialog=al.create();
               alertDialog.show();




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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ChonHinh) {
                Uri imageUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == ChupHinh) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(bitmap);
            }
        }
    }

    private void init() {

        s1 = (TextView) findViewById(R.id.id);
        l2 = (EditText) findViewById(R.id.tieude);
        s3 = (EditText) findViewById(R.id.lp);
        s4 = (EditText) findViewById(R.id.gp);
        s5 = (EditText) findViewById(R.id.gd);
        s6 = (EditText) findViewById(R.id.gn);
        s7 = (EditText) findViewById(R.id.dd);
        s8 = (EditText) findViewById(R.id.mt);
        s9 = (EditText) findViewById(R.id.dia);
        s10 = (EditText) findViewById(R.id.dienthoai);

        b1 = (Button) findViewById(R.id.chupanh);
        b2 = (Button) findViewById(R.id.chonanh);
        b3 = (Button) findViewById(R.id.sua);
        b4 = (Button) findViewById(R.id.xoa);
        img = (ImageView) findViewById(R.id.imageView6);
    }

}