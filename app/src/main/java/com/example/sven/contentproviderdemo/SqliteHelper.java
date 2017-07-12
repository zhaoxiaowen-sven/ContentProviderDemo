
package com.example.sven.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by SVEN on 2017/7/11.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SqliteHelper";

    private volatile static SqliteHelper sqliteHelper;
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table if not exists users(" +
            "id int primary key," +
            "name varchar, " +
            "age int," +
            "address varchar)";
    private static final String DROP_TABLE = "drop table if exists users";

    private SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

    private SqliteHelper(Context context){
        this(context, DB_NAME, null, DB_VERSION);
    }

    public static SqliteHelper getInstance(Context context){
        if(sqliteHelper == null){
            synchronized (SqliteHelper.class){
                if (sqliteHelper == null){
                    sqliteHelper = new SqliteHelper(context);
                }
            }
        }
        return sqliteHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
