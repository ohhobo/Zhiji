package com.sdu.zhiji.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public final static String LDB_NAME = "local_db";
    public final static int LDB_VERSION = 1;
    public final static String TABLE_RESULTS = "results";

    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, LDB_NAME, null, LDB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("local_db", "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table results ("
                + "id integer primary key autoincrement,"
                + "person varchar(50),"
                + "user varchar(50),"
                + "e integer,"
                + "i integer,"
                + "s integer,"
                + "n integer,"
                + "t integer,"
                + "f integer,"
                + "j integer,"
                + "p integer,"
                + "page integer"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
