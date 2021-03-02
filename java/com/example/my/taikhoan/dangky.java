package com.example.my.taikhoan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;

public class dangky extends AppCompatActivity {
    EditText edt1, edt2, edt3,edt4,edt5,edt6;
    Button btndk;
    dbtrangchu db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        db = new dbtrangchu(this);

        init();
        xuly();

    }

    private void xuly() {


        btndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = edt1.getText().toString();
                String s2 = edt2.getText().toString();
                String s3 = edt3.getText().toString();
                String s4 = edt4.getText().toString();
                String s5 = edt5.getText().toString();
                String s6 = edt6.getText().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                 if (!(s2.equals(s3)))
                 {
                     Toast.makeText(getApplicationContext(),"Mật khẩu không đúng,vui lòng kiểm tra",Toast.LENGTH_SHORT).show();
                 }
                  if (!(s4.contains("@")))
                  {
                      Toast.makeText(getApplicationContext(),"Gmail sai hoặc chưa nhập",Toast.LENGTH_SHORT).show();

                  }
                else {

                    if (s2.equals(s3)) {
                        Boolean check = db.check(s1);
                        if (check == true) {
                            Boolean insert = db.insert(s1, s2,s4,s5,s6);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Tên đăng ký đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });
    }


     public void quay(View view) {
         Intent it = new Intent(this, dangnhaptro.class);
         startActivity(it);
     }

    private void init() {
        edt1 = (EditText) findViewById(R.id.tendky);
        edt2 = (EditText) findViewById(R.id.matkhau);
        edt3 = (EditText) findViewById(R.id.xacnhan);
        edt4=(EditText) findViewById(R.id.gmail);
        edt5=(EditText) findViewById(R.id.sodt);
        edt6=(EditText) findViewById(R.id.hoten);
        btndk = (Button) findViewById(R.id.dky);
    }
}
