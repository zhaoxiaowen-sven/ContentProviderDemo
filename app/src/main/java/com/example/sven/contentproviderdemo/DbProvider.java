
package com.example.sven.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.sven.contentproviderdemo.db.SqliteHelper;

/**
 * Created by SVEN on 2017/7/11.
 */

public class DbProvider extends ContentProvider {

    private static final String TAG = "DbProvider";

    public static final String AUTHORITY = "com.example.sven.contentproviderdemo.provider";

    public static final int USER_DIR = 0;

    public static final int USER_ITEM = 1;

    public static final int BOOK_DIR = 2;

    public static final int BOOK_ITEM = 3;

    private SqliteHelper mSqliteHelper;

    private static UriMatcher mUriMatcher;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "users", USER_DIR);
        mUriMatcher.addURI(AUTHORITY, "users/#", USER_ITEM);
        mUriMatcher.addURI(AUTHORITY, "books", BOOK_DIR);
        mUriMatcher.addURI(AUTHORITY, "books/#", BOOK_ITEM);
    }

    @Override
    public boolean onCreate() {
        Log.i(TAG, "onCreate");
        mSqliteHelper = SqliteHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
            @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.i(TAG, "query");
        SQLiteDatabase db = mSqliteHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (mUriMatcher.match(uri)) {
            case USER_DIR:
                cursor = db.query("users", projection, selection, selectionArgs, null, null,
                        sortOrder);
                break;
            case USER_ITEM:
                String useId = uri.getPathSegments().get(1);
                cursor = db.query("users", projection, "id = ?", new String[] {
                        useId
                }, null, null, sortOrder);
                break;
            case BOOK_DIR:
                cursor = db.query("books", projection, selection, selectionArgs, null, null,
                        sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("books", projection, "id = ?", new String[] {
                        bookId
                }, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (mUriMatcher.match(uri)) {
            case USER_DIR:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "/users";
            case USER_ITEM:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "/users";
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "/books";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "/books";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.i(TAG, "insert");
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (mUriMatcher.match(uri)) {
            case USER_DIR:
            case USER_ITEM:
                long newUserId = db.insert("users", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/users/" + newUserId);
                break;
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("books", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/books/" + newBookId);
                break;
            default:
                break;
        }
        Log.i(TAG, "insert" + values.toString() + " uri = " + uriReturn);
        return uriReturn;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        Log.i(TAG, "delete");
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (mUriMatcher.match(uri)) {
            case USER_DIR:
                deleteRows = db.delete("users", selection, selectionArgs);
                break;
            case USER_ITEM:
                String userId = uri.getPathSegments().get(1);
                deleteRows = db.delete("users", "id = ?", new String[] {
                        userId
                });
                break;
            case BOOK_DIR:
                deleteRows = db.delete("books", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete("books", "id = ?", new String[] {
                        bookId
                });
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
            @Nullable String[] selectionArgs) {
        Log.i(TAG, "update");
        SQLiteDatabase db = mSqliteHelper.getWritableDatabase();
        int updateRows = 0;
        switch (mUriMatcher.match(uri)) {
            case USER_DIR:
                updateRows = db.update("users", values, selection, selectionArgs);
                break;
            case USER_ITEM:
                String userId = uri.getPathSegments().get(1);
                updateRows = db.update("users", values, "id = ?", new String[] {
                        userId
                });
                break;
            case BOOK_DIR:
                updateRows = db.update("books", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("books", values, "id = ?", new String[] {
                        bookId
                });
                break;
            default:
                break;
        }
        return updateRows;
    }
}
