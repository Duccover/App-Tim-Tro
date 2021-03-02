package com.example.my;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbtrangchu extends SQLiteOpenHelper {
    public static String database = "nhatro.sqlite";

    public dbtrangchu(@Nullable Context context) {
        super(context, database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table phong(Maphong integer primary key,Tieude Text,Loaiphong Text,Giaphong integer,Giadien integer,Gianuoc integer,Dodac Text,Mota Text,Diadiem Text,Dienthoai integer,Anh Blob)");
        db.execSQL("Create table dangky(tendangky text primary key  ,matkhau text, gmail text, sodienthoai interger,hovaten text)");
         db.execSQL("Create table gopy (danhgia Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists phong");
        db.execSQL("Drop table if exists dangky");
        db.execSQL("Drop table if exists gopy");
    }


    public boolean insert(Integer Maphong, String Tieude, String Loaiphong, String Giaphong, String Giadien, String Gianuoc, String Dodac, String Mota, String Diadiem,String Dienthoai, byte[] Anh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maphong", Maphong);
        contentValues.put("tieude", Tieude);
        contentValues.put("loaiphong", Loaiphong);
        contentValues.put("giaphong", Giaphong);
        contentValues.put("giadien", Giadien);
        contentValues.put("gianuoc", Gianuoc);
        contentValues.put("dodac", Dodac);
        contentValues.put("mota", Mota);
        contentValues.put("diadiem", Diadiem);
        contentValues.put("dienthoai", Dienthoai);
        contentValues.put("anh", Anh);


        long ins = db.insert("phong", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }


    public boolean check(Integer Maphong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from phong where Maphong=?", new String[]{String.valueOf(Maphong)});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public boolean update(Integer Maphong, String Tieude, String Loaiphong, String Giaphong, String Giadien, String Gianuoc, String Dodac, String Mota, String Diadiem,String Dienthoai, byte[] Anh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maphong", Maphong);
        contentValues.put("tieude", Tieude);
        contentValues.put("loaiphong", Loaiphong);
        contentValues.put("giaphong", Giaphong);
        contentValues.put("giadien", Giadien);
        contentValues.put("gianuoc", Gianuoc);
        contentValues.put("dodac", Dodac);
        contentValues.put("mota", Mota);
        contentValues.put("diadiem", Diadiem);
        contentValues.put("dienthoai", Dienthoai);
        contentValues.put("anh", Anh);
        db.update("phong", contentValues, "Maphong=?", new String[]{String.valueOf(Maphong)});
        return true;
    }


    public boolean delete(Integer Maphong) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("phong", "Maphong=?", new String[]{String.valueOf(Maphong)});
        return true;

    }

    public Cursor alldata() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from phong", null);
        return cursor;


    }

    public boolean insert(String tendangky, String matkhau, String gmail, String sodienthoai, String hovaten) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendangky", tendangky);
        contentValues.put("matkhau", matkhau);
        contentValues.put("gmail", gmail);
        contentValues.put("sodienthoai", sodienthoai);
        contentValues.put("hovaten", hovaten);

        long ins = db.insert("dangky", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }

    public boolean check(String tendangky) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from dangky where tendangky=?", new String[]{tendangky});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public boolean checktkmk(String tendangky, String matkhau) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from dangky where tendangky=? and matkhau=?", new String[]{tendangky, matkhau});
        if (cursor.getCount() > 0) return true;
        else return false;

    }
    public Cursor timphong( String search)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from phong where Diadiem like '%"+search+"%' or Diadiem like '%"+search+"%' ",null);
      return cursor;
    }

    public Cursor doc()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from phong ",null);
        return cursor;

    }
    public Cursor sua()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from phong  ",null);
        return cursor;
    }
    public Cursor taikhoan()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from dangky  ",null);
        return cursor;
    }
public boolean gopy(String danhgia)
{
    SQLiteDatabase db=this.getWritableDatabase();
 ContentValues contentValues=new ContentValues();
 contentValues.put("danhgia",danhgia);
 long ins = db.insert("gopy",null,contentValues);
    if (ins == -1) return false;
    else return true;

}

}
