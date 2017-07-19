
package com.example.sven.contentproviderdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sven.contentproviderdemo.bean.Book;
import com.example.sven.contentproviderdemo.bean.User;

import java.util.ArrayList;
import java.util.List;

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

    private synchronized void openDb() {
        // if (!db.isOpen()) {
        db = sqliteHelper.getWritableDatabase();
        // }
    }

    public synchronized void closeDb() {
        if (sqliteHelper != null) {
            sqliteHelper.close();
        }

        if (db != null) {
            db.close();
        }
    }

    public void insertUser(User user) {
        openDb();
        ContentValues values = new ContentValues();
        // values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("address", user.getAddress());
        db.insert(DbConstant.TABLE_USER, null, values);
        closeDb();
    }

    public void deleteUser(int id) {
        openDb();
        db.delete(DbConstant.TABLE_USER, "id = ?", new String[] {
                id + ""
        });
        closeDb();
    }

    public void updateUser(int id, User user) {
        openDb();
        ContentValues values = new ContentValues();
        // values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("address", user.getAddress());
        db.update(DbConstant.TABLE_USER, values, "id = ?", new String[] {
                id + ""
        });
        closeDb();
    }

    public User queryUser(int id) {
        openDb();
        User user = new User();
        Cursor cursor = db.query(DbConstant.TABLE_USER, null, "id = ?", new String[] {
                id + ""
        }, null, null, null);
        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
        }
        cursor.close();
        closeDb();
        return user;
    }

    public List<User> queryUsers() {
        openDb();
        List<User> userList = new ArrayList<User>();
        Cursor cursor = db.query(DbConstant.TABLE_USER, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            userList.add(user);
        }
        cursor.close();
        closeDb();
        return userList;
    }

    public void insertBook(Book book) {
        openDb();
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("price", book.getPrice());
        db.insert(DbConstant.TABLE_BOOK, null, values);
        closeDb();
    }

    public void deleteBook(int id) {
        openDb();
        db.delete(DbConstant.TABLE_BOOK, "id = ?", new String[] {
                id + ""
        });
        closeDb();
    }

    public void updateBook(int id, Book book) {
        openDb();
        ContentValues values = new ContentValues();
        values.put("name", book.getName());
        values.put("author", book.getAuthor());
        values.put("price", book.getPrice());
        db.update(DbConstant.TABLE_BOOK, values, "id = ?", new String[] {
                id + ""
        });
        closeDb();
    }

    public Book queryBook(int id) {
        openDb();
        // Log.i(TAG , "cursor = "+cursor);??
        Book book = new Book();
        Cursor cursor = db.query(DbConstant.TABLE_BOOK, null, "id = ?", new String[] {
                id + ""
        }, null, null, null);

        if (cursor.moveToFirst()) {
            book.setName(cursor.getString(cursor.getColumnIndex("name")));
            book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            book.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
        }
        cursor.close();
        closeDb();
        return book;
    }

    public List<Book> queryBooks() {
        openDb();
        List<Book> bookList = new ArrayList<Book>();
        Cursor cursor = db.query(DbConstant.TABLE_BOOK, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Book book = new Book();
            // book.setId(cursor.getInt(cursor.getColumnIndex("id")));
            book.setName(cursor.getString(cursor.getColumnIndex("name")));
            book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            book.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            bookList.add(book);
        }
        cursor.close();
        closeDb();
        return bookList;
    }
}
