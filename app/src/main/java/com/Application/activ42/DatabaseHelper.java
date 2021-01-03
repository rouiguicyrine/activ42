package com.Application.activ42;
import android.content.ContentValues;
import android.content.Context ;
import android.database.Cursor;
import  android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper ;


public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key,Password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }

    //insertion dans database
    public boolean insert(String email, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Password", Password);
        long ins = db.insert("user", null, contentValues);
        if (ins == 1) return false;
        else return true;
    }
    //verifier si l'email existe
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery("select * from user where email=?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;



    }




}
