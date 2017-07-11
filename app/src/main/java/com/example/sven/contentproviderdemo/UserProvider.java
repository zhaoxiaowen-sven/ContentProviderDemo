package com.example.sven.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by SVEN on 2017/7/11.
 */

public class UserProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.sven.contentproviderdemo";

    public static final int USER_DIR = 0;

    public static final int USER_ITEM = 1;

    private static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "user", USER_DIR);
        mUriMatcher.addURI(AUTHORITY, "user", USER_ITEM);
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (mUriMatcher.match(uri)){
            case USER_DIR:
                return "vnd.android.cursor.dir/vnd."+AUTHORITY+"/user";
            case USER_ITEM:
                return "vnd.android.cursor.dir/vnd."+AUTHORITY+"/user";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}