
package com.example.sven.contentproviderdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SVEN on 2017/7/12.
 */

public class DBHelper {

    private SQLiteDatabase db;
    private SqliteHelper sqliteHelper;

    public DBHelper(Context context) {
        sqliteHelper = SqliteHelper.getInstance(context);
        db = sqliteHelper.getWritableDatabase();
    }

    private synchronized void openDb(){
        if(!db.isOpen()){
            db = sqliteHelper.getWritableDatabase();
        }
    }

    public synchronized void closeDb() {
        if (sqliteHelper != null) {
            sqliteHelper.close();
        }

        if (db != null) {
            db.close();
        }
    }

    public void insertUser(String tableName, User user) {
        openDb();
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("address", user.getAddress());
        db.insert(tableName, null, values);
        closeDb();
    }

    public void deleteUser(String tableName, int userId) {
        openDb();
        db.delete(tableName, "id = ?", new String[] {
                userId + ""
        });
        closeDb();
    }

    public User queryUser(String tableName, int id) {
        openDb();
        User user = new User();
        Cursor cursor = db.query(tableName, null, "id = ?", new String[] {
                id + ""
        }, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            }
        }
        cursor.close();
        closeDb();
        return user;
    }

    public void updateUser(String tablename, int id, User user) {
        openDb();
        ContentValues values = new ContentValues();
//        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("address", user.getAddress());
        db.update(tablename, values, "id = ?", new String[] {
                id + ""
        });
        closeDb();
    }
}
