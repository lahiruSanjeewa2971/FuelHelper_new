package com.example.fuelhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "UsersDatabase.db";

    public DBHelper(Context context) {
        super(context, "UsersDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(name TEXT , email TEXT primary key, usertype TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String name, String email, String usertype, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("usertype", usertype);
        contentValues.put("password", password);



        long result = db.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return  true;
    }
    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email= ?", new String[] {email});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkemailandpassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ? and usertype = ?", new String[] {email, password, "user"});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
