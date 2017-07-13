
package com.example.sven.contentproviderdemo.db;

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
    private static final String DB_NAME = "user_book.db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_USER = "create table if not exists "
            + DbConstant.TABLE_USER + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name varchar, "
            + "age int,"
            + "address varchar)";

    private static final String CREATE_TABLE_BOOK = "create table if not exists "
            + DbConstant.TABLE_BOOK + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name varchar, "
            + "author int,"
            + "price varchar)";

    private static final String DROP_TABLE_USER = "drop table if exists users";
    private static final String DROP_TABLE_BOOK = "drop table if exists books";

    private SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
            int version) {
        super(context, name, factory, version);
    }

    private SqliteHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    public static SqliteHelper getInstance(Context context) {
        if (sqliteHelper == null) {
            synchronized (SqliteHelper.class) {
                if (sqliteHelper == null) {
                    sqliteHelper = new SqliteHelper(context);
                }
            }
        }
        return sqliteHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade");
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_BOOK);
        onCreate(db);
    }
}
