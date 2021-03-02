package com.example.my.taikhoan;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;
import com.example.my.quyen;

public class taikhoan extends AppCompatActivity {
    dbtrangchu db;
    TextView txt;
    int pos;
    Button btn,btn2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taikhoan);
         db=new dbtrangchu(this);
         txt=(TextView) findViewById(R.id.txt);
        btn=(Button) findViewById(R.id.btn);
        btn2=(Button) findViewById(R.id.button2);

        Cursor cursor=db.taikhoan();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            Intent it=getIntent();
            it.putExtra("ht",cursor.getString(4));
            txt.setText(getIntent().getStringExtra("ht"));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder al=new AlertDialog.Builder(taikhoan.this);
                al.setTitle("Thông báo");
                al.setMessage("Đăng xuất?");
                al.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent itt=new Intent(taikhoan.this, quyen.class);
                        startActivity(itt);



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
btn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent it=new Intent(taikhoan.this,danhgia.class);
        startActivity(it);
    }
});


    }
}
