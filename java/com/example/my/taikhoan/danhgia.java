package com.example.my.taikhoan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;
import com.example.my.quanly.text;

public class danhgia extends AppCompatActivity {
    Button btn;
    EditText edt;
    dbtrangchu db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gopy);
        btn=(Button) findViewById(R.id.button);
        edt=(EditText) findViewById(R.id.edit);
        db=new dbtrangchu(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textx=edt.getText().toString();
                boolean te=db.gopy(textx);

                if (textx.equals("")) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập đánh giá ", Toast.LENGTH_SHORT).show();
                }else {

                    if (te = true) {
                        Toast.makeText(getApplicationContext(), "Cảm ơn bạn đã đánh giá ", Toast.LENGTH_SHORT).show();
                        Intent it=new Intent(danhgia.this, taikhoan.class);
                        startActivity(it);

                    }
                }

            }
        });


    }
}
