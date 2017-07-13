
package com.example.sven.contentproviderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.sven.contentproviderdemo.bean.Book;
import com.example.sven.contentproviderdemo.bean.User;
import com.example.sven.contentproviderdemo.db.DBHelper;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DbActivity";
    private DBHelper dbHelper;
    // private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // tv = (TextView) findViewById(R.id.tv);
        dbHelper = new DBHelper(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // case R.id.bt1:
            // dbHelper = new DBHelper(this);
            // break;
            case R.id.bt2:
                dbHelper.insertUser(new User(1, "Amy" + System.currentTimeMillis(), 22, "beijing"));
                break;
            case R.id.bt3:
                dbHelper.deleteUser(1);
                break;
            case R.id.bt4:
                dbHelper.updateUser(1, new User(1, "Alice", 23, "shenzhen"));
                break;
            case R.id.bt5:
                User user = dbHelper.queryUser(1);
                Log.i(TAG, "user = " + user.toString());
                break;
            case R.id.bt6:
                List<User> userList = dbHelper.queryUsers();
                for (User item : userList) {
                    Log.i(TAG, item.toString());
                }
                break;
            case R.id.book_bt2:
                dbHelper.insertBook(new Book("Android" + System.currentTimeMillis(), "sven",
                        new Random().nextInt(100)));
                break;
            case R.id.book_bt3:
                dbHelper.deleteBook(1);
                break;
            case R.id.book_bt4:
                dbHelper.updateBook(1, new Book("Python", "zxw", 50));
                break;
            case R.id.book_bt5:
                Book book = dbHelper.queryBook(1);
                Log.i(TAG, book.toString());
                break;
            case R.id.book_bt6:
                List<Book> bookList = dbHelper.queryBooks();
                for (Book b : bookList) {
                    Log.i(TAG, b.toString());
                }
                break;
            default:
                break;
        }
    }
}
