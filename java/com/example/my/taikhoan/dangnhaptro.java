package com.example.my.taikhoan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;
import com.example.my.quanly.quantrixem;
import com.example.my.quyen;
import com.example.my.taikhoan.dangky;
import com.example.my.trangchuxem.trangchuxem;

import java.util.ArrayList;

public class dangnhaptro extends AppCompatActivity {
    EditText edt1,edt2;
    Button btn;
    dbtrangchu db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);
        db=new dbtrangchu(this);
        edt1=(EditText) findViewById(R.id.tk);
        edt2=(EditText) findViewById(R.id.mk);
        btn=(Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String tendangky=  edt1.getText().toString();
              String matkhau=   edt2.getText().toString();

              Boolean checktk= db.checktkmk(tendangky,matkhau);



              if (checktk == true) {
                  Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                  Intent it = new Intent(dangnhaptro.this, quantrixem.class);
                  startActivity(it);

              }
              else
                  Toast.makeText(getApplicationContext(),"Sai mật khẩu hoặc tài khoản",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void dky(View view) {
        Intent it=new Intent(this, dangky.class);
        startActivity(it);
    }

    public void google(View view) {
        TextView mywed=new TextView(this);
        mywed.setText("https://myaccount.google.com/");
        Linkify.addLinks(mywed,Linkify.WEB_URLS);
    }

    public void facebook(View view) {
        TextView mywed=new TextView(this);
        mywed.setText("https://facebook.com/");
        Linkify.addLinks(mywed,Linkify.WEB_URLS);
    }
}
