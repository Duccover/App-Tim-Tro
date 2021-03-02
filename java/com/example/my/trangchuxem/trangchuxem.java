package com.example.my.trangchuxem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.example.my.R;
import com.example.my.dbtrangchu;
import com.example.my.quanly.qlanh;
import com.example.my.quanly.quantrixem;
import com.example.my.quanly.text;

import java.util.ArrayList;

public class trangchuxem extends AppCompatActivity {
    dbtrangchu db;
    ArrayList<tro> list;
    adaptertro adap = null;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchuxem);
        db = new dbtrangchu(this);
        listView = (ListView) findViewById(R.id.lv);
        Cursor cursor = db.alldata();
        cursor.moveToFirst();
      add();
        read();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Tìm trọ");




    }


    private void read() {

        Cursor cursor = db.alldata();

        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            String Tieude = cursor.getString(1);
            String Giaphong = cursor.getString(3);
            String Diadiem = cursor.getString(8);
            byte[] anh = cursor.getBlob(10);
            list.add(new tro(Tieude, Giaphong, Diadiem, anh));
        }
        adap.notifyDataSetChanged();
    }

    private void add() {
        Cursor cursor = db.alldata();

        list = new ArrayList<>();
        adap = new adaptertro(trangchuxem.this, list);
        listView.setAdapter(adap);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(trangchuxem.this, xem2.class);
                cursor.moveToPosition(position);
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
                it.putExtra("itempos", Integer.toString(position));

                byte[] blob = cursor.getBlob(10);
                qlanh blob2Img = new qlanh();
                String img = blob2Img.BlobToString(blob);
                it.putExtra("img", img);
                startActivity(it);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm phòng theo địa điểm");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {



                Cursor cursor = db.timphong(search.toString().trim());
                list.clear();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToPosition(i);
                    String Tieude = cursor.getString(1);
                    String Giaphong = cursor.getString(3);
                    String Diadiem = cursor.getString(8);
                    byte[] anh = cursor.getBlob(10);
                    list.add(new tro(Tieude, Giaphong, Diadiem, anh));
                }
                adap.notifyDataSetChanged();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}