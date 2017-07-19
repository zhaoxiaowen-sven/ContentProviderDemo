
package com.example.sven.contentproviderdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    public static final String AUTHORITY = "com.example.sven.contentproviderdemo.provider";
    private static final String TAG = "CPClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                // 添加User数据
                Uri uriInsert = Uri.parse("content://" + AUTHORITY + "/users");
                ContentValues values = new ContentValues();
                Random random = new Random();
                values.put("name", "sven" + random.nextInt(30));
                values.put("age", random.nextInt(20));
                values.put("address", "guangzhou");
                Uri newUri = getContentResolver().insert(uriInsert, values);
                String newId = newUri.getPathSegments().get(1);
                Log.i(TAG, "newId = " + newId);
                break;
            case R.id.bt2:

                break;
            case R.id.bt3:

                break;
            case R.id.bt4:
                // 查询User数据
                Uri uriQuery = Uri.parse("content://" + AUTHORITY + "/users");
                Cursor cursor = getContentResolver().query(uriQuery, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String address = cursor.getString(cursor.getColumnIndex("address"));
                        // int age = cursor.getInt(0);
                        // String name = cursor.getString(1);
                        // String address = cursor.getString(2);

                        Log.i(TAG, "name = " + name + ", age = " + age + ", address = " + address);
                    }
                    cursor.close();
                }
                break;
            case R.id.bt45:
                Uri uriQuerySingle = Uri.parse("content://" + AUTHORITY + "/users/1");
                Cursor cursor2 = getContentResolver().query(uriQuerySingle, null, null, null, null);
                if (cursor2 != null) {
                    if (cursor2.moveToFirst()) {
                        String name = cursor2.getString(cursor2.getColumnIndex("name"));
                        int age = cursor2.getInt(cursor2.getColumnIndex("age"));
                        String address = cursor2.getString(cursor2.getColumnIndex("address"));
                        Log.i(TAG, "name = " + name + ", age = " + age + ", address = " + address);
                    }
                    cursor2.close();
                }
                break;

            case R.id.bt456:
                // uri格式最后一定要带一个 /
                Uri uriCall = Uri.parse("content://" + AUTHORITY + "/");
                Bundle b = getContentResolver().call(uriCall, "getData", null, null);
                Log.i(TAG, "" + b.get("name"));
                break;

            case R.id.bt5:
                Uri uriInsertBook = Uri.parse("content://" + AUTHORITY + "/books");
                ContentValues values2 = new ContentValues();
                values2.put("name", "Android");
                values2.put("author", "sven");
                values2.put("price", 50);
                Uri newUriBook = getContentResolver().insert(uriInsertBook, values2);
                String newIdBook = newUriBook.getPathSegments().get(1);
                Log.i(TAG, "newId = " + newIdBook);
                break;
            case R.id.bt6:

                break;
            case R.id.bt7:

                break;
            case R.id.bt8:
                Uri uriQueryBook = Uri.parse("content://" + AUTHORITY + "/books");
                Cursor cursor3 = getContentResolver().query(uriQueryBook, null, null, null, null);
                if (cursor3 != null) {
                    while (cursor3.moveToNext()) {
                        String name = cursor3.getString(cursor3.getColumnIndex("name"));
                        String author = cursor3.getString(cursor3.getColumnIndex("author"));
                        int price = cursor3.getInt(cursor3.getColumnIndex("price"));
                        Log.i(TAG,
                                "name = " + name + ", author = " + author + ", price = " + price);
                    }
                    cursor3.close();
                }
                break;
            case R.id.bt89:
                Uri uriQuery2 = Uri.parse("content://" + AUTHORITY + "/books/1");
                Cursor cursor4 = getContentResolver().query(uriQuery2, null, null, null, null);
                if (cursor4 != null) {
                    if (cursor4.moveToFirst()) {
                        String name = cursor4.getString(cursor4.getColumnIndex("name"));
                        String author = cursor4.getString(cursor4.getColumnIndex("author"));
                        int price = cursor4.getInt(cursor4.getColumnIndex("price"));
                        Log.i(TAG,
                                "name = " + name + ", author = " + author + ", price = " + price);
                    }
                    cursor4.close();
                }
                break;
            default:
                break;
        }
    }
}
