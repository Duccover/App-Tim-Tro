package com.example.my.trangchuxem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;

public class xem2 extends AppCompatActivity {
    public static final int call = 1;
    TextView s1, s2, s3, s4, s5, s6, s7, s8, s9, s10;
    ImageView img;
    ImageButton img2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin phòng");


        s2 = (TextView) findViewById(R.id.td);
        s2.setText(getIntent().getStringExtra("td"));

        s3 = (TextView) findViewById(R.id.lp);
        s3.setText(getIntent().getStringExtra("lp"));

        s4 = (TextView) findViewById(R.id.gp);
        s4.setText(getIntent().getStringExtra("gp"));

        s5 = (TextView) findViewById(R.id.gd);
        s5.setText(getIntent().getStringExtra("gd"));

        s6 = (TextView) findViewById(R.id.gn);
        s6.setText(getIntent().getStringExtra("gn"));

        s7 = (TextView) findViewById(R.id.dd);
        s7.setText(getIntent().getStringExtra("dd"));

        s8 = (TextView) findViewById(R.id.mt);
        s8.setText(getIntent().getStringExtra("mt"));

        s9 = (TextView) findViewById(R.id.dia);
        s9.setText(getIntent().getStringExtra("dia"));

        s10 = (TextView) findViewById(R.id.sdt);
        s10.setText(getIntent().getStringExtra("sdt"));

        img2 = (ImageButton) findViewById(R.id.imageButton2);


        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nu=s10.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+nu));
                startActivityForResult(intent,call);
            }
        });




        anh blob2Img = new anh();
        img = (ImageView) findViewById(R.id.img);
        img.setImageBitmap(blob2Img.StringToBitmap(getIntent().getStringExtra("img")));


    }
}

