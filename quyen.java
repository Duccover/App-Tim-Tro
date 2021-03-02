package com.example.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.taikhoan.dangnhaptro;
import com.example.my.trangchuxem.trangchuxem;

public class quyen extends AppCompatActivity{
    Button b1,b2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quyen);
        b1=(Button)findViewById( R.id.tim);
        b2=(Button)findViewById(R.id.chothue);

      b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent it = new Intent(quyen.this, trangchuxem.class);
              startActivity(it);
          }
      });
      b2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent it = new Intent(quyen.this, dangnhaptro.class);
              startActivity(it);
          }
      });
    }

}
