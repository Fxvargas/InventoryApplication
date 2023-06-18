package com.example.newinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBActivity extends SQLiteOpenHelper {

    public static final String DBNAME = "LoginDB.db";

    public DBActivity(Context context){

        super(context, "LoginDB.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase SQLdb){

        SQLdb.execSQL("create Table users(username TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLdb, int i, int i1){

        SQLdb.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String username, String password) {

        SQLiteDatabase SQLdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = SQLdb.insert("users", null, contentValues);

        if (result == -1) {

            return false;

        } else {

            return true;

        }
    }

    public Boolean checkusername(String username){

        SQLiteDatabase SQLdb = this.getWritableDatabase();

        Cursor cursor = SQLdb.rawQuery("Select * from users where username = ?", new String[] {username});

        if(cursor.getCount() > 0){

            return true;

        }

        else{

            return false;

        }

    }

    public Boolean checkpwd(String username, String password) {

        SQLiteDatabase SQLdb = this.getWritableDatabase();
        Cursor cursor = SQLdb.rawQuery("Select * from users where username = ? and password = ?", new String[] {username, password});
        if(cursor.getCount() > 0){

            return true;

        }

        else {

            return false;

        }

    }

}



