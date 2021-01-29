package com.example.hupu.SQLite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String SQL_name = "user.db";
    static int dbVersion = 1 ;

    public DatabaseHelper(Context context){
        super(context,SQL_name,null,dbVersion);
    }
    //创建时使用
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id integer primary key autoincrement,username varchar(80),password varchar(80))";
        db.execSQL(sql);
    }
    //更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
