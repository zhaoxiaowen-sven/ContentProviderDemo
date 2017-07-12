
package com.example.sven.contentproviderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DbActivity";
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                dbHelper = new DBHelper(this);
                break;
            case R.id.bt2:
                dbHelper.insertUser("users", new User(1, "Amy", 22, "beijing"));
                break;
            case R.id.bt3:
                dbHelper.deleteUser("users", 1);
                break;
            case R.id.bt4:
                dbHelper.updateUser("users", 1, new User(1, "Alice", 23, "shenzhen"));
                break;
            case R.id.bt5:
                User user = dbHelper.queryUser("users", 1);
                Log.i(TAG, "user = " + user.toString());
                break;
            default:
                break;
        }
    }
}
