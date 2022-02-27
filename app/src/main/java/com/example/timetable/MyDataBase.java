package com.example.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String DBName = "login";
    public static final String column_Title = "My Task";


    public MyDataBase(Context context) {
        super(context,"login",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT, task TEXT, datetime default current_timestamp)");
        String query = "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }

    public Boolean EditData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long res = db.insert("users",null,cv);

        return res != -1;
    }
    public Boolean insertTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("task",task);
        db.replace(column_Title,null,cv);
        return true;
    }
    public ArrayList getAllData(){
        SQLiteDatabase db =this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor res = db.rawQuery("Select (username || ' : ' || task || ' : ' || time('%d-%m-%Y %H:%M:%S',datetime)) as fullname from " + column_Title,null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
           if((res != null)&&(res.getCount()>0)){
               arrayList.add(res.getString(res.getColumnIndex("fullname")));
           }
            res.moveToNext();
        }
        return arrayList;
    }
    public boolean delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + column_Title);
        return true;
    }
    public  Boolean checkUserName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[] {username});
        return cursor.getCount() > 0;
    }

    public Boolean checkAllData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        return cursor.getCount() > 0;
    }
}
