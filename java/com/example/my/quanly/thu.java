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
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my.R;
import com.example.my.dbtrangchu;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class thu extends AppCompatActivity {

 ImageButton b2;
 ImageSwitcher s1;
         RecyclerView re;

    private ArrayList<Uri> list;
    dbtrangchu db;
    int ChupHinh = 123;
    int ChonHinh = 321;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thu);
        db = new dbtrangchu(this);
        ActionBar actionBar = getSupportActionBar();
        event();

        b2=(ImageButton) findViewById(R.id.imageButton) ;
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




    private void event() {

  
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, ChonHinh);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if (requestCode==ChupHinh && resultCode == RESULT_OK && data!=null)
//        {
//            Bitmap bitmap= (Bitmap)data.getExtras().get("data");
//            img.setImageBitmap(bitmap);
//
//        }
//        if (requestCode==ChonHinh && resultCode == RESULT_OK && data!=null)
//        {
//            Uri uri=data.getData();
//            try {
//                InputStream inputStream=getContentResolver().openInputStream(uri);
//                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
//                img.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//

    private byte[] getByteArrayFromImageView(ImageView imgMatHang) {
        BitmapDrawable drawable = (BitmapDrawable) imgMatHang.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
