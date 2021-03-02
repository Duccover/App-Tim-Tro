package com.example.my.quanly;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.my.R;
import com.example.my.dbtrangchu;

public class text extends AppCompatActivity {
    public static final int call=1;
    TextView s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
    ImageView img;
    ImageButton img2;
    private MenuItem item;
    private int position;
   dbtrangchu db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
       db=new dbtrangchu(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.xem2);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Thông tin phòng");
        img2=(ImageButton) findViewById(R.id.imageb);


        s2=(TextView)findViewById(R.id.td);
        s2.setText(getIntent().getStringExtra("td"));

        s3=(TextView)findViewById(R.id.lp);
        s3.setText(getIntent().getStringExtra("lp"));

        s4=(TextView)findViewById(R.id.gp);
        s4.setText(getIntent().getStringExtra("gp"));

        s5=(TextView)findViewById(R.id.gd);
        s5.setText(getIntent().getStringExtra("gd"));

        s6=(TextView)findViewById(R.id.gn);
        s6.setText(getIntent().getStringExtra("gn"));

        s7=(TextView)findViewById(R.id.dd);
        s7.setText(getIntent().getStringExtra("dd"));

        s8=(TextView)findViewById(R.id.mt);
        s8.setText(getIntent().getStringExtra("mt"));

        s9=(TextView)findViewById(R.id.dia);
        s9.setText(getIntent().getStringExtra("dia"));


        s10=(TextView)findViewById(R.id.sdt);
        s10.setText(getIntent().getStringExtra("sdt"));


        qlanh blob2Img = new qlanh();
        img=(ImageView)findViewById(R.id.img);
        img.setImageBitmap(blob2Img.StringToBitmap(getIntent().getStringExtra("img")));

        img2=(ImageButton) findViewById(R.id.imageb);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nu=s10.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+nu));
                startActivityForResult(intent,call);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.sua, menu);

        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Cursor cursor=db.sua();
        switch (item.getItemId())
        {

            case R.id.sua:
                Intent it=new Intent(getApplicationContext(),sua.class);
                cursor.moveToPosition(Integer.parseInt(getIntent().getStringExtra("itempos")));
                it.putExtra("mp", cursor.getString(0));
                it.putExtra("td", cursor.getString(1));
                it.putExtra("lp", cursor.getString(2));
                it.putExtra("gp", cursor.getString(3));
                it.putExtra("gd", cursor.getString(4));
                it.putExtra("gn", cursor.getString(5));
                it.putExtra("dd", cursor.getString(6));
                it.putExtra("mt", cursor.getString(7));
                it.putExtra("dia", cursor.getString(8));
                it.putExtra("sdt", cursor.getString(9));

                byte[] blob = cursor.getBlob(10);
                qlanh blob2Img = new qlanh();
                String img = blob2Img.BlobToString(blob);
                it.putExtra("img", img);
                startActivity(it);



        }

        return super.onOptionsItemSelected(item);
    }
}
